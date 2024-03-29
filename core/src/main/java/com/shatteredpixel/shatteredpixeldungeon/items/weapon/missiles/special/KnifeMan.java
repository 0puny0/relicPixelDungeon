package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class KnifeMan extends MissileWeapon {
    {
        image = ItemSpriteSheet.KNIFE_MAN;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch = 1.2f;
        tier = 3;
    }

    @Override
    public float accuracyFactor(Char owner, Char target) {
        return Char.INFINITE_ACCURACY;
    }
}
