package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.KindOfWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.SpareSword;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.watabou.utils.Callback;

public class ShadowWarriorBuff extends Buff implements ActionIndicator.Action {
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

    }
    public static class RiposteTracker extends Buff{
        { actPriority = VFX_PRIO;}
        public Char enemy;
        @Override
        public boolean act() {
            Hero hero=(Hero) target;
            SpareSword spareSword=hero.belongings.getItem(SpareSword.class);
            if (spareSword!=null&&hero.canAttack(enemy,spareSword)){
                target.sprite.attack(enemy.pos, new Callback() {
                    @Override
                    public void call() {
                        KindOfWeapon w=hero.belongings.weapon;
                        hero.belongings.weapon=spareSword;
                        hero.attack(enemy);
                        hero.belongings.weapon=w;
                        if (hero.hasTalent(Talent.WEAKNESS_MARK)&&enemy!=null){
                            Buff.affect(enemy,ArmorBreak.class,hero.pointsInTalent(Talent.WEAKNESS_MARK));
                        }
                        next();
                    }
                });
                detach();
                return false;
            }else {
                detach();
                return true;
            }
        }
    }
    public static class BlackClothes extends CounterBuff{
        @Override
        public void countUp(float inc) {
            super.countUp(inc);
            if (count()>4- Dungeon.hero.pointsInTalent(Talent.BLACK_CLOTHES)){
                Buff.affect(target,Miss.class);
                detach();
            }
        }
    }
}
