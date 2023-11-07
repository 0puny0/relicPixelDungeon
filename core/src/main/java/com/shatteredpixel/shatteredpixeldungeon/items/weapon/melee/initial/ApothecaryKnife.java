package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.initial;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class ApothecaryKnife extends MeleeWeapon {

    {
        image = ItemSpriteSheet.APOTHECARY_KNIFE;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch = 0.9f;
        DMG=Attribute.lower;
        ASPD =Attribute.higher;
        ACC=Attribute.higher;
        bones = false;


    }
}