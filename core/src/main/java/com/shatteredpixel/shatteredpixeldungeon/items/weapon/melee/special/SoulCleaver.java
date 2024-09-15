package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class SoulCleaver extends MeleeWeapon {
    {
        image = ItemSpriteSheet.KATANA;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch=0.95f;
        DMG=Attribute.lower;
        ACC=Attribute.lower;
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        damage+= defender.HP/20;
        return super.proc(attacker, defender, damage);
    }
}
