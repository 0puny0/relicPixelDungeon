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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.Ratmogrify;
import com.shatteredpixel.shatteredpixeldungeon.items.food.MysteryMeat;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Rat extends Mob {

	{
		spriteClass = RatSprite.class;

		HP=HT=10;
		minDMG=1;
		maxDMG=4;
		minDR=0;
		maxDR=1;
		defenseSkill =2;
		attackSkill = 8;
		maxLvl = 5;
	}

	@Override
	protected boolean act() {
		if (Dungeon.level.heroFOV[pos] && Dungeon.hero.armorAbility instanceof Ratmogrify){
			alignment = Alignment.ALLY;
			if (state == SLEEPING) state = WANDERING;
		}
		return super.act();
	}


	@Override
	public void rollToDropLoot() {
		Hero hero= Dungeon.hero;
		if(hero.hasTalent(Talent.ANATOMY_EXPERT)){
			int meatAvailable = 1 + 2*hero.pointsInTalent(Talent.ANATOMY_EXPERT);
			Talent.AutopsyMeatDropped dropped = Buff.affect(hero, Talent.AutopsyMeatDropped.class);
			meatAvailable -= dropped.count();
			if(meatAvailable>0){
				dropped.countUp(1);
				Dungeon.level.drop(new MysteryMeat(), pos).sprite.drop();
			}
		}
		super.rollToDropLoot();
	}

	private static final String RAT_ALLY = "rat_ally";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		if (alignment == Alignment.ALLY) bundle.put(RAT_ALLY, true);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		if (bundle.contains(RAT_ALLY)) alignment = Alignment.ALLY;
	}
}
