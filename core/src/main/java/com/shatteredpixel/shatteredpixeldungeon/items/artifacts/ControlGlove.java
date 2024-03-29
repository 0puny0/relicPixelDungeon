package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LockedFloor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PinCushion;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.DwarfKing;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEnergy;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.TargetedSpell;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ControlGlove extends Artifact{
    {
        image = ItemSpriteSheet.CONTROL_GLOVE;

        exp = 0;
        levelCap = 10;

        charge = Math.min(level()+3, 10);
        partialCharge = 0;
        chargeCap = Math.min(level()+3, 10);
        defaultAction = AC_CONTROL;
    }
    public static final String AC_CONTROL = "CONTROL";
    @Override
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        if (isEquipped( hero )
                && !cursed
                && hero.buff(MagicImmune.class) == null
                && charge > 0 ) {
            actions.add(AC_CONTROL);
        }
        return actions;
    }

    @Override
    public void execute( Hero hero, String action ) {
        super.execute(hero, action);
        if (hero.buff(MagicImmune.class) != null) return;
        if (action.equals( AC_CONTROL )) {
            if (charge>0){
                GameScene.selectCell(onControl);
            }else {
                GLog.i( Messages.get(this, "no_charge"));
            }
        }
    }
    @Override
    public String desc() {
        String desc = super.desc();

        if ( isEquipped( Dungeon.hero ) ){
            if (!cursed) {
                desc += "\n\n" + Messages.get(this, "desc_equipped");

            } else {
                desc += "\n\n" + Messages.get(this, "desc_cursed");
            }
        }

        return desc;
    }
    @Override
    public void charge(Hero target, float amount) {
        if (cursed || target.buff(MagicImmune.class) != null) return;
        if (charge < chargeCap) {
            partialCharge += 0.25f*amount;
            if (partialCharge >= 1){
                partialCharge--;
                charge++;
                updateQuickslot();
            }
        }
    }
    @Override
    public Item upgrade() {
        if(level()<7){
            chargeCap++;
        }
        return super.upgrade();
    }
    @Override
    protected ArtifactBuff passiveBuff() {
        return new gloveRecharge();
    }

    public class gloveRecharge extends ArtifactBuff{
        @Override
        public boolean act() {
            if (!isCursed()){
                exp++;
                if (exp >= (level() + 1) * 60 && level() < levelCap) {
                    upgrade();
                    exp -= level() * 60;
                    GLog.p(Messages.get(ControlGlove.class, "levelup"));
                }
            }
            if (charge < chargeCap && !cursed && target.buff(MagicImmune.class) == null) {
                LockedFloor lock = target.buff(LockedFloor.class);
                if (activeBuff == null && (lock == null || lock.regenOn())) {
                    float missing = (chargeCap - charge);
                    if (level() > 7) missing += 5*(level() - 7)/3f;
                    float turnsToCharge = (65 - missing);
                    turnsToCharge /= RingOfEnergy.artifactChargeMultiplier(target);
                    float chargeToGain = (1f / turnsToCharge);
                    partialCharge += chargeToGain;
                }

                if (partialCharge >= 1) {
                    charge++;
                    partialCharge -= 1;
                    if (charge == chargeCap){
                        partialCharge = 0;
                    }
                }
            } else {
                if (cursed && Random.Float() <0.02f){
                    Item toDrop=Dungeon.hero.belongings.randomUnequipped();
                    if (toDrop != null){
                        Dungeon.level.drop( toDrop.detachAll(Dungeon.hero.belongings.backpack),  Dungeon.hero.pos ).sprite.drop();
                        GLog.n( Messages.get(ControlGlove.class, "curse_effect"));
                    }
                }
                partialCharge = 0;
            }

            if (cooldown > 0)
                cooldown --;

            updateQuickslot();

            spend( TICK );

            return true;
        }
    }
    private CellSelector.Listener onControl = new CellSelector.Listener() {
        @Override
        public void onSelect( Integer target ) {
            if (target != null) {
                final Ballistica shot = new Ballistica( curUser.pos, target, Ballistica.PROJECTILE);
                int cell = shot.collisionPos;

                curUser.sprite.zap(cell);
                if (Actor.findChar(target) != null)
                    QuickSlotButton.target(Actor.findChar(target));
                else
                    QuickSlotButton.target(Actor.findChar(cell));

                curUser.busy();

                fx(shot, new Callback() {
                    public void call() {
                        affectTarget(shot, curUser);
                        Invisibility.dispel();
                        updateQuickslot();
                        curUser.spendAndNext( 1f );
                    }
                });
            }
        }
        @Override
        public String prompt() {
            return Messages.get(TargetedSpell.class, "prompt");
        }
    };

    protected void fx(Ballistica bolt, Callback callback) {
        MagicMissile.boltFromChar( curUser.sprite.parent,
                MagicMissile.BEACON,
                curUser.sprite,
                bolt.collisionPos,
                callback);
        Sample.INSTANCE.play( Assets.Sounds.ZAP );
    }

    protected void affectTarget(Ballistica bolt, Hero hero) {
        charge--;
        Char ch = Actor.findChar(bolt.collisionPos);

        //special logic for DK when he is on his throne
        if (ch == null && bolt.path.size() > bolt.dist+1){
            ch = Actor.findChar(bolt.path.get(bolt.dist+1));
            if (!(ch instanceof DwarfKing && Dungeon.level.solid[ch.pos])){
                ch = null;
            }
        }

        if (ch != null && ch.buff(PinCushion.class) != null){

            while (ch.buff(PinCushion.class) != null) {
                Item item = ch.buff(PinCushion.class).grabOne();

                if (item.doPickUp(hero, ch.pos)) {
                    hero.spend(-Item.TIME_TO_PICK_UP); //casting the spell already takes a turn
                    GLog.i( Messages.capitalize(Messages.get(hero, "you_now_have", item.name())) );

                } else {
                    GLog.w(Messages.get(this, "cant_grab"));
                    Dungeon.level.drop(item, ch.pos).sprite.drop();
                    return;
                }

            }

        } else if (Dungeon.level.heaps.get(bolt.collisionPos) != null){

            Heap h = Dungeon.level.heaps.get(bolt.collisionPos);

            if (h.type != Heap.Type.HEAP){
                GLog.w(Messages.get(this, "cant_grab"));
                h.sprite.drop();
                return;
            }

            while (!h.isEmpty()) {
                Item item = h.peek();
                if (item.doPickUp(hero, h.pos)) {
                    h.pickUp();
                    hero.spend(-Item.TIME_TO_PICK_UP); //casting the spell already takes a turn
                    GLog.i( Messages.capitalize(Messages.get(hero, "you_now_have", item.name())) );

                } else {
                    GLog.w(Messages.get(this, "cant_grab"));
                    h.sprite.drop();
                    return;
                }
            }

        } else {
            GLog.w(Messages.get(this, "no_target"));
        }

    }

}
