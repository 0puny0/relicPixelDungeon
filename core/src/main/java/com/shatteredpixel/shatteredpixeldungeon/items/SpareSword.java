/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfForceOut;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

import java.util.ArrayList;

public class SpareSword extends MeleeWeapon {

    {
        image = ItemSpriteSheet.SPARE_SWORD;
        hitSound = Assets.Sounds.HIT_STAB;
        hitSoundPitch=0.9f;
        cursedKnown = true;
        unique = true;
        ACC=Attribute.higher;
        bones = false;
    }

    @Override
    public int max(int lvl) {
        float dmg =6 + buffedLvl()*3;
        return Math.max(0, (int)dmg);
    }

    @Override
    public int min(int lvl) {
        int dmg;
        dmg = 1 + buffedLvl();
        return dmg;
    }

    @Override
    public int level() {
        int level=super.level();
        level+= Dungeon.hero == null ? 0 : Dungeon.hero.lvl/5;
        if (curseInfusionBonus) level += 1 + level/6;
        return level;
    }
    @Override
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions(hero);
        actions.remove(AC_EQUIP);
        return actions;
    }

    @Override
    public String info() {
        String info = desc();
        info += "\n\n" + Messages.get(this, "stats_known", tier, min(), max(),STRReq());
        int diffStr=Dungeon.hero.STR()-STRReq();
        if (diffStr<0) {
            info +=Messages.get(MeleeWeapon.class, "too_heavy");
        } else if (diffStr>0){
            info += Messages.get(MeleeWeapon.class, "excess_str", ASPD ==Attribute.lowest?(int)(diffStr* RingOfForceOut.halfExStrBonus(Dungeon.hero)):
                    (int)(diffStr * RingOfForceOut.extraStrengthBonus(Dungeon.hero )));
        }else if(diffStr==0){
            info +=Messages.get(MeleeWeapon.class, "just_right");
        }
        info += "\n\n" +statsInfo();
        return info;
    }
    @Override
    public int STRReq(int lvl) {
        return STRReq(1, lvl); //tier 1
    }
    @Override
    public float delayFactor(Char owner) {
        return 0;
    }

    @Override
    public boolean isIdentified() {
        return true;
    }



}
