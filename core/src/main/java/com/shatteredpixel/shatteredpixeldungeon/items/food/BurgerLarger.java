package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class BurgerLarger extends Food {
    {
        image = ItemSpriteSheet.BURGER_LARGER;
        energy = UNIT_ENERGY*2;
        eatTime=TIME_TO_EAT;
    }
    @Override
    public int value() {
        return 20 * quantity;
    }
}
