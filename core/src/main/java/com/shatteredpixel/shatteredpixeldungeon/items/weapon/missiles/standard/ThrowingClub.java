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

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.standard;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Bundle;

public class ThrowingClub extends MissileWeapon {
	
	{
		image = ItemSpriteSheet.THROWING_CLUB2;
		hitSound = Assets.Sounds.HIT_CRUSH;
		hitSoundPitch = 1f;
		baseUses = 15;
		sticky = false;
	}
	private int image_T2=ItemSpriteSheet.THROWING_CLUB2;
	private int image_T3=ItemSpriteSheet.THROWING_CLUB3;
	private int image_T4=ItemSpriteSheet.THROWING_CLUB4;
	private int image_T5=ItemSpriteSheet.THROWING_CLUB5;

	@Override
	public void setTier(int t) {
		super.setTier(t);
		matchImage(t);
	}

	@Override
	public int max(int lvl) {
		return  5 * tier +                  //8 base, down from 10
				(tier) * lvl;               //scaling unchanged
	}
	private void matchImage(int tier){
		switch (tier){
			case 2:
				image=image_T2;
				break;
			case 3:
				image=image_T3;
				break;
			case 4:
				image=image_T4;
				break;
			case 5:
				image=image_T5;
				break;
		}
	}
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		matchImage(tier);
	}
}
