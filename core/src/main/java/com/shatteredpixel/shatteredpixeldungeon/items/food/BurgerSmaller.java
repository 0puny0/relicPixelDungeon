package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class BurgerSmaller extends Food {
    {
        image = ItemSpriteSheet.BURGER_SMALLER;
        energy = UNIT_ENERGY;
        eatTime=TIME_TO_EAT/3*2;
    }
    @Override
    public int value() {
        return 10 * quantity;
    }
}
