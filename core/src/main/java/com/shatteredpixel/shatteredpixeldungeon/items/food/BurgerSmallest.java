package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CounterBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class BurgerSmallest extends Food {
    {
        image = ItemSpriteSheet.BURGER_SMALLEST;
        energy = UNIT_ENERGY/3*2;
        eatTime=TIME_TO_EAT/3;
    }
    @Override
    public int value() {
        return 10 * quantity;
    }
}
