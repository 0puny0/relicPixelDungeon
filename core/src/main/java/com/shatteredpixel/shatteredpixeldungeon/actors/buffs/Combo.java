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
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredpixel.shatteredpixeldungeon.items.Dewdrop;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Door;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndCombo;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Image;
import com.watabou.noosa.Visual;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;

public class Combo extends Buff implements ActionIndicator.Action {

	{
		type = buffType.POSITIVE;
	}

	private int count = 0;//连击数
	private float comboTime = 0f;//消退时间
	public boolean isFinish=false;//是否是终结技
	public int couldUseTime=0;//允许使用次数

	@Override
	public int icon() {
		return BuffIndicator.COMBO;
	}
	//buff图标颜色
	@Override
	public void tintIcon(Image icon) {
			if (count >= 9)    icon.hardlight(1f, 0f, 0f);
		else if (count >= 7)icon.hardlight(1f, 0.8f, 0f);
		else if (count >= 5)icon.hardlight(1f, 1f, 0f);
		else if (count >= 3)icon.hardlight(0.8f, 1f, 0f);
		else if (count >= 1)icon.hardlight(0f, 1f, 0f);
		else                icon.resetColor();
	}

	@Override
	public float iconFadePercent() {
		float time;
		int point=Dungeon.hero.pointsInTalent(Talent.KEEP_VIGILANCE);
		switch (point){
			case 1:
				time=6;
				break;
			case 2:
				time=15;
				break;
			case 3:
				time=999;
				break;
			default:
				time=4;
				break;
		}
		return Math.max(0, (time- comboTime)/time);
	}

	@Override
	public String iconTextDisplay() {
		return Integer.toString((int)comboTime);
	}

	public void hit( Char enemy ) {
		count++;
		if(count%2==1)couldUseTime++;
		int point=Dungeon.hero.pointsInTalent(Talent.KEEP_VIGILANCE);
		switch (point){
			case 1:
				comboTime=6;
				break;
			case 2:
				comboTime=15;
				break;
			case 3:
				comboTime=999;
				break;
			default:
				comboTime=4;
				break;
		}

		if (count >0&&couldUseTime>0) {

			ActionIndicator.setAction( this );
			Badges.validateMasteryCombo( count );

			GLog.p( Messages.get(this, "combo", count) );

		}

		BuffIndicator.refreshHero(); //击中时刷新可视buff。 refresh the buff visually on-hit

	}

	public void addTime( float time ){
		comboTime += time;
	}

	@Override
	public void detach() {
		super.detach();
		ActionIndicator.clearAction(this);
	}

	@Override
	public boolean act() {
		comboTime-=TICK;
		spend(TICK);
		if (comboTime <= 0) {
			detach();
		}
		return true;
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", count, dispTurns(comboTime));
	}

	private boolean slapUsed = false;
	private boolean parryUsed = false;
	private boolean cleaveUsed = false;
	private boolean furyUsed = false;
	private boolean spurtUsed = false;


	private static final String SLAP_USED	 = "slap_used";
	private static final String PARRY_USED   = "parry_used";
	private static final String CLEAVE_USED   = "cleave_used";
	private static final String FURY_USED   = "fury_used";
	private static final String SPURT_USED = "spurt_used";
	private static final String COUNT = "count";
	private static final String TIME  = "combotime";
	private static final String ISFINISH  = "isfinish";
	private static final String COULDUSETIME  = "couldusetime";


	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(COUNT, count);
		bundle.put(TIME, comboTime);
		bundle.put(ISFINISH, isFinish);
		bundle.put(COULDUSETIME, couldUseTime);

		bundle.put(SLAP_USED, slapUsed);
		bundle.put(PARRY_USED, parryUsed);
		bundle.put(CLEAVE_USED, cleaveUsed);
		bundle.put(FURY_USED, furyUsed);
		bundle.put(SPURT_USED, spurtUsed);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		count = bundle.getInt( COUNT );
		comboTime = bundle.getFloat( TIME );
		isFinish = bundle.getBoolean( ISFINISH );
		couldUseTime = bundle.getInt( COULDUSETIME );

		slapUsed = bundle.getBoolean(SLAP_USED);
		parryUsed = bundle.getBoolean(PARRY_USED);
		cleaveUsed= bundle.getBoolean(CLEAVE_USED);
		furyUsed = bundle.getBoolean(FURY_USED);
		spurtUsed = bundle.getBoolean(SPURT_USED);
		if (count >= 2) ActionIndicator.setAction(this);
	}

	@Override
	public String actionName() {
		return Messages.get(this, "action_name");
	}

	@Override
	public int actionIcon() {
		return HeroIcon.COMBO;
	}
	@Override
	public Visual secondaryVisual() {
		BitmapText txt = new BitmapText(PixelScene.pixelFont);
		txt.text( Integer.toString(count) );
		txt.hardlight(CharSprite.POSITIVE);
		txt.measure();
		return txt;
	}

	@Override
	public int indicatorColor() {

		if (count >= 9)    return 0xFF0000;
		else if (count >= 7)return 0xFFCC00;
		else if (count >= 5)return 0xFFFF00;
		else if (count >= 3)return 0xCCFF00;
		else if (count >= 1)return 0x00FF00;
		else return 0xDFDFDF;
	}

	@Override
	public void doAction() {
		GameScene.show(new WndCombo(this));
	}

	public enum ComboMove {
		SLAP , PARRY,  CLEAVE,FURY, SPURT,FINISH,CALM;
		public String desc(int count,boolean isFinish){
			String key=isFinish?".finish_desc":".desc";
			switch (this){
				case SLAP:
					return Messages.get(this, name()+key, count/2);
				case PARRY:
					return Messages.get(this, name()+key, count*5);
				case CLEAVE:
					return Messages.get(this, name()+key, count*15);
				case FURY:
					return Messages.get(this, name()+key, count);
				case SPURT:
					return Messages.get(this, name()+key, count/2,count/3);
				case FINISH:
					return Messages.get(this, name()+key);
				case CALM:
					return Messages.get(this, name()+key,count);
				default:
					return Messages.get(this, name()+key);
			}
		}
		public String title(){
			return Messages.get(this, name() + ".name");
		}
	}

	public int getComboCount(){
		return count;
	}
	public boolean canUseMove(ComboMove move){
		if (move == ComboMove.SLAP && slapUsed)   return false;
		if (move == ComboMove.PARRY && parryUsed)  return false;
		if (move == ComboMove.CLEAVE && cleaveUsed)  return false;
		if (move == ComboMove.FURY && furyUsed)  return false;
		if (move == ComboMove.SPURT && spurtUsed)  return false;
		if (move == ComboMove.FINISH){
			if(count<7&&Dungeon.hero.pointsInTalent(Talent.VENT_NOPLACE)<2){
				return false;
			}else if (count<5&&Dungeon.hero.pointsInTalent(Talent.VENT_NOPLACE)>1){
				return  false;
			}

		}
		return true;
	}
	public void useMove(ComboMove move){
		if (move == ComboMove.PARRY){
			parryUsed = true;
			comboTime = 5f;
			Invisibility.dispel();
			Buff.affect(target, ParryTracker.class, Actor.TICK);
			((Hero)target).spendAndNext(TICK);
			Dungeon.hero.busy();
		} else if(move == ComboMove.FINISH){
			isFinish=true;
			if (Dungeon.hero.hasTalent(Talent.VENT_NOPLACE)){
				count+=(Dungeon.hero.pointsInTalent(Talent.VENT_NOPLACE)+1)/2*2;
			}
			GameScene.show(new WndCombo(this));
		}else if (move==ComboMove.CALM){
			int heal =Math.round(count*0.01f* (target.HT-target.HP));
			PotionOfHealing.cure(target);
			Buff.affect(target, Healing.class).setHeal(heal, 0, 1);
			Dungeon.hero.sprite.operate(Dungeon.hero.pos);
			detach();
			ActionIndicator.clearAction(Combo.this);
			((Hero)target).spendAndNext(TICK);
		}else {
			moveBeingUsed = move;
			GameScene.selectCell(listener);
		}
	}
	public void resetCombo(){
		slapUsed = false;
		parryUsed = false;
		cleaveUsed = false;
		furyUsed = false;
		spurtUsed = false;
		isFinish=false;
		couldUseTime=0;
	}
	public static class ParryTracker extends FlavourBuff{
		{ actPriority = HERO_PRIO+1;}

		public  boolean isFinish;
		public void parry(){
			if(!isFinish){
				detach();
			}
		}
	}
	public static class RiposteTracker extends Buff{
		{ actPriority = VFX_PRIO;}
		public Char enemy;
		@Override
		public boolean act() {
			if (target.buff(Combo.class) != null) {
				moveBeingUsed = ComboMove.PARRY;
				target.sprite.attack(enemy.pos, new Callback() {
					@Override
					public void call() {
						target.buff(Combo.class).doAttack(enemy);
						next();
					}
				});
				detach();
				return false;
			} else {
				detach();
				return true;
			}
		}
	}
	private static ComboMove moveBeingUsed;
	private void doAttack(final Char enemy) {
		AttackIndicator.target(enemy);

		boolean wasAlly = enemy.alignment == target.alignment;
		Hero hero = (Hero) target;

		float dmgMulti = 1f;
		int dmgBonus = 0;

		//variance in damage dealt
		switch (moveBeingUsed) {
			case SLAP:
				dmgMulti = 0;
				break;
			case PARRY:
				dmgMulti = isFinish?(1+0.05f*count):1f;
				break;
			case CLEAVE:
				dmgMulti = isFinish?0.15f*count:1f;
				break;
			case FURY:
				dmgMulti = 0.6f;
				break;
			case SPURT:

				break;
		}
		int oldPos= enemy.pos;
		if (hero.attack(enemy, dmgMulti, dmgBonus, Char.INFINITE_ACCURACY)){
			//special on-hit effects
			switch (moveBeingUsed) {
				case SLAP:
					//trace a ballistica to our target (which will also extend past them
					Ballistica trajectory = new Ballistica(target.pos, enemy.pos, Ballistica.STOP_TARGET);
					//trim it to just be the part that goes past them
					trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size() - 1), Ballistica.PROJECTILE);
					//knock them back along that ballistica, ensuring they don't fall into a pit
					int dist = 2;
					if (isFinish) {
						dist=count/2;
					} else if (!enemy.flying) {
						while (dist > trajectory.dist ||
								(dist > 0 && Dungeon.level.pit[trajectory.path.get(dist)])) {
							dist--;
						}
					}
					if (enemy.pos == oldPos) {
						WandOfBlastWave.throwChar(enemy, trajectory, dist, true, true, hero.getClass());
					}
					break;
				default:
					hit(enemy);
					break;
			}
		}
		Invisibility.dispel();
		//Post-attack behaviour
		if (moveBeingUsed==ComboMove.FURY){
			furyUsed=true;
			if(isFinish) {
				count--;
				if (count > 0 && enemy.isAlive() && hero.canAttack(enemy) &&
						(wasAlly || enemy.alignment != target.alignment)){
					target.sprite.attack(enemy.pos, new Callback() {
						@Override
						public void call() {
							doAttack(enemy);
						}
					});
				} else {
					detach();
					Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
					ActionIndicator.clearAction(Combo.this);
					hero.spendAndNext(hero.attackDelay());
				}
			}else {
				if (enemy.isAlive() && hero.canAttack(enemy)
						&& (wasAlly || enemy.alignment != target.alignment)) {
					target.sprite.attack(enemy.pos, new Callback() {
						@Override
						public void call() {
							hit(enemy);
							hero.attack(enemy, 0.6f, 0, Char.INFINITE_ACCURACY);
						}
					});
				}
				couldUseTime--;
				if (couldUseTime<=0)ActionIndicator.clearAction(Combo.this);
				hero.spendAndNext(hero.attackDelay());
			}
		}else {
			if(isFinish){
				if(moveBeingUsed==ComboMove.CLEAVE
						&&!enemy.isAlive() || (!wasAlly && enemy.alignment == target.alignment)){
					Combo.this.resetCombo();
					count=count/2-1;
					hit(null);
					couldUseTime=(count+1)/2;
					hero.spendAndNext(hero.attackDelay());
				}else {
						detach();
						ActionIndicator.clearAction(Combo.this);
						hero.spendAndNext(hero.attackDelay());
				}

			}else {
				switch(moveBeingUsed){
					case SLAP:
						slapUsed = true;
						hero.spendAndNext(hero.attackDelay());
						break;
					case CLEAVE:
						cleaveUsed=true;
						if(!enemy.isAlive() || (!wasAlly && enemy.alignment == target.alignment)){
							hit(enemy);hit(enemy);
						}
						hero.spendAndNext(hero.attackDelay());
						break;
					case SPURT:
						spurtUsed =true;
						hero.spendAndNext(hero.attackDelay());
						break;

				}
				couldUseTime--;
				if (couldUseTime<=0)ActionIndicator.clearAction(Combo.this);
			}
		}
		if (!enemy.isAlive() || (!wasAlly && enemy.alignment == target.alignment)) {
			if (hero.hasTalent(Talent.LETHAL_DEFENSE) && hero.buff(BrokenSeal.WarriorShield.class) != null){
				BrokenSeal.WarriorShield shield = hero.buff(BrokenSeal.WarriorShield.class);
				shield.supercharge(Math.round(shield.maxShield() * hero.pointsInTalent(Talent.LETHAL_DEFENSE)/3f));
			}
		}
	}
	private CellSelector.Listener listener = new CellSelector.Listener() {

		@Override
		public void onSelect(Integer cell) {
			if (cell == null||cell==-1) return;
			final Char enemy = Actor.findChar( cell );
			final Hero hero=(Hero) target;
			boolean couldAttak=(enemy != null && enemy != hero
					&&Dungeon.level.heroFOV[cell] &&!hero.isCharmedBy( enemy ));

			if (moveBeingUsed==ComboMove.SPURT){
				int range = 4;
				if (isFinish)range=count/2;
				if (hero.rooted){
					Camera.main.shake(1, 1f);
					return;
				}

				if (Dungeon.level.distance(hero.pos, cell) > range){
					GLog.w(Messages.get(MeleeWeapon.class, "bad_target"));
					return;
				}

				Ballistica dash = new Ballistica(hero.pos, cell, Ballistica.PROJECTILE);

				if (!dash.collisionPos.equals(cell)
						|| Actor.findChar(cell) != null
						|| (Dungeon.level.solid[cell] && !Dungeon.level.passable[cell])){
					GLog.w(Messages.get(MeleeWeapon.class, "bad_target"));
					return;
				}

				hero.busy();
				Sample.INSTANCE.play(Assets.Sounds.MISS);
				float distance = Math.max( 1f, Dungeon.level.trueDistance( hero.pos, cell ));
				hero.sprite.emitter().start(Speck.factory(Speck.JET), 0.01f, Math.round(1 +distance));
				hero.sprite.jump(hero.pos, cell, 2f*distance, 0.07f*distance, new Callback() {
					@Override
					public void call() {
						if (Dungeon.level.map[hero.pos] == Terrain.OPEN_DOOR) {
							Door.leave( hero.pos );
						}
						hero.pos = cell;
						Dungeon.level.occupyCell(hero);
						hero.spendAndNext(TICK);
					}
				});
				if (isFinish){
					Buff.affect(hero, Adrenaline.class,count/3);
					detach();
					ActionIndicator.clearAction(Combo.this);
				}else {
					spurtUsed =true;
					couldUseTime--;
					if (couldUseTime<=0)ActionIndicator.clearAction(Combo.this);
				}
			}else {
				if(couldAttak&&hero.canAttack(enemy)){
					hero.busy();
					hero.sprite.attack(cell, new Callback() {
						@Override
						public void call() {
							doAttack(enemy);
						}
					});
				}else {
					GLog.w(Messages.get(Combo.class, "bad_target"));
				}
			}

		}

		@Override
		public String prompt() {
			return Messages.get(Combo.class, "prompt");
		}
	};
}
	/*
	private void doVolley(final Char enemy){

		Hero hero = (Hero) target;
		ArrayList<MissileWeapon> missiles = hero.belongings.getAllItems(MissileWeapon.class);
		ArrayList<MissileWeapon> list = new ArrayList<>(missiles);
		for(MissileWeapon m :list){
			for (int i=1;i<m.quantity();i++){
				missiles.add(m);
			}
		}
		hero.busy();
		Random.shuffle(missiles);
		Buff.affect(hero, VolleyTracker.class, 0f);
		castMissile(missiles,hero,enemy);
		if (hero.hasTalent(Talent.LETHAL_DEFENSE) && hero.buff(BrokenSeal.WarriorShield.class) != null) {
			BrokenSeal.WarriorShield shield = hero.buff(BrokenSeal.WarriorShield.class);
			shield.recoverShield(Math.round(shield.maxShield() * hero.pointsInTalent(Talent.LETHAL_DEFENSE) / 3f));
		}
	}
	public static class VolleyTracker extends FlavourBuff{};
	Actor volleyActor = null;
	private void castMissile( ArrayList<MissileWeapon> missiles, Hero hero, Char enemy){
		MissileWeapon cur = missiles.remove(0);
		hero.sprite.zap(enemy.pos);
		hero.busy();
		float startTime = Game.timeTotal;
		((MissileSprite) hero.sprite.parent.recycle(MissileSprite.class)).
				reset(hero.sprite,
						enemy.sprite,
						cur,
						new Callback() {
							@Override
							public void call() {
								cur.throwSound();
								Item i = cur.detach(hero.belongings.backpack);
								boolean wasAlly = enemy.alignment == target.alignment;
								if (i != null) i.Throw(hero,enemy.pos);
								if (Game.timeTotal - startTime < 0.33f){
									hero.sprite.parent.add(new Delayer(0.33f - (Game.timeTotal - startTime)) {
										@Override
										protected void onComplete() {
											afterCast( missiles, hero, enemy,wasAlly);
										}
									});
								} else {
									afterCast(missiles, hero, enemy,wasAlly);
								}
							}
						});
	}
	private void afterCast(  ArrayList<MissileWeapon> missiles, Hero hero, Char enemy,boolean wasAlly){
		if (volleyActor != null){
			volleyActor.next();
			volleyActor = null;
		}
		count--;
		boolean enemyDie=!enemy.isAlive() || (!wasAlly && enemy.alignment == target.alignment);
		if (!missiles.isEmpty() &&!enemyDie&&hero.isAlive()&&count!=0) {
			Actor.add(new Actor() {
				{
					actPriority = VFX_PRIO-1;
				}

				@Override
				protected boolean act() {
					volleyActor = this;
					castMissile(missiles, hero, enemy);
					Actor.remove(this);
					return false;
				}
			});
			hero.next();
		} else {
			if (hero.buff(WildMagic.WildMagicTracker.class) != null) {
				hero.buff(WildMagic.WildMagicTracker.class).detach();
			}
			detach();
			ActionIndicator.clearAction(Combo.this);
			hero.spendAndNext(1f/ RingOfFuror.attackSpeedMultiplier(hero));
			Item.updateQuickslot();
		}
	}

	 */
