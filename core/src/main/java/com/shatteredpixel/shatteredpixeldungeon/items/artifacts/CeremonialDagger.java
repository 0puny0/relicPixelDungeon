package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Wraith;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class CeremonialDagger extends Artifact {
    {
        image = ItemSpriteSheet.CEREMONIAL_DAGGER1;
        levelCap = 10;
        charge = 0;
        partialCharge = 0;
        chargeCap = 5 + level()/2;

        defaultAction = AC_CEREMONY;
    }
    private int storedBloodEnergy = 0;
    public static final String AC_PRICK = "PRICK";
    public static final String  AC_CEREMONY="CEREMONY";
    @Override
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        if (isEquipped( hero )
                && !cursed
                && !hero.isInvulnerable(getClass())
                && hero.buff(MagicImmune.class) == null){
            actions.add(AC_PRICK);
            actions.add(AC_CEREMONY);
        }
        return actions;
    }

    @Override
    public void execute(Hero hero, String action ) {
        super.execute(hero, action);

        if (action.equals(AC_PRICK)){

            int damage = (level()/5+1)*10;

            if (damage > hero.HP) {
                GLog.i( Messages.get(this, "no_hp"));
            } else {
                prick(hero);
            }
        }else   if (action.equals(AC_CEREMONY)){
            if (charge>=2){
                charge-=2;
                ceremony(hero);
            }else {
                GLog.i( Messages.get(this, "no_charge"));
            }
        }
    }


    private void prick(Hero hero){
        int damage = (level()/5+1)*10;
        hero.sprite.operate( hero.pos );
        hero.busy();
        hero.spend(Actor.TICK);
        GLog.w( Messages.get(this, "onprick") );
        if (damage <= 0){
            damage = 1;
        } else {
            Sample.INSTANCE.play(Assets.Sounds.CURSED);
            hero.sprite.emitter().burst( ShadowParticle.CURSE, damage );
        }

        hero.damage(damage, this);

        if (!hero.isAlive()) {
            Badges.validateDeathFromFriendlyMagic();
            Dungeon.fail( getClass() );
            GLog.n( Messages.get(this, "ondeath") );
        } else {
            hero.buff( CeremonialDagger.DaggerRegen.class ).gainBloodValue(damage,true);
        }
    }
    private void ceremony(Hero hero){
        hero.sprite.operate( hero.pos );
        hero.busy();
        hero.spend(Actor.TICK);
        Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
        ArrayList<Integer> respawnPoints = new ArrayList<>();
        for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
            int p = hero.pos + PathFinder.NEIGHBOURS8[i];
            if (Actor.findChar( p ) == null && Dungeon.level.passable[p]) {
                respawnPoints.add( p );
            }
        }
        if (respawnPoints.size() > 0) {
            int callNum=Math.min(level()/5+2,respawnPoints.size());
            for (int i=0;i<callNum;i++){
                int index = Random.index( respawnPoints );
                Wraith w = Wraith.spawnAt(respawnPoints.get(index));
                Buff.affect(w, Corruption.class);
                respawnPoints.remove(index);
            }
        }
        updateQuickslot();
    }

    @Override
    public void level(int value) {
        super.level( value);
        chargeCap = 5 + level()/2;
        if (level() >= 10)
            image = ItemSpriteSheet.CEREMONIAL_DAGGER;
        else if (level() >= 5)
            image = ItemSpriteSheet.CEREMONIAL_DAGGER;
    }

    @Override
    public Item upgrade() {
        super.upgrade();
        chargeCap = 5 + level()/2;
        if (level() >= 10)
            image = ItemSpriteSheet.CEREMONIAL_DAGGER3;
        else if (level() >= 5)
            image = ItemSpriteSheet.CEREMONIAL_DAGGER2;
        return this;
    }
    private static final String STORED = "stored";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put( STORED, storedBloodEnergy );
    }
    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        storedBloodEnergy=bundle.getInt(STORED);
        if (level() >= 10) image = ItemSpriteSheet.CEREMONIAL_DAGGER3;
        else if (level() >= 5) image = ItemSpriteSheet.CEREMONIAL_DAGGER2;
    }

    @Override
    protected ArtifactBuff passiveBuff() {
        return new DaggerRegen();
    }

    //神器充能
    @Override
    public void charge(Hero target, float amount) {
        if (cursed || target.buff(MagicImmune.class) != null) return;
        partialCharge += 0.25f*amount;
        if (partialCharge >= 1){
            partialCharge--;
            charge++;

            if (charge == chargeCap){
                GLog.p( Messages.get(this, "full") );
                partialCharge = 0;
            }
            updateQuickslot();
        }
    }
    @Override
    public String desc() {
        String desc = super.desc();

        if (isEquipped (Dungeon.hero)){
            desc += "\n\n";
            if (cursed)
                desc += Messages.get(this, "desc_cursed");
            else if (level() == 0)
                desc += Messages.get(this, "desc_1");
            else if (level() < levelCap)
                desc += Messages.get(this, "desc_2");
            else
                desc += Messages.get(this, "desc_3");
        }

        return desc;
    }

    public class DaggerRegen extends ArtifactBuff {
        public void gainBloodValue(int loseHp,boolean isHero){
            if (cursed)return;
            //充能
            if (charge!=chargeCap){
                if (isHero){
                    partialCharge+=loseHp/((level()/5+1)*20f);
                }else {
                    partialCharge+=loseHp/((level()/5+1)*80f);
                }
                if (partialCharge>1){
                    charge+=partialCharge/1;
                    partialCharge=partialCharge%1;
                    updateQuickslot();
                }
            }else {
                partialCharge=0;
            }
            //经验
            if (level() >= levelCap||!isHero) return;
            storedBloodEnergy+=loseHp;
            if (storedBloodEnergy>40+level()*40){
                int upgrades =storedBloodEnergy/( 40+level()*40);
                upgrades = Math.min(upgrades, levelCap-level());
                upgrade(upgrades);
                if (level() == levelCap){
                    storedBloodEnergy=0;
                    GLog.p( Messages.get(CeremonialDagger.class, "levelup"));
                } else if(level()==5) {
                    GLog.p( Messages.get(CeremonialDagger.class, "levelup") );
                }
            }
        }
    }
}
