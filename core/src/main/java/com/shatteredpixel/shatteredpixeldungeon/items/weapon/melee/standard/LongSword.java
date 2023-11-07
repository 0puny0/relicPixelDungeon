package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class LongSword extends MeleeWeapon {

    {
        image = ItemSpriteSheet.LONG_SWORD;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch=1f;
    }
}
