package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;

public class ArmorBreak extends FlavourBuff{
    {
        type=buffType.NEGATIVE ;
        announced=true;
    }
    public static int DURATION=5;
    @Override
    public int icon() {
        return BuffIndicator.ARMOR_BREAK;
    }

}
