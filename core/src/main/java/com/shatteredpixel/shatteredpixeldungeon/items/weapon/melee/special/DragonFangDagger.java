package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class DragonFangDagger extends MeleeWeapon {
    {
        image = ItemSpriteSheet.DRAGON_FANG_DAGGER;
        hitSound = Assets.Sounds.HIT_STAB;
        hitSoundPitch = 0.95f;
        DMG =Attribute.lower;
        ASPD=Attribute.higher;
    }

    @Override
    public String statsInfo() {
        if(isIdentified()){
            return  Messages.get(this, "stats_desc",10+LevelAppreciation()*5);
        }else {
            return  Messages.get(this, "typical_stats_desc",10);
        }
    }
    @Override
    public int proc(Char attacker, Char defender, int damage) {
        if (defender.surprisedBy(attacker)) {
           damage+=Math.round(attacker.HP*(0.1f+LevelAppreciation()*0.05f));
        }
        return super.proc(attacker, defender, damage);
    }
}
