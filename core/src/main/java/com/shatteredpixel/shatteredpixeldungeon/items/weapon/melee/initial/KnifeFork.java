package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.initial;
import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class KnifeFork extends MeleeWeapon {
    {
        image = ItemSpriteSheet.KNIFE_FORK;
        hitSound = Assets.Sounds.HIT_STAB;
        hitSoundPitch=1.1f;
        DMG=Attribute.lowest;
        ASPD =Attribute.highest;
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        if(form==Form.FORM1){
            float procChance = 0.15f+(int)(Math.sqrt(8 * buffedLvl() + 1) - 1)/2*0.1f;
            if (defender.HP <= damage && Random.Float() < procChance){
                Buff.affect(defender, EatProc.class);
            }
        }
        return super.proc(attacker, defender, damage);
    }
    public float satisfyMultiplier(){
        switch (form){
            case FORM0:
                return 1.15f+(int)(Math.sqrt(8 * buffedLvl() + 1) - 1)/2*0.1f;
            case FORM1:
                return 1f;
            case FORM2:
                return 1-(0.15f+(int)(Math.sqrt(8 * buffedLvl() + 1) - 1)/2*0.1f);
        }
        return 1.15f+(int)(Math.sqrt(8 * buffedLvl() + 1) - 1)/2*0.1f;
    }
    public String statsInfo() {
        if(isIdentified()){
            return  Messages.get(this, "stats_desc",15+(int)(Math.sqrt(8 * buffedLvl() + 1) - 1)/2*10);
        }else {
            return  Messages.get(this, "typical_stats_desc",15);
        }
    }
    public static class EatProc extends Buff {
        @Override
        public boolean act() {
            detach();
            return true;
        }
    }
}
