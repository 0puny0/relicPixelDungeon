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

package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.QuickSlot;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ManaBarrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.adventurer.AlertScan;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.adventurer.ElementalStrike;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.adventurer.FaithShield;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Challenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Feint;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.NaturesPower;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.SpiritHawk;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.SpectralBlades;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WildMagic;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WarpBeacon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.ElementalBlast;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.DeathMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.ShadowClone;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.SmokeBomb;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.HeroicLeap;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.Shockwave;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.Endure;
import com.shatteredpixel.shatteredpixeldungeon.custom.testmode.BackpackCleaner;
import com.shatteredpixel.shatteredpixeldungeon.custom.testmode.ImmortalShieldAffecter;
import com.shatteredpixel.shatteredpixeldungeon.custom.testmode.LevelTeleporter;
import com.shatteredpixel.shatteredpixeldungeon.custom.testmode.MobPlacer;
import com.shatteredpixel.shatteredpixeldungeon.custom.testmode.TestBag;
import com.shatteredpixel.shatteredpixeldungeon.custom.testmode.TimeReverser;
import com.shatteredpixel.shatteredpixeldungeon.custom.testmode.TrapPlacer;
import com.shatteredpixel.shatteredpixeldungeon.custom.testmode.WandOfScanningBeam;
import com.shatteredpixel.shatteredpixeldungeon.custom.testmode.generator.LazyTest;
import com.shatteredpixel.shatteredpixeldungeon.custom.testmode.generator.TestArmor;
import com.shatteredpixel.shatteredpixeldungeon.custom.testmode.generator.TestArtifact;
import com.shatteredpixel.shatteredpixeldungeon.custom.testmode.generator.TestMelee;
import com.shatteredpixel.shatteredpixeldungeon.custom.testmode.generator.TestMissile;
import com.shatteredpixel.shatteredpixeldungeon.custom.testmode.generator.TestPotion;
import com.shatteredpixel.shatteredpixeldungeon.custom.testmode.generator.TestRing;
import com.shatteredpixel.shatteredpixeldungeon.custom.testmode.generator.TestValue;
import com.shatteredpixel.shatteredpixeldungeon.items.BlessedPearl;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.SpareSword;
import com.shatteredpixel.shatteredpixeldungeon.items.Waterskin;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.PotionBandolier;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.ScrollHolder;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.VelvetPouch;
import com.shatteredpixel.shatteredpixeldungeon.items.food.BurgerLarger;
import com.shatteredpixel.shatteredpixeldungeon.items.food.BurgerLargest;
import com.shatteredpixel.shatteredpixeldungeon.items.food.BurgerSmaller;
import com.shatteredpixel.shatteredpixeldungeon.items.food.BurgerSmallest;
import com.shatteredpixel.shatteredpixeldungeon.items.food.SmallRation;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfExperience;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfInvisibility;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfMindVision;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfStrength;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTerror;
import com.shatteredpixel.shatteredpixeldungeon.items.treasure.FrostCube;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfMagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.initial.KnifeFork;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.initial.Rapier;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.initial.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.initial.Dagger;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.initial.MagesStaff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.initial.SwordShield;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.initial.ThrowingKnife;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.initial.ThrowingPlate;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.initial.ThrowingSpike;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.initial.ThrowingStone;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.watabou.utils.DeviceCompat;

public enum HeroClass {

	WARRIOR( HeroSubClass.BERSERKER, HeroSubClass.GLADIATOR ),
	MAGE( HeroSubClass.BATTLEMAGE, HeroSubClass.WARLOCK ),
	ROGUE( HeroSubClass.ASSASSIN, HeroSubClass.FREERUNNER ),
	HUNTRESS( HeroSubClass.SNIPER, HeroSubClass.WARDEN ),
	ADVENTURER(HeroSubClass.SAVIOR,HeroSubClass.SURVIVOR),
	DUELIST(HeroSubClass.SHADOWWARRIOR);


	private HeroSubClass[] subClasses;

	HeroClass( HeroSubClass...subClasses ) {
		this.subClasses = subClasses;
	}

	public void initHero( Hero hero ) {

		hero.heroClass = this;
		hero.updateHT(true);
		Talent.initClassTalents(hero);

		Item item = new ClothArmor().identify();
		if (!Challenges.isItemBlocked(item)) hero.belongings.armor = (ClothArmor)item;

		item = new SmallRation();
		if (!Challenges.isItemBlocked(item)) item.quantity(3).collect();

		new VelvetPouch().collect();
		Dungeon.LimitedDrops.VELVET_POUCH.drop();

		Waterskin waterskin = new Waterskin();
		waterskin.collect();

		new ScrollOfIdentify().identify();

		switch (this) {
			case WARRIOR:
				initWarrior( hero );
				break;

			case MAGE:
				initMage( hero );
				break;

			case ROGUE:
				initRogue( hero );
				break;

			case HUNTRESS:
				initHuntress( hero );
				break;

			case  ADVENTURER:
				initAdventurer(hero);
				break;
			case DUELIST:
				initDuelist( hero );
				break;
		}

		for (int s = 0; s < QuickSlot.SIZE; s++){
			if (Dungeon.quickslot.getItem(s) == null){
				Dungeon.quickslot.setSlot(s, waterskin);
				break;
			}
		}
		if (DeviceCompat.isDebug()){
			textTime(hero);
		}
	}

	public Badges.Badge masteryBadge() {
		switch (this) {
			case WARRIOR:
				return Badges.Badge.MASTERY_WARRIOR;
			case MAGE:
				return Badges.Badge.MASTERY_MAGE;
			case ROGUE:
				return Badges.Badge.MASTERY_ROGUE;
			case HUNTRESS:
				return Badges.Badge.MASTERY_HUNTRESS;
			case ADVENTURER:
				return Badges.Badge.MASTERY_ADVENTURER;
		}
		return null;
	}

	private static void initWarrior( Hero hero ) {

		(hero.belongings.weapon = new KnifeFork()).identify();
		ThrowingPlate plate = new ThrowingPlate();
		plate.quantity(6).collect();
		Dungeon.quickslot.setSlot(0, plate);

		if (hero.belongings.armor != null){
			hero.belongings.armor.affixSeal(new BrokenSeal());
		}

		new PotionOfHealing().identify();
		new ScrollOfRage().identify();
	}

	private static void initMage( Hero hero ) {
		MagesStaff staff;
		staff = new MagesStaff(new WandOfMagicMissile());
		(hero.belongings.weapon = staff).identify();
		hero.belongings.weapon.activate(hero);
		Buff.affect(hero, ManaBarrier.class).fillBarrier();
		Dungeon.quickslot.setSlot(0, staff);


		new ScrollOfRecharging().identify();
		new PotionOfLiquidFlame().identify();
	}

	private static void initRogue( Hero hero ) {
		(hero.belongings.weapon = new Dagger()).identify();

		CloakOfShadows cloak = new CloakOfShadows();
		(hero.belongings.artifact = cloak).identify();
		hero.belongings.artifact.activate( hero );

		ThrowingKnife knives = new ThrowingKnife();
		knives.quantity(3).collect();

		Dungeon.quickslot.setSlot(0, cloak);
		Dungeon.quickslot.setSlot(1, knives);

		new ScrollOfMagicMapping().identify();
		new PotionOfInvisibility().identify();
	}

	private static void initHuntress( Hero hero ) {
		SpiritBow bow = new SpiritBow();
		(hero.belongings.weapon = bow).identify();
		Dungeon.quickslot.setSlot(0, bow);

		new PotionOfHaste().identify();
		new ScrollOfLullaby().identify();
	}
	private static void initAdventurer( Hero hero ) {
		SwordShield sword=new SwordShield();
		(hero.belongings.weapon = sword).identify();
		ThrowingStone stones = new ThrowingStone();
		stones.quantity(3).collect();
		Dungeon.quickslot.setSlot(0, sword);
		Dungeon.quickslot.setSlot(1, stones);

		if (hero.belongings.armor != null){
			hero.belongings.armor.affixPearl(new BlessedPearl());
		}
		new PotionOfExperience().identify();
		new ScrollOfTerror().identify();
	}
	private static void initDuelist( Hero hero ) {

		(hero.belongings.weapon = new Rapier()).identify();
		hero.belongings.weapon.activate(hero);

		ThrowingSpike spikes = new ThrowingSpike();
		spikes.quantity(2).collect();
		new SpareSword().identify().collect();

		Dungeon.quickslot.setSlot(0, hero.belongings.weapon);
		Dungeon.quickslot.setSlot(1, spikes);

		new PotionOfMindVision().identify();
		new ScrollOfMirrorImage().identify();
	}
	public String title() {
		return Messages.get(HeroClass.class, name());
	}

	public String desc(){
		return Messages.get(HeroClass.class, name()+"_desc");
	}

	public String shortDesc(){
		return Messages.get(HeroClass.class, name()+"_desc_short");
	}

	public HeroSubClass[] subClasses() {
		return subClasses;
	}

	public ArmorAbility[] armorAbilities(){
		switch (this) {
			case WARRIOR: default:
				return new ArmorAbility[]{new HeroicLeap(), new Shockwave(), new Endure()};
			case MAGE:
				return new ArmorAbility[]{new ElementalBlast(), new WildMagic(), new WarpBeacon()};
			case ROGUE:
				return new ArmorAbility[]{new SmokeBomb(), new DeathMark(), new ShadowClone()};
			case HUNTRESS:
				return new ArmorAbility[]{new SpectralBlades(), new NaturesPower(), new SpiritHawk()};
			case ADVENTURER:
				return new ArmorAbility[]{new ElementalStrike(), new AlertScan(), new FaithShield()};
			case DUELIST:
				return new ArmorAbility[]{new Challenge(), new ElementalStrike(), new Feint()};
		}
	}

	public String spritesheet() {
		switch (this) {
			case WARRIOR: default:
				return Assets.Sprites.WARRIOR;
			case MAGE:
				return Assets.Sprites.MAGE;
			case ROGUE:
				return Assets.Sprites.ROGUE;
			case HUNTRESS:
				return Assets.Sprites.HUNTRESS;
			case ADVENTURER:
				return Assets.Sprites.ADVENTURER;
			case DUELIST:
				return Assets.Sprites.DUELIST;
		}
	}

	public String splashArt(){
		switch (this) {
			case WARRIOR: default:
				return Assets.Splashes.WARRIOR;
			case MAGE:
				return Assets.Splashes.MAGE;
			case ROGUE:
				return Assets.Splashes.ROGUE;
			case HUNTRESS:
				return Assets.Splashes.HUNTRESS;
			case ADVENTURER:
				return Assets.Splashes.ADVENTURER;
			case DUELIST:
				return Assets.Splashes.DUELIST;
		}
	}
	
	public boolean isUnlocked(){
		//always unlock on debug builds
		if (DeviceCompat.isDebug()) return true;
		return true;
//		switch (this){
//			case WARRIOR: default:
//				return true;
//			case MAGE:
//				return Badges.isUnlocked(Badges.Badge.UNLOCK_MAGE);
//			case ROGUE:
//				return Badges.isUnlocked(Badges.Badge.UNLOCK_ROGUE);
//			case HUNTRESS:
//				return Badges.isUnlocked(Badges.Badge.UNLOCK_HUNTRESS);
//			case DUELIST:
//				return Badges.isUnlocked(Badges.Badge.UNLOCK_DUELIST);
//		}
	}
	private static void textTime(Hero hero) {
		new ScrollHolder().collect();
		Dungeon.LimitedDrops.SCROLL_HOLDER.drop();

		new PotionBandolier().collect();
		Dungeon.LimitedDrops.POTION_BANDOLIER.drop();

		if (!Dungeon.LimitedDrops.VELVET_POUCH.dropped()) {
			new VelvetPouch().collect();
			Dungeon.LimitedDrops.VELVET_POUCH.drop();
		}
		new MagicalHolster().collect();
		Dungeon.LimitedDrops.MAGICAL_HOLSTER.drop();
		new TestBag().collect();
		//TODO 镭射激光炮
//		new WandOfScanningBeam().identify().collect();

		new TestMelee().collect();
		new TestMissile().collect();
		new TestArmor().collect();
		new TestRing().collect();
		new TestArtifact().collect();
		new TestPotion().collect();

		new TestValue().collect();

		new LevelTeleporter().collect();

		new BackpackCleaner().collect();

		new ImmortalShieldAffecter().collect();
		new TimeReverser().collect();
		new TrapPlacer().collect();

		new MobPlacer().collect();

		new LazyTest().collect();
//		new DictBook().collect();
	}

	public String unlockMsg() {
		return shortDesc() + "\n\n" + Messages.get(HeroClass.class, name()+"_unlock");
	}

}
