package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.watabou.noosa.Image;

public class WarWall  extends Buff{
    public int icon(){return BuffIndicator.UPGRADE;}
    public void tintIcon(Image icon){icon.hardlight(0.6f, 0.6f, 0.6f);}
    public  int lvl(){
        return Dungeon.hero.pointsInTalent(Talent.WAR_WALL);
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc", lvl());
    }

    @Override
    public boolean attachTo(Char target) {
        boolean doWell= super.attachTo(target);
        QuickSlotButton.refresh();
        return doWell;
    }

    @Override
    public void detach() {
        super.detach();
        QuickSlotButton.refresh();
    }
};
