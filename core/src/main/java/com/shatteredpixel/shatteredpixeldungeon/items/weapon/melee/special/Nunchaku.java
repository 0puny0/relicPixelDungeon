package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special;
import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
public class Nunchaku extends MeleeWeapon {
    {
        image = ItemSpriteSheet.NUNCHAKU;
        hitSound = Assets.Sounds.HIT;
        hitSoundPitch=1.3f;
        DMG=Attribute.lowest;
        ASPD =Attribute.highest;
        ACC=Attribute.lower;
    }

}
