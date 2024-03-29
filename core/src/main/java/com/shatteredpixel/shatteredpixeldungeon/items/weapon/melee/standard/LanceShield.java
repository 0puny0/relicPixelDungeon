package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class LanceShield extends MeleeWeapon {
    {
        image = ItemSpriteSheet.LANCE_SHIELD;
        hitSound = Assets.Sounds.HIT_STAB;
        hitSoundPitch=1f;
        DMG=Attribute.lower;
        RCH=2;
    }


}
