package com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.adventurer;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.effects.CheckedCell;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TalismanOfForesight;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.Trap;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.ConeAOE;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.audio.Sample;

public class AlertScan extends ArmorAbility {
    {
        baseChargeUse = 25;
    }
    @Override
    protected void activate(ClassArmor armor, Hero hero, Integer target) {
        Ballistica aim;
        //The direction of the aim only matters if it goes outside the map
        //So we try to aim in the cardinal direction that has the most space
        int x = hero.pos % Dungeon.level.width();
        int y = hero.pos / Dungeon.level.width();

        if (Math.max(x, Dungeon.level.width()-x) >= Math.max(y, Dungeon.level.height()-y)){
            if (x > Dungeon.level.width()/2){
                aim = new Ballistica(hero.pos, hero.pos - 1, Ballistica.WONT_STOP);
            } else {
                aim = new Ballistica(hero.pos, hero.pos + 1, Ballistica.WONT_STOP);
            }
        } else {
            if (y > Dungeon.level.height()/2){
                aim = new Ballistica(hero.pos, hero.pos - Dungeon.level.width(), Ballistica.WONT_STOP);
            } else {
                aim = new Ballistica(hero.pos, hero.pos + Dungeon.level.width(), Ballistica.WONT_STOP);
            }
        }

        int aoeSize = 4 + hero.pointsInTalent(Talent.SCAN_RADIUS);

        int projectileProps = Ballistica.STOP_TARGET;

        ConeAOE aoe = new ConeAOE(aim, aoeSize, 360, projectileProps);

        boolean noticed = false;
        int shieldCount=0;
        for (int cell : aoe.cells) {
            GameScene.effectOverFog(new CheckedCell(cell, hero.pos));
            if (Dungeon.level.discoverable[cell] && !(Dungeon.level.mapped[cell] || Dungeon.level.visited[cell])) {
                Dungeon.level.mapped[cell] = true;
            }
            if (Dungeon.level.secret[cell]) {
                int oldValue = Dungeon.level.map[cell];
                GameScene.discoverTile(cell, oldValue);
                Dungeon.level.discover(cell);
                ScrollOfMagicMapping.discover(cell);
                noticed = true;
            }
            Char ch = Actor.findChar(cell);
            if (ch != null && ch.alignment != Char.Alignment.NEUTRAL && ch.alignment != hero.alignment){
                Buff.append(hero, TalismanOfForesight.CharAwareness.class, 10 ).charID = ch.id();
                if (hero.hasTalent(Talent.WEAKNESS_INSIGHT)){
                    Buff.affect(ch, Vulnerable.class,hero.pointsInTalent(Talent.WEAKNESS_INSIGHT)*3);
                }
                shieldCount++;
            }
            Trap t = Dungeon.level.traps.get(cell);
            if (t != null){
                Buff.append(hero, TalismanOfForesight.HeapAwareness.class, 10 ).pos = t.pos;
                shieldCount++;
            }
        }
        Buff.affect(hero,Barrier.class).setShield(shieldCount*(4+hero.pointsInTalent(Talent.HOSTILITY_AlERT)));
        Dungeon.observe();
        Dungeon.hero.checkVisibleMobs();
        GameScene.updateFog();

        hero.sprite.zap(target);
        hero.spendAndNext(Actor.TICK);
        armor.charge -= chargeUse(hero);
        armor.updateQuickslot();
        Sample.INSTANCE.play(Assets.Sounds.SCAN);
        if (noticed) Sample.INSTANCE.play(Assets.Sounds.SECRET);
    }

    @Override
    public int icon() {
        return HeroIcon.ALERT_SCAN;
    }

    @Override
    public Talent[] talents() {
        return new Talent[]{Talent.HOSTILITY_AlERT,Talent.SCAN_RADIUS,Talent.WEAKNESS_INSIGHT,Talent.HEROIC_ENERGY};
    }

}
