package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard;
import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
public class DoubleBlade extends MeleeWeapon {
    {
        image = ItemSpriteSheet.DOUBLE_BLADE;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch=1.15f;
        DMG=Attribute.lowest;
        ASPD =Attribute.highest;
    }
}
