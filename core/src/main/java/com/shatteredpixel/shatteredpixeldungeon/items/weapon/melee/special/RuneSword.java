package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special;
import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.custom.messages.M;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.CursedWand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.DamageWand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.initial.MagesStaff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.initial.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MissileSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class RuneSword extends MeleeWeapon {
    {
        image = ItemSpriteSheet.RUNE_SWORD;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch=1f;
        DMG=Attribute.lower;
        ACC=Attribute.higher;
        usesTargeting = true;
        hasSkill=true;
        defaultAction=AC_WEAPONSKILL;
    }
    public static final String AC_FILL		="FILL";
    private int maxCharges=4;
    private int curCharges=0;
    @Override
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions=super.actions(hero);
        actions.add(AC_FILL);
        return actions;
    }

    @Override
    public void execute(Hero hero, String action) {

        super.execute(hero, action);

        if (!isEquipped(hero)){
            GLog.i( Messages.get(MeleeWeapon.class, "need_to_equip") );
            return;
        }
        if (action.equals(AC_WEAPONSKILL)) {
            if(curCharges>0){
                onZap();
            }else {
                onFill();
            }
        }
        if (action.equals(AC_FILL)){
            onFill();
        }
    }

    @Override
    public String statsInfo() {
        if(isIdentified()){
            return  Messages.get(this, "stats_desc",tier+buffedLvl(),Math.round(tier/2f*(6+buffedLvl())));
        }else {
            return  Messages.get(this, "typical_stats_desc",tier,3*tier);
        }

    }
    private static final String CUR_CHARGES    = "curcharges";
    private static final String MAX_CHARGES    = "maxcharges";
    @Override
    public void storeInBundle( Bundle bundle ) {
        super.storeInBundle( bundle );
        bundle.put(CUR_CHARGES,curCharges);
        bundle.put(MAX_CHARGES,maxCharges);
    }
    @Override
    public void restoreFromBundle( Bundle bundle ) {
        super.restoreFromBundle( bundle );
        curCharges=bundle.getInt(CUR_CHARGES);
        maxCharges=bundle.getInt(MAX_CHARGES);
        modeSwitch();
    }
    public void modeSwitch() {
        if(curCharges>0){
            image = ItemSpriteSheet.RUNE_SWORD_;
            DMG=Attribute.higher;
        }else {
            image = ItemSpriteSheet.RUNE_SWORD;
            DMG=Attribute.lower;
        }
    }
    public void useCharge(){
        if(curCharges>0) curCharges--;
        modeSwitch();
        updateQuickslot();
    }
    @Override
    public String status() {
        if (levelKnown) {
            return (isIdentified() ? curCharges : "?") + "/" + maxCharges;
        } else {
            return null;
        }
    }
    @Override
    public int buffedLvl() {
        return super.buffedLvl()+slashSwordQi().buffedLvl();
    }
    private void onFill(){
        curCharges=maxCharges;
        curUser.sprite.operate(curUser.pos);
        Sample.INSTANCE.play(Assets.Sounds.READ );
        curUser.spendAndNext( 1f );
        modeSwitch();
        updateQuickslot();
    }
    private void onZap(){
        curUser = Dungeon.hero;
        curItem = this;
        GameScene.selectCell( shooter );
        updateQuickslot();
    }
    public SwordQi slashSwordQi(){
        return new SwordQi();
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        useCharge();
        return super.proc(attacker, defender, damage);
    }

    public  class SwordQi extends DamageWand {
        {
            image = ItemSpriteSheet.SWORD_QI;
        }

        @Override
        public void fx(Ballistica bolt, Callback callback) {
            Sample.INSTANCE.play( Assets.Sounds.MISS);
            ((MissileSprite) curUser.sprite.parent.recycle(MissileSprite.class)).
                    reset(curUser.sprite,
                            bolt.collisionPos,
                            this,callback);
        }
        @Override
        public int min(int lvl) {
            return RuneSword.this.tier+RuneSword.this.buffedLvl();
        }

        @Override
        public int max(int lvl) {
            return Math.round(RuneSword.this.tier/2f*(6+RuneSword.this.buffedLvl()));
        }

        @Override
        public void onZap(Ballistica bolt) {

            Char ch = Actor.findChar( bolt.collisionPos );
            if (ch != null) {
                wandProc(ch, chargesPerCast());
                int dis= Dungeon.level.distance(bolt.collisionPos,bolt.sourcePos)-1;
                ch.damage(damageRoll(), this);
                Sample.INSTANCE.play( Assets.Sounds.HIT_MAGIC, 1-dis*0.2f, Random.Float(0.87f, 1.15f) );

                ch.sprite.burst(0x38c3c3, RuneSword.this.buffedLvl() / 3 + 1);
            } else {
                Dungeon.level.pressCell(bolt.collisionPos);
            }
        }

        @Override
        public void onHit(MagesStaff staff, Char attacker, Char defender, int damage) {

        }
    }
    private CellSelector.Listener shooter = new CellSelector.Listener() {
        @Override
        public void onSelect( Integer target ) {
            if (target != null) {
                if (Dungeon.hero.buff(MagicImmune.class) != null){
                    GLog.w( Messages.get(Wand.class, "no_magic") );
                    return ;
                }
                SwordQi swordQi =slashSwordQi();
                final Ballistica shot = new Ballistica(curUser.pos, target, Ballistica.MAGIC_BOLT);
                int cell = shot.collisionPos;
                curUser.sprite.zap(cell);
                //attempts to target the cell aimed at if something is there, otherwise targets the collision pos.
                if (Actor.findChar(target) != null){
                    QuickSlotButton.target(Actor.findChar(target));
                } else{
                    QuickSlotButton.target(Actor.findChar(cell));
                }
                curUser.busy();
                swordQi.fx(shot, new Callback() {
                        public void call() {
                            swordQi.onZap(shot);
                            Invisibility.dispel();
                            curUser.spendAndNext( 1f);
                            useCharge();
                        }
                    });
            }
        }
        @Override
        public String prompt() {
            return Messages.get(SpiritBow.class, "prompt");
        }
    };
}
