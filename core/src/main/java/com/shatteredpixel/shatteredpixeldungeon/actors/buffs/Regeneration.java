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

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.ChaliceOfBlood;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEnergy;
import com.watabou.utils.Bundle;

public class Regeneration extends Buff {
	
	{
		//unlike other buffs, this one acts after the hero and takes priority against other effects
		//healing is much more useful if you get some of it off before taking damage
		actPriority = HERO_PRIO - 1;
	}
	
	private static final float REGENERATION_DELAY = 10;
	private float partialRegen;
	private static final String PARTIALREGEN 	= "partialRegen";
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		bundle.put( PARTIALREGEN, partialRegen );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		partialRegen = bundle.getFloat(PARTIALREGEN);
	}
	@Override
	public boolean act() {
		if (target.isAlive()) {

			if (target.HP < regencap() && !((Hero)target).toHungry()) {
				LockedFloor lock = target.buff(LockedFloor.class);
				if (lock == null || lock.regenOn()) {
					partialRegen+=target.HT/200f;
					if(partialRegen> 1){
						target.HP+=(int)partialRegen;
						partialRegen -= (int)partialRegen;
					}
					target.HP += 1;
					if (target.HP == regencap()) {
						((Hero) target).resting = false;
					}
				}
			}

			ChaliceOfBlood.chaliceRegen regenBuff = Dungeon.hero.buff( ChaliceOfBlood.chaliceRegen.class);

			float delay = REGENERATION_DELAY;
			if (regenBuff != null && target.buff(MagicImmune.class) == null) {
				if (regenBuff.isCursed()) {
					delay *= 1.5f;
				} else {
					delay -= regenBuff.itemLevel()*0.9f;
					delay /= RingOfEnergy.artifactChargeMultiplier(target);
				}
			}
			if(((Hero)target).toWellfed()){
				delay*=0.67f;
			}
			spend( delay );
			
		} else {
			
			diactivate();
			
		}
		
		return true;
	}
	
	public int regencap(){
		return target.HT;
	}
}
