package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special;
import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfForceOut;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class Nunchaku extends MeleeWeapon {
    {
        image = ItemSpriteSheet.NUNCHAKU;
        hitSound = Assets.Sounds.HIT;
        hitSoundPitch=1.2f;
        DMG=Attribute.lowest;
        ASPD =Attribute.highest;
        ACC=Attribute.lower;
    }
}
