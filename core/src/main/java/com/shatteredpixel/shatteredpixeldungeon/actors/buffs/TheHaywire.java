package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;

public class TheHaywire extends FlavourBuff{
    {
        type = Buff.buffType.POSITIVE;
    }
    @Override
    public int icon() {
        return BuffIndicator.HASTE;
    }

    @Override
    public void tintIcon(Image icon) {
        icon.hardlight(1, 0.5f, 0);
    }

    @Override
    public float iconFadePercent() {
        float max = 4;
        return Math.max(0, (max-visualcooldown()) / max);
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc", lvl(),(int)visualcooldown());
    }
    public  int lvl(){
        if (Dungeon.hero.pointsInTalent(Talent.THE_HAYWIRE)>1){
            return Dungeon.hero.pointsInTalent(Talent.THE_HAYWIRE)-1;
        }else {
            return 0;
        }
    }
}
