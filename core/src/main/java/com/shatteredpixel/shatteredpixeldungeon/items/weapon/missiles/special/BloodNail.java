package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Amok;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class BloodNail extends MissileWeapon {
    {
        image = ItemSpriteSheet.BLOOD_NAIL;
        hitSound = Assets.Sounds.HIT_STAB;
        hitSoundPitch = 1.1f;
        tier = 2;
        baseUses = 5;
    }

    @Override
    public int max(int lvl) {
        return  4 * tier +                      //9 base, down from 15
                (tier == 1 ? 2*lvl : tier*lvl); //scaling unchanged
    }

    @Override
    public int proc(Char attacker, Char defender, int damage ) {
        Buff.prolong( defender, Amok.class, 5f );
        return super.proc( attacker, defender, damage );
    }
}
