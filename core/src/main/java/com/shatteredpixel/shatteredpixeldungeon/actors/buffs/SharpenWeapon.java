package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.watabou.noosa.Image;

public class SharpenWeapon extends FlavourBuff{
    {
        type = buffType.POSITIVE;
    }

    @Override
    public int icon() {
        return BuffIndicator.UPGRADE;
    }

    @Override
    public void tintIcon(Image icon) {
        icon.hardlight(0.5f, 0f, 1f);
    }

    @Override
    public float iconFadePercent() {
        float max = 2+Dungeon.hero.pointsInTalent(Talent.SHARPEN_WEAPON)/3;
        return Math.max(0, (max-visualcooldown()) / max);
    }
    public  int lvl(){
        return 1+Dungeon.hero.pointsInTalent(Talent.SHARPEN_WEAPON)/2;
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
