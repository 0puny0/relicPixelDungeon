package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Dodge extends FlavourBuff {
    @Override
    public int icon() {
        return BuffIndicator.DODGE;
    }
    @Override
    public void tintIcon(Image icon) {
        icon.hardlight(0.4f, 0.2f, 0.5f+count*0.15f);
    }
    public static final float DURATION	= 2f;

    public boolean doWell(){
        return Random.Float()<count*0.25f;
    }
    public float damageAvoid(){
        return 0.45f;
    }
    private float count = 0;

    public void countUp( float inc ){
        count += inc;
    }

    public void countDown( float inc ){
        count -= inc;
    }

    public float count(){
        return count;
    }

    @Override
    public boolean attachTo(Char target) {
        countUp(1);
        return super.attachTo(target);
    }

    private static final String COUNT = "count";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(COUNT, count);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        count = bundle.getFloat(COUNT);
    }
}
