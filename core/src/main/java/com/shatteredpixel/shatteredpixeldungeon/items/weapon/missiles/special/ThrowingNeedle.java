package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class ThrowingNeedle extends MissileWeapon {
    {
        image = ItemSpriteSheet.THROWING_NEEDLE;
        hitSound = Assets.Sounds.HIT_STAB;
        hitSoundPitch = 1.2f;

        tier = 5;
    }
    @Override
    public int max(int lvl) {
        return  4 * tier +                      //9 base, down from 15
                (tier == 1 ? 2*lvl : tier*lvl); //scaling unchanged
    }
    @Override
    public float delayFactor(Char owner) {
        return super.delayFactor(owner)*0.5f;
    }
}
