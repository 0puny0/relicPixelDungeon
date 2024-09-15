package com.shatteredpixel.shatteredpixeldungeon.items.treasure;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class GoldenCudgel extends MeleeWeapon {
    {
        image = ItemSpriteSheet.GOLDEN_CUDGEL;
        hitSound = Assets.Sounds.HIT_CRUSH;
        hitSoundPitch = 1.15f;
        unique = true;
        treasure=true;
    }

    @Override
    public int STRReq(int lvl) {
        changeTier();
        return 8 + tier * 2;
    }

    public void changeTier(){
        setTier((Dungeon.hero.STR()-8)/2);
    }
}
