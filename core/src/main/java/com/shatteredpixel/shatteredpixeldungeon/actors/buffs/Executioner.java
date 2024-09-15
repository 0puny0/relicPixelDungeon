package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.watabou.utils.Bundle;

public class Executioner extends Buff implements ActionIndicator.Action{
    private float count = 0;


    public void countUp(float inc) {
        if (count>=4)return;
        count += inc;
    }
    public void countDown( float inc ){
        count -= inc;
        if (count<0)count=0;
    }

    public float damage(){
        return count;
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
    @Override
    public String actionName() {
        return null;
    }

    @Override
    public int indicatorColor() {
        return 0;
    }

    @Override
    public void doAction() {
        countDown(4- Dungeon.hero.pointsInTalent(Talent.EXECUTION_RHYTHM));
    }


}
