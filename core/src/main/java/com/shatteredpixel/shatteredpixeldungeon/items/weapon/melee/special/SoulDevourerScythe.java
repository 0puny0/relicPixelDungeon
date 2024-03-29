package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Combo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard.Scimitar;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.BArray;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class SoulDevourerScythe extends MeleeWeapon {
    {
        image = ItemSpriteSheet.SOUL_DEVOURER_SCYTHE;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch = 1.1f;
        ASPD=Attribute.lowest;
        usesTargeting = true;
        hasSkill=true;
        defaultAction=AC_WEAPONSKILL;

    }
    @Override
    public void execute(Hero hero, String action) {

        super.execute(hero, action);
        if (action.equals(AC_WEAPONSKILL)) {
            if (!isEquipped(hero)){
                GLog.i( Messages.get(MeleeWeapon.class, "need_to_equip") );
                return;
            }
            onHarvest();
        }
    }

    int soulHarvest=0;

    private static final String SOULHARVEST  = "soulharvest";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(SOULHARVEST,soulHarvest);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        soulHarvest=bundle.getInt(SOULHARVEST);
    }
    @Override
    public String statsInfo() {
        if(isIdentified()){
            return  Messages.get(this, "stats_desc",soulHarvest);
        }else {
            return  Messages.get(this, "typical_stats_desc");
        }

    }
    @Override
    public int max(int lvl) {
        return super.max(lvl)+soulHarvest;
    }

    public void onHarvest(){
        Hero hero=Dungeon.hero;
        Sample.INSTANCE.play(Assets.Sounds.CURSED );
        for (Char ch : Actor.chars()){
            if (hero.canAttack(ch)){
                int aoeHit = min();
                if (ch.buff(Vulnerable.class) != null) aoeHit *= 1.33f;
                Buff.affect(ch, GrowProc.class).SetScythe(this);

                ch.sprite.emitter().burst( ShadowParticle.CURSE, 3);
                ch.damage(aoeHit, hero);
                ch.sprite.flash();
            }
        }
        hero.spendAndNext(curUser.attackDelay());
        hero.sprite.attack(hero.pos, new Callback() {
            @Override
            public void call() {

            }
        });
    }
    public static class GrowProc extends Buff {
        SoulDevourerScythe s;
        @Override
        public boolean act() {
            detach();
            return true;
        }
        public void SetScythe(SoulDevourerScythe scythe){
            s=scythe;
        }
        public void Grow(){
            if(s!=null){
                s.soulHarvest++;
            }
        }
    }
}
