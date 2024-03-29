package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;

public class Miss extends Buff{
    {
        announced=true;
        type=buffType.POSITIVE;
    }

    @Override
    public int icon() {
        return BuffIndicator.HASTE;
    }

    @Override
    public void tintIcon(Image icon) {
        icon.hardlight(0f, 0.2f, 0.5f);
    }
}
