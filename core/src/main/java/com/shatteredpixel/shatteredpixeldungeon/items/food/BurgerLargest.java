package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class BurgerLargest extends Food {
    {
        image = ItemSpriteSheet.BURGER_LARGEST;
        energy = UNIT_ENERGY*3;
        eatTime=TIME_TO_EAT;
    }
    @Override
    public int value() {
        return 30 * quantity;
    }
}
