package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArmorBreak;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class RottenEgg extends MissileWeapon {
    {
        image = ItemSpriteSheet.ROTTEN_EGG;
        hitSound = Assets.Sounds.HIT;
        hitSoundPitch = 0.85f;

        tier = 3;
        baseUses = 5;
    }
    @Override
    public int proc(Char attacker, Char defender, int damage ) {
        Buff.prolong( defender, ArmorBreak.class,ArmorBreak.DURATION );
        return super.proc( attacker, defender, damage );
    }
}
