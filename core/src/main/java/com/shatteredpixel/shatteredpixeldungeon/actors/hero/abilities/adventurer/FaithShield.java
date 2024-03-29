package com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.adventurer;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Combo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TalismanOfForesight;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special.Shuriken;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special.ThrowingShield;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MissileSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;

import java.util.HashSet;

public class FaithShield extends ArmorAbility {
    {
        baseChargeUse = 35;
    }
    @Override
    protected void activate(ClassArmor armor, Hero hero, Integer target) {
        armor.charge -= chargeUse(hero);
        Buff.affect(hero,ParryTracker.class,30f).setCharge(armor,chargeUse(hero));
        armor.updateQuickslot();
        Sample.INSTANCE.play(Assets.Sounds.SCAN);
    }
    @Override
    public Talent[] talents() {
        return new Talent[]{Talent.STRONG_IMPACT,Talent.FAITH_AFTERGLOW,Talent.FAITH_ETERNAL,Talent.HEROIC_ENERGY};
    }

    public static class ParryTracker extends FlavourBuff{
        {
            actPriority = HERO_PRIO+1;
            type=buffType.POSITIVE;
            announced=true;
        }

        @Override
        public int icon() {
            return BuffIndicator.SHELTER;
        }

        @Override
        public void tintIcon(Image icon) {
            icon.hardlight(1f, 1f, 0f);
        }

        @Override
        public float iconFadePercent() {
            return Math.max(0, (30 - visualcooldown()) / 30);
        }

        private boolean parry=false;
        private ClassArmor armor;
        private float charge;
        public void parry(){
            parry=true;
            detach();
        }
        public void  setCharge(ClassArmor classArmor,float armorCharge){
            armor=classArmor;
            charge=armorCharge;
        }
        @Override
        public void detach() {
            if (!parry&&Dungeon.hero.hasTalent(Talent.FAITH_ETERNAL)&&armor!=null){
                armor.charge=Math.min(armor.charge+charge*Dungeon.hero.pointsInTalent(Talent.FAITH_ETERNAL)*0.25f,100f);
            }
            super.detach();
        }
        private static final String ARMOR="armor";
        private static final String CHARGE="charge";

        @Override
        public void storeInBundle(Bundle bundle) {
            super.storeInBundle(bundle);
            bundle.put(ARMOR, armor);
            bundle.put(CHARGE,charge);
        }

        @Override
        public void restoreFromBundle(Bundle bundle) {
            super.restoreFromBundle(bundle);
            armor = (ClassArmor) bundle.get(ARMOR);
            charge=bundle.getFloat(CHARGE);
        }
    }
    public static class RiposteTracker extends Buff{
        { actPriority = VFX_PRIO;}
        public Char enemy;
        @Override
        public boolean act() {
            Hero hero= Dungeon.hero;
            ThrowingShield proto = new FaithThrowingShield();
            proto.level(Dungeon.hero.pointsInTalent(Talent.STRONG_IMPACT));
            final HashSet<Callback> callbacks = new HashSet<>();

            Callback callback = new Callback() {
                @Override
                public void call() {
                    hero.shoot(enemy,proto);
                    callbacks.remove( this );
                    next();
                }
            };
            MissileSprite m = ((MissileSprite)hero.sprite.parent.recycle( MissileSprite.class ));
            m.reset( hero.sprite, enemy.pos, proto, callback );
            m.hardlight(1f, 1f, 0.1f);
            m.alpha(0.8f);
            callbacks.add( callback );

            hero.sprite.zap( enemy.pos );
            hero.busy();
            detach();
            return false;
        }
    }

}
class FaithThrowingShield extends ThrowingShield{

    @Override
    public int proc( Char attacker, Char defender, int damage ) {

        Ballistica trajectory = new Ballistica(attacker.pos, defender.pos, Ballistica.STOP_TARGET);
        trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size()-1), Ballistica.PROJECTILE);
        WandOfBlastWave.throwChar(defender,
                trajectory,
                2+Dungeon.hero.pointsInTalent(Talent.STRONG_IMPACT),
                true,
                true,
                getClass());
        if (Dungeon.hero.hasTalent(Talent.FAITH_AFTERGLOW)&&defender.isAlive()){
            int point=Dungeon.hero.pointsInTalent(Talent.FAITH_AFTERGLOW);
            Buff.affect(attacker, TalismanOfForesight.CharAwareness.class,point*2).charID = defender.id();
            Buff.affect(defender, Blindness.class,point*2);
        }
        return damage;
    }

    @Override
    public float accuracyFactor(Char owner, Char target) {
        return Char.INFINITE_ACCURACY;
    }
}