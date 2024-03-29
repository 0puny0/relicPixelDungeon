package com.shatteredpixel.shatteredpixeldungeon.items.potions;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class PotionOfRandom extends Potion{
    {
        icon = ItemSpriteSheet.Icons.POTION_EXP;

        bones = true;
    }

    @Override
    public void apply( Hero hero ) {
        float chance= Random.Float();
        if (chance<0.1f){
           new PotionOfFrost().apply(hero);
        }else if (chance<0.2f){
            new PotionOfHaste().apply(hero);
        }else if (chance<0.3f){
            new PotionOfInvisibility().apply(hero);
        }else if (chance<0.4f){
            new PotionOfLevitation().apply(hero);
        }else if (chance<0.5f){
            new PotionOfLiquidFlame().apply(hero);
        }else if (chance<0.6f){
            new PotionOfMindVision().apply(hero);
        }else if (chance<0.7f){
            new PotionOfParalyticGas().apply(hero);
        }else if (chance<0.8f){
            new PotionOfPurity().apply(hero);
        }else if (chance<0.9f){
            new PotionOfToxicGas().apply(hero);
        }else {
            new PotionOfExperience().apply(hero);
            new PotionOfHealing().apply(hero);
            new PotionOfStrength().apply(hero);
        }
    }
}
