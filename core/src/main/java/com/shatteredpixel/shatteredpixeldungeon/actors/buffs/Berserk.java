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

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal.WarriorShield;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

import java.text.DecimalFormat;

public class Berserk extends Buff {

	private enum State{
		NORMAL, BERSERK,RECOVERING
	}
	float damageStore=0;
	public void attackDamage(){
		damageStore+=target.HP*0.03f;
		int dmg=0;
		if (damageStore>1){
			dmg+=(int) damageStore;
			damageStore%=1;
		}
		if (dmg>0){
			target.damage(dmg,this);
		}

		WarriorShield buff = target.buff(WarriorShield.class);
		if (Dungeon.hero.hasTalent(Talent.ENDLESS_RAGE)&&buff!=null){
			buff.recoverShield(dmg*(0.1f+Dungeon.hero.pointsInTalent(Talent.ENDLESS_RAGE)*0.33f));
		}
	}


	private State state = State.NORMAL;

	private static final float LEVEL_RECOVER_START = 4f;
	private float levelRecovery;

	private static final String STATE = "state";
	private static final String LEVEL_RECOVERY = "levelrecovery";
	private static final String DAMAGESTORE="damagestore";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(STATE, state);
		bundle.put(DAMAGESTORE,damageStore);
		 bundle.put(LEVEL_RECOVERY, levelRecovery);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);

		state = bundle.getEnum(STATE, State.class);
		damageStore = bundle.getFloat(DAMAGESTORE);
		 levelRecovery = bundle.getFloat(LEVEL_RECOVERY);
	}

	@Override
	public boolean act() {
		if (state == State.BERSERK){
			ShieldBuff buff = target.buff(WarriorShield.class);
			if (target.shielding() > 0) {
				//lose 2.5% of shielding per turn, but no less than 1
				int dmg = (int)Math.ceil(target.shielding() * 0.025f);
				if (buff != null && buff.shielding() > 0) {
					dmg = buff.absorbDamage(dmg);
				}

				if (dmg > 0){
					//if there is leftover damage, then try to remove from other shielding buffs
					for (ShieldBuff s : target.buffs(ShieldBuff.class)){
						dmg = s.absorbDamage(dmg);
						if (dmg == 0) break;
					}
				}

				if (target.shielding() <= 0){
					state = State.RECOVERING;
					BuffIndicator.refreshHero();
					if (!target.isAlive()){
						target.die(this);
						if (!target.isAlive()) Dungeon.fail(this.getClass());
					}
				}

			} else {
				state = State.RECOVERING;
				if (!target.isAlive()){
					target.die(this);
					if (!target.isAlive()) Dungeon.fail(this.getClass());
				}

			}
		}
		spend(TICK);
		return true;
	}


	public boolean berserking(){
		if (target.HP == 0
				&& state == State.NORMAL
				&& target.buff(WarriorShield.class) != null
				&& ((Hero)target).hasTalent(Talent.DEATHLESS_FURY)){
			state = State.BERSERK;
			SpellSprite.show(target, SpellSprite.BERSERK);
			Sample.INSTANCE.play( Assets.Sounds.CHALLENGE );
			GameScene.flash(0xFF0000);

			if (target.HP > 0) {
				levelRecovery = 0;
			} else {
				levelRecovery = LEVEL_RECOVER_START - ((Hero)target).pointsInTalent(Talent.DEATHLESS_FURY);
			}
			WarriorShield shield = target.buff(WarriorShield.class);
			int shieldAmount = Math.round(shield.maxShield() * 6f);
			shield.supercharge(shieldAmount);

			BuffIndicator.refreshHero();
		}

		return state == State.BERSERK && target.shielding() > 0;

	}

	public void recover(float percent){
		if (state == State.RECOVERING &&levelRecovery > 0){
			levelRecovery -= percent;
			if (levelRecovery <= 0) {
				state = State.NORMAL;
				levelRecovery = 0;
			}
		}
	}

	@Override
	public int icon() {
		if (state!=State.NORMAL) return BuffIndicator.BERSERK;
		return super.icon();
	}
	@Override
	public void tintIcon(Image icon) {
		if (state==State.RECOVERING) {
			icon.hardlight(0, 0, 1f);
		}else {
			icon.hardlight(1f, 0, 0);
		}
	}

	@Override
	public float iconFadePercent() {
		if (state==State.RECOVERING){
				return 1f - levelRecovery/(LEVEL_RECOVER_START-Dungeon.hero.pointsInTalent(Talent.DEATHLESS_FURY));
		}
		return super.iconFadePercent();
	}


	@Override
	public String name() {
		if (state==State.RECOVERING){
			return Messages.get(this, "recovering");
		}else {
			return Messages.get(this, "berserk");
		}
	}

	@Override
	public String desc() {
		if (state==State.RECOVERING){
			return Messages.get(this, "recovering_desc") + "\n\n" + Messages.get(this, "recovering_desc_levels",new DecimalFormat("#.##").format(levelRecovery));
		}else {
			return Messages.get(this, "berserk_desc");
		}

	}
}
