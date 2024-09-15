package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.initial;
import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Door;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;

public class Rapier extends MeleeWeapon  {
    {
        image = ItemSpriteSheet.RAPIER;
        hitSound = Assets.Sounds.HIT_STAB;
        hitSoundPitch=1.15f;
        DMG=Attribute.lower;
        ASPD =Attribute.higher;
        hasSkill=true;
        defaultAction=AC_WEAPONSKILL;
        usesTargeting=true;
    }
    //蓄势攻击使用重击音效
    @Override
    public void execute(Hero hero, String action) {

        super.execute(hero, action);
        if (action.equals(AC_WEAPONSKILL)) {
            if (!isEquipped(hero)){
                GLog.i( Messages.get(MeleeWeapon.class, "need_to_equip") );
                return;
            }
            GameScene.selectCell( new CellSelector.Listener() {
                @Override
                public void onSelect(Integer cell) {
                    if (cell != null) {
                        onLunge(hero, cell);
                    }
                }
                @Override
                public String prompt() {
                    return Messages.get(Rapier.class,"target");
                }
            } );
        }
    }

    public  void onLunge(Hero hero, Integer target){
        if (target == null){
            return;
        }

        Char enemy = Actor.findChar(target);
        //duelist can lunge out of her FOV, but this wastes the ability instead of cancelling if there is no target
        if (Dungeon.level.heroFOV[target]) {
            if (enemy == null || enemy == hero || hero.isCharmedBy(enemy)) {
                GLog.w(Messages.get(this, "no_target"));
                return;
            }
        }

        if (hero.rooted || Dungeon.level.distance(hero.pos, target) < 2
                || Dungeon.level.distance(hero.pos, target)-1 > reachFactor(hero)){
            GLog.w(Messages.get(this, "bad_pos"));
            if (hero.rooted)  Camera.main.shake(1, 1f);
            return;
        }

        int lungeCell = -1;
        for (int i : PathFinder.NEIGHBOURS8){
            if (Dungeon.level.distance(hero.pos+i, target) <= reachFactor(hero)
                    && Actor.findChar(hero.pos+i) == null
                    && (Dungeon.level.passable[hero.pos+i] || (Dungeon.level.avoid[hero.pos+i] && hero.flying))){
                if (lungeCell == -1 || Dungeon.level.trueDistance(hero.pos + i, target) < Dungeon.level.trueDistance(lungeCell, target)){
                    lungeCell = hero.pos + i;
                }
            }
        }

        if (lungeCell == -1){
            GLog.w(Messages.get(this, "bad_pos"));
            return;
        }

        final int dest = lungeCell;
        hero.busy();
        Sample.INSTANCE.play(Assets.Sounds.MISS);
        hero.sprite.jump(hero.pos, dest, 0, 0.1f, new Callback() {
            @Override
            public void call() {
                if (Dungeon.level.map[hero.pos] == Terrain.OPEN_DOOR) {
                    Door.leave( hero.pos );
                }
                hero.pos = dest;
                Dungeon.level.occupyCell(hero);
                Dungeon.observe();

                if (enemy != null && hero.canAttack(enemy)) {
                    hero.sprite.attack(enemy.pos, new Callback() {
                        @Override
                        public void call() {

                            AttackIndicator.target(enemy);
                            if (hero.attack(enemy,1f,0,Char.INFINITE_ACCURACY)) {
                                Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
                            }
                            Invisibility.dispel();
                            hero.spendAndNext(hero.attackDelay());
                        }
                    });
                } else {
                    GLog.w(Messages.get(Rapier.class, "no_target"));
                    hero.spendAndNext(hero.speed());
                }
            }
        });

    }
}
/*
    int moveDist = 1;
        if (Dungeon.level.distance(hero.pos, target) > moveDist+reachFactor(hero)){
                GLog.w(Messages.get(this, "bad_pos"));
                return;
                }
                Ballistica dash = new Ballistica(hero.pos, target, Ballistica.PROJECTILE|Ballistica.IGNORE_SOFT_SOLID);
                Integer nextPos=null;
                if (dash.path.indexOf(dash.collisionPos) - reachFactor(hero)>0){
                nextPos=dash.path.get(dash.path.indexOf(dash.collisionPos) - reachFactor(hero));
                }else {
                nextPos=dash.path.get(0);
                }
                Char enemy = Actor.findChar(dash.collisionPos);
                if ((dash.collisionPos).intValue()!=target.intValue()){
                GLog.w(Messages.get(this, "no_target"));
                return;
                }
                if (Dungeon.level.heroFOV[target]) {
                if (enemy == null || enemy == hero || hero.isCharmedBy(enemy)) {
                GLog.w(Messages.get(this, "no_target"));
                return;
                }
                }
                if (!Dungeon.level.passable[nextPos] || (Dungeon.level.avoid[nextPos] && hero.flying)){
                GLog.w(Messages.get(this, "bad_pos"));
                return;
                }
                if (hero.rooted){
                Camera.main.shake(1, 1f);
                return;
                }
final int dest = nextPos;
        hero.busy();
        Sample.INSTANCE.play(Assets.Sounds.MISS);
        hero.sprite.jump(hero.pos, dest, 0, 0.1f, new Callback() {
@Override
public void call() {
        if (Dungeon.level.map[hero.pos] == Terrain.OPEN_DOOR) {
        Door.leave( hero.pos );
        }
        hero.pos = dest;
        Dungeon.level.occupyCell(hero);
        Dungeon.observe();
        DMG=Attribute.common;
        if (enemy != null && hero.canAttack(enemy)) {
        hero.sprite.attack(enemy.pos, new Callback() {
@Override
public void call() {

        AttackIndicator.target(enemy);
        if (hero.attack(enemy)) {
        Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
        }
        Invisibility.dispel();
        hero.spendAndNext(hero.speed());
        }
        });
        }else {
        hero.spendAndNext(hero.speed());
        }
        DMG=Attribute.lower;
        }
        });

 */