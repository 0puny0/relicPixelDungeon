package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special;
import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
public class Rapier extends MeleeWeapon {
    {
        image = ItemSpriteSheet.RAPIER;
        hitSound = Assets.Sounds.HIT_STAB;
        hitSoundPitch=1.15f;
        DMG=Attribute.lower;
        ASPD =Attribute.highest;
        ACC=Attribute.lowest;

    }
}
