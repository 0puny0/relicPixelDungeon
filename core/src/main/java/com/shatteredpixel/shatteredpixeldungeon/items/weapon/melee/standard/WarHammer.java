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

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Aim;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class WarHammer extends MeleeWeapon {

	{
		image = ItemSpriteSheet.WAR_HAMMER;
		hitSound = Assets.Sounds.HIT_CRUSH;
		hitSoundPitch=0.85f;
        DMG=Attribute.highest;
		ASPD =Attribute.lowest;
	}


	@Override
	public int proc(Char attacker, Char defender, int damage) {
		if(attacker.buff(Aim.class)!=null) {
			Buff.affect(defender, Paralysis.class,1f);
			ArrayList<Char> targets = new ArrayList<>();
			for (int i : PathFinder.NEIGHBOURS8){
				if (Actor.findChar(defender.pos + i) != null) targets.add(Actor.findChar(defender.pos + i));
			}
			WandOfBlastWave.BlastWave.blast(defender.pos);
			for (Char ch : targets) {
				if ( ch.alignment == Char.Alignment.ENEMY) {
					Buff.affect(ch, Paralysis.class,1f);
				}
			}
		}
		return super.proc(attacker, defender, damage);
	}
}
