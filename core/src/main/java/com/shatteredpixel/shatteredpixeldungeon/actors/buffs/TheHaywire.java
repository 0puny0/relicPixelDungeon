package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.watabou.noosa.Image;

public class TheHaywire extends FlavourBuff{
    {
        type = Buff.buffType.POSITIVE;
    }

    @Override
    public int icon() {
        return BuffIndicator.ADVANCE;
    }

    @Override
    public void tintIcon(Image icon) {
        icon.hardlight(1, 0.6f, 0);
    }

    @Override
    public float iconFadePercent() {
        float max = 2+Dungeon.hero.pointsInTalent(Talent.THE_HAYWIRE)/3;
        return Math.max(0, (max-visualcooldown()) / max);
    }
    public  int lvl(){
        return 1+Dungeon.hero.pointsInTalent(Talent.THE_HAYWIRE)/2;
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc", lvl(),dispTurns());
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
}
