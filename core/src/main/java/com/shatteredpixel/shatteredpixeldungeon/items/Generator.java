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

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.AdventurerArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.HuntressArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.LeatherArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.MageArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.MailArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.PlateArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.RogueArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ScaleArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.WarriorArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CeremonialDagger;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.ChaliceOfBlood;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.ControlGlove;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.EtherealChains;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HornOfPlenty;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.MasterThievesArmband;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.SandalsOfNature;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TalismanOfForesight;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.UnstableSpellbook;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.items.food.MysteryMeat;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Pasty;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfExperience;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfInvisibility;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLevitation;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfMindVision;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfParalyticGas;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfPurity;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfStrength;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfArcana;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfElements;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEnergy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEvasion;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfForce;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfFuror;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfMight;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfSharpshooting;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfWealth;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfCommand;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTerror;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfFear;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAggression;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAugmentation;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfBlast;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfBlink;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfClairvoyance;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfDeepSleep;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfDisarming;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfEnchantment;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfFlock;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfIntuition;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfShock;
import com.shatteredpixel.shatteredpixeldungeon.items.treasure.FrostCube;
import com.shatteredpixel.shatteredpixeldungeon.items.treasure.GoldenCudgel;
import com.shatteredpixel.shatteredpixeldungeon.items.treasure.VibraniumShield;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfCorrosion;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfCorruption;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfDisintegration;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFireblast;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLightning;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfMagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfPrismaticLight;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfRegrowth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfTransfusion;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfWarding;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special.DragonFangDagger;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special.SoulDevourerScythe;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard.AssassinationBlade;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard.Crossbow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special.CursedSword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.initial.Dagger;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard.DoubleBlade;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special.DriveWhip;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special.Flail;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard.Glaive;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special.GreatAxe;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special.HideSword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard.Katana;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.initial.KnifeFork;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard.KnightLance;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special.GoldSword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.initial.MagesStaff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special.Nunchaku;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special.PiercingBlade;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special.PricklyShield;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard.LongSword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard.RedtasselledSpear;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard.GreatShield;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special.RuneSword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard.Scimitar;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.initial.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard.TekkoKagi;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard.WarHammer;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.initial.SwordShield;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts.Dart;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.initial.ThrowingPlate;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.initial.ThrowingSpike;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special.BloodNail;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special.Bolas;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special.FishingSpear;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special.ForceCube;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special.HeavyBoomerang;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special.KnifeMan;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special.Kunai;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special.RottenEgg;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special.Shuriken;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special.ThrowingNeedle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special.ThrowingSaltBag;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special.ThrowingShield;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.standard.ThrowingClub;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.initial.ThrowingKnife;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.standard.ThrowingSpear;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.initial.ThrowingStone;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special.Tomahawk;
import com.shatteredpixel.shatteredpixeldungeon.plants.Blindweed;
import com.shatteredpixel.shatteredpixeldungeon.plants.Mageroyal;
import com.shatteredpixel.shatteredpixeldungeon.plants.Earthroot;
import com.shatteredpixel.shatteredpixeldungeon.plants.Fadeleaf;
import com.shatteredpixel.shatteredpixeldungeon.plants.Firebloom;
import com.shatteredpixel.shatteredpixeldungeon.plants.Icecap;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.plants.Rotberry;
import com.shatteredpixel.shatteredpixeldungeon.plants.Sorrowmoss;
import com.shatteredpixel.shatteredpixeldungeon.plants.Starflower;
import com.shatteredpixel.shatteredpixeldungeon.plants.Stormvine;
import com.shatteredpixel.shatteredpixeldungeon.plants.Sungrass;
import com.shatteredpixel.shatteredpixeldungeon.plants.Swiftthistle;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Generator {

	public enum Category {
		WEAPON	( 2, 2, MeleeWeapon.class),
		WEP_INI( 0, 0, MeleeWeapon.class),
		WEP_STAN( 0, 0, MeleeWeapon.class),
		WEP_SPE_T2( 0, 0, MeleeWeapon.class),
		WEP_SPE_T3( 0, 0, MeleeWeapon.class),
		WEP_SPE_T4( 0, 0, MeleeWeapon.class),
		WEP_SPE_T5( 0, 0, MeleeWeapon.class),

		
		ARMOR	( 2, 1, Armor.class ),
		
		MISSILE ( 1, 2, MissileWeapon.class ),
		MIS_INI( 0, 0, MissileWeapon.class),
		MIS_STAN( 0, 0, MissileWeapon.class),
		MIS_SPE_T2( 0, 0, MissileWeapon.class),
		MIS_SPE_T3( 0, 0, MissileWeapon.class),
		MIS_SPE_T4( 0, 0, MissileWeapon.class),
		MIS_SPE_T5( 0, 0, MissileWeapon.class),
		
		WAND	( 1, 1, Wand.class ),
		RING	( 1, 0, Ring.class ),
		ARTIFACT( 0, 1, Artifact.class),

		FOOD	( 0, 0, Food.class ),
		
		POTION	( 8, 8, Potion.class ),
		SEED	( 1, 1, Plant.Seed.class ),
		
		SCROLL	( 8, 8, Scroll.class ),
		STONE   ( 1, 1, Runestone.class),

		TREASURE(0.02f,0.02f,	Item.class),
		GOLD	( 10, 10,   Gold.class );
		
		public Class<?>[] classes;

		//some item types use a deck-based system, where the probs decrement as items are picked
		// until they are all 0, and then they reset. Those generator classes should define
		// defaultProbs. If defaultProbs is null then a deck system isn't used.
		//Artifacts in particular don't reset, no duplicates!
		public float[] probs;
		public float[] defaultProbs = null;
		//These variables are used as a part of the deck system, to ensure that drops are consistent
		// regardless of when they occur (either as part of seeded levelgen, or random item drops)
		public Long seed = null;
		public int dropped = 0;

		//game has two decks of 35 items for overall category probs
		//one deck has a ring and extra armor, the other has an artifact and extra thrown weapon
		//Note that pure random drops only happen as part of levelgen atm, so no seed is needed here
		public float firstProb;
		public float secondProb;
		public Class<? extends Item> superClass;
		
		private Category( float firstProb, float secondProb, Class<? extends Item> superClass ) {
			this.firstProb = firstProb;
			this.secondProb = secondProb;
			this.superClass = superClass;
		}
		
		public static int order( Item item ) {
			for (int i=0; i < values().length; i++) {
				if (values()[i].superClass.isInstance( item )) {
					return i;
				}
			}

			//items without a category-defined order are sorted based on the spritesheet
			return Short.MAX_VALUE+item.image();
		}

		static {
			GOLD.classes = new Class<?>[]{
					Gold.class };
			GOLD.probs = new float[]{ 1 };
			
			POTION.classes = new Class<?>[]{
					PotionOfStrength.class, //2 drop every chapter, see Dungeon.posNeeded()
					PotionOfHealing.class,
					PotionOfMindVision.class,
					PotionOfFrost.class,
					PotionOfLiquidFlame.class,
					PotionOfToxicGas.class,
					PotionOfHaste.class,
					PotionOfInvisibility.class,
					PotionOfLevitation.class,
					PotionOfParalyticGas.class,
					PotionOfPurity.class,
					PotionOfExperience.class};
			POTION.defaultProbs = new float[]{ 0, 6, 4, 3, 3, 3, 2, 2, 2, 2, 2, 1 };
			POTION.probs = POTION.defaultProbs.clone();
			
			SEED.classes = new Class<?>[]{
					Rotberry.Seed.class, //quest item
					Sungrass.Seed.class,
					Fadeleaf.Seed.class,
					Icecap.Seed.class,
					Firebloom.Seed.class,
					Sorrowmoss.Seed.class,
					Swiftthistle.Seed.class,
					Blindweed.Seed.class,
					Stormvine.Seed.class,
					Earthroot.Seed.class,
					Mageroyal.Seed.class,
					Starflower.Seed.class};
			SEED.defaultProbs = new float[]{ 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 2 };
			SEED.probs = SEED.defaultProbs.clone();
			
			SCROLL.classes = new Class<?>[]{
					ScrollOfUpgrade.class, //3 drop every chapter, see Dungeon.souNeeded()
					ScrollOfIdentify.class,
					ScrollOfRemoveCurse.class,
					ScrollOfMirrorImage.class,
					ScrollOfRecharging.class,
					ScrollOfTeleportation.class,
					ScrollOfLullaby.class,
					ScrollOfMagicMapping.class,
					ScrollOfRage.class,
					ScrollOfRetribution.class,
					ScrollOfTerror.class,
					ScrollOfTransmutation.class
			};
			SCROLL.defaultProbs = new float[]{ 0, 6, 4, 3, 3, 3, 2, 2, 2, 2, 2, 1 };
			SCROLL.probs = SCROLL.defaultProbs.clone();
			
			STONE.classes = new Class<?>[]{
					StoneOfEnchantment.class,   //1 is guaranteed to drop on floors 6-19
					StoneOfIntuition.class,     //1 additional stone is also dropped on floors 1-3
					StoneOfDisarming.class,
					StoneOfFlock.class,
					StoneOfShock.class,
					StoneOfBlink.class,
					StoneOfDeepSleep.class,
					StoneOfClairvoyance.class,
					StoneOfAggression.class,
					StoneOfBlast.class,
					StoneOfFear.class,
					StoneOfAugmentation.class  //1 is sold in each shop
			};
			STONE.defaultProbs = new float[]{ 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0 };
			STONE.probs = STONE.defaultProbs.clone();

			WAND.classes = new Class<?>[]{
					WandOfMagicMissile.class,
					WandOfLightning.class,
					WandOfDisintegration.class,
					WandOfFireblast.class,
					WandOfCorrosion.class,
					WandOfBlastWave.class,
					WandOfLivingEarth.class,
					WandOfFrost.class,
					WandOfPrismaticLight.class,
					WandOfWarding.class,
					WandOfTransfusion.class,
					WandOfCorruption.class,
					WandOfRegrowth.class };
			WAND.probs = new float[]{ 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3 };
			
			//see generator.randomWeapon
			WEAPON.classes = new Class<?>[]{};
			WEAPON.probs = new float[]{};
			
			WEP_INI.classes = new Class<?>[]{
					MagesStaff.class,
					SpiritBow.class,
					KnifeFork.class,
					SwordShield.class,
					Dagger.class
			};
			WEP_INI.probs = new float[]{ 0,0,1,1,1 };
			
			WEP_STAN.classes = new Class<?>[]{
					AssassinationBlade.class,
					Crossbow.class,
					DoubleBlade.class,
					Glaive.class,
					GreatShield.class,
					Katana.class,
					KnightLance.class,
					LongSword.class,
					RedtasselledSpear.class,
					Scimitar.class,
					TekkoKagi.class,
					WarHammer.class,
			};
			WEP_STAN.probs = new float[]{4,3,4,4,3,4,3,4,3,4,3,3 };

			WEP_SPE_T2.classes = new Class<?>[]{
					HideSword.class,
					DragonFangDagger.class,
					PricklyShield.class,
			};
			WEP_SPE_T2.probs=new float[]{4,4,4};
			WEP_SPE_T3.classes = new Class<?>[]{
					DriveWhip.class,
					PiercingBlade.class,
					SoulDevourerScythe.class,
					Nunchaku.class,
			};
			WEP_SPE_T3.probs=new float[]{4,4,4,3};
			WEP_SPE_T4.classes = new Class<?>[]{
					RuneSword.class,
					Flail.class,
//					MagicShield.class,
			};
			WEP_SPE_T4.probs=new float[]{4,3};
			WEP_SPE_T5.classes = new Class<?>[]{

					CursedSword.class,
//					DemonSword.class,
					GreatAxe.class,
			};
			WEP_SPE_T5.probs = new float[]{4,4};
			//see Generator.randomArmor
			ARMOR.classes = new Class<?>[]{
					ClothArmor.class,
					LeatherArmor.class,
					MailArmor.class,
					ScaleArmor.class,
					PlateArmor.class,
					WarriorArmor.class,
					MageArmor.class,
					RogueArmor.class,
					HuntressArmor.class,
					AdventurerArmor.class};
			ARMOR.probs = new float[]{ 1, 1, 1, 1, 1, 0, 0, 0, 0,0 };
			
			//see Generator.randomMissile
			MISSILE.classes = new Class<?>[]{};
			MISSILE.probs = new float[]{};
			MIS_INI.classes = new Class<?>[]{
					ThrowingStone.class,
					ThrowingKnife.class,
					ThrowingPlate.class,
					ThrowingSpike.class
			};
			MIS_INI.probs = new float[]{1,1,1,1 };
			MIS_STAN.classes=new Class<?>[]{
					Dart.class,
					ThrowingSpear.class,
					ThrowingClub.class
			};
			MIS_STAN.probs = new float[]{0,1,1 };
			MIS_SPE_T2.classes = new Class<?>[]{
					Shuriken.class,
					FishingSpear.class,
					BloodNail.class,
					ThrowingSaltBag.class
			};
			MIS_SPE_T2.probs = new float[]{ 4, 5, 5 ,5};
			
			MIS_SPE_T3.classes = new Class<?>[]{
					Kunai.class,
					Bolas.class,
					ThrowingShield.class,
					KnifeMan.class,
					RottenEgg.class
			};
			MIS_SPE_T3.probs = new float[]{ 5, 5, 5,5,4};
			
			MIS_SPE_T4.classes = new Class<?>[]{
					Tomahawk.class,
					HeavyBoomerang.class
			};
			MIS_SPE_T4.probs = new float[]{ 5, 4 };
			
			MIS_SPE_T5.classes = new Class<?>[]{
					ForceCube.class,
					ThrowingNeedle.class
			};
			MIS_SPE_T5.probs = new float[]{ 5,5 };
			
			FOOD.classes = new Class<?>[]{
					Food.class,
					Pasty.class,
					MysteryMeat.class };
			FOOD.probs = new float[]{ 4, 1, 0 };
			
			RING.classes = new Class<?>[]{
					RingOfArcana.class,
					RingOfEvasion.class,
					RingOfElements.class,
					RingOfForce.class,
					RingOfFuror.class,
					RingOfHaste.class,
					RingOfEnergy.class,
					RingOfMight.class,
					RingOfSharpshooting.class,
					RingOfCommand.class,
					RingOfWealth.class};
			RING.probs = new float[]{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4 };
			
			ARTIFACT.classes = new Class<?>[]{
					AlchemistsToolkit.class,
					ChaliceOfBlood.class,
					CloakOfShadows.class,
					DriedRose.class,
					EtherealChains.class,
					HornOfPlenty.class,
					MasterThievesArmband.class,
					SandalsOfNature.class,
					TalismanOfForesight.class,
					TimekeepersHourglass.class,
					UnstableSpellbook.class,
					CeremonialDagger.class,
					ControlGlove.class
			};
			ARTIFACT.defaultProbs = new float[]{ 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 ,1,1};
			ARTIFACT.probs = ARTIFACT.defaultProbs.clone();

			TREASURE.classes=new Class<?>[]{
					FrostCube.class,
					GoldenCudgel.class,
					VibraniumShield.class
			};
			TREASURE.probs = new float[]{ 1, 2, 0 };
		}
	}

	private static final float[][] floorSetTierProbs = new float[][] {
			{0, 75, 20,  4,  1},
			{0, 25, 50, 20,  5},
			{0,  0, 40, 50, 10},
			{0,  0, 20, 40, 40},
			{0,  0,  0, 20, 80}
	};
	private static boolean usingFirstDeck = false;
	private static HashMap<Category,Float> categoryProbs = new LinkedHashMap<>();

	public static void fullReset() {
		usingFirstDeck = Random.Int(2) == 0;
		generalReset();
		for (Category cat : Category.values()) {
			reset(cat);
			if (cat.defaultProbs != null) {
				cat.seed = Random.Long();
				cat.dropped = 0;
			}
		}
	}

	public static void generalReset(){
		for (Category cat : Category.values()) {
			categoryProbs.put( cat, usingFirstDeck ? cat.firstProb : cat.secondProb );
		}
	}

	public static void reset(Category cat){
		if (cat.defaultProbs != null) cat.probs = cat.defaultProbs.clone();
	}
	
	public static Item random() {
		Category cat = Random.chances( categoryProbs );
		if (cat == null){
			usingFirstDeck = !usingFirstDeck;
			generalReset();
			cat = Random.chances( categoryProbs );
		}
		categoryProbs.put( cat, categoryProbs.get( cat ) - 1);

		if (cat == Category.SEED) {
			//We specifically use defaults for seeds here because, unlike other item categories
			// their predominant source of drops is grass, not levelgen. This way the majority
			// of seed drops still use a deck, but the few that are spawned by levelgen are consistent
			return randomUsingDefaults(cat);
		} else {
			return random(cat);
		}
	}
	
	public static Item random( Category cat ) {
		switch (cat) {
			case ARMOR:
				return randomArmor();
			case WEAPON:
				return randomMelee();
			case MISSILE:
				return randomMissile();
			case ARTIFACT:
				Item item = randomArtifact();
				//if we're out of artifacts, return a ring instead.
				return item != null ? item : random(Category.RING);
			case TREASURE:
				return randomTreasure();
			default:
				if (cat.defaultProbs != null && cat.seed != null){
					Random.pushGenerator(cat.seed);
					for (int i = 0; i < cat.dropped; i++) Random.Long();
				}

				int i = Random.chances(cat.probs);
				if (i == -1) {
					reset(cat);
					i = Random.chances(cat.probs);
				}
				if (cat.defaultProbs != null) cat.probs[i]--;

				if (cat.defaultProbs != null && cat.seed != null){
					Random.popGenerator();
					cat.dropped++;
				}

				return ((Item) Reflection.newInstance(cat.classes[i])).random();
		}
	}

	//overrides any deck systems and always uses default probs
	// except for artifacts, which must always use a deck
	public static Item randomUsingDefaults( Category cat ){
		if (cat.defaultProbs == null || cat == Category.ARTIFACT) {
			return random(cat);
		} else {
			return ((Item) Reflection.newInstance(cat.classes[Random.chances(cat.defaultProbs)])).random();
		}
	}
	
	public static Item random( Class<? extends Item> cl ) {
		return Reflection.newInstance(cl).random();
	}
	public static Item randomTreasure(){
		Item i=(Item) Reflection.newInstance(Category.TREASURE.classes[Random.chances(Category.TREASURE.probs)]);
		return i;
	}
	public static Armor randomArmor(){
		return randomArmor(Dungeon.depth / 5);
	}
	
	public static Armor randomArmor(int floorSet) {

		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);
		
		Armor a = (Armor)Reflection.newInstance(Category.ARMOR.classes[Random.chances(floorSetTierProbs[floorSet])]);
		a.random();
		return a;
	}
	public static final Category[] speMeleeTier=new Category[]{
			Category.WEP_STAN,
			Category.WEP_INI,
			Category.WEP_SPE_T2,
			Category.WEP_SPE_T3,
			Category.WEP_SPE_T4,
			Category.WEP_SPE_T5,
	};

	public static MeleeWeapon randomMelee(){
		return randomMelee(Dungeon.depth / 5);
	}
	
	public static MeleeWeapon randomMelee(int floorSet) {
		return randomMelee(floorSet,0);
	}
	public  static  MeleeWeapon randomMelee(int floorSet,float rarity){

		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);
		int tier=Random.chances(floorSetTierProbs[floorSet])+1;
		Category c;
		if (Random.Float()<0.15f+rarity){
			c=speMeleeTier[tier];
		}else {
			c=Category.WEP_STAN;
		}
		MeleeWeapon w = (MeleeWeapon)Reflection.newInstance(c.classes[Random.chances(c.probs)]);
		w.random();
		w.setTier(tier);
		return w;
	}
	
	public static final Category[] speMisTiers = new Category[]{
			Category.MIS_STAN,
			Category.MIS_INI,
			Category.MIS_SPE_T2,
			Category.MIS_SPE_T3,
			Category.MIS_SPE_T4,
			Category.MIS_SPE_T5
	};
	
	public static MissileWeapon randomMissile(){
		return randomMissile(Dungeon.depth / 5);
	}
	
	public static MissileWeapon randomMissile(int floorSet) {
		return randomMissile(floorSet,0);
	}
	public  static  MissileWeapon randomMissile(int floorSet,float rarity){
		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);
		int tier=Random.chances(floorSetTierProbs[floorSet])+1;
		Category c;
		if (Random.Float()<0.25f+rarity){
			c=speMisTiers[tier];
		}else {
			c=Category.MIS_STAN;
		}
		MissileWeapon w = (MissileWeapon)Reflection.newInstance(c.classes[Random.chances(c.probs)]);
		w.random();
		w.setTier(tier);
		return w;
	}
	//enforces uniqueness of artifacts throughout a run.
	public static Artifact randomArtifact() {

		Category cat = Category.ARTIFACT;

		if (cat.defaultProbs != null && cat.seed != null){
			Random.pushGenerator(cat.seed);
			for (int i = 0; i < cat.dropped; i++) Random.Long();
		}

		int i = Random.chances( cat.probs );

		if (cat.defaultProbs != null && cat.seed != null){
			Random.popGenerator();
			cat.dropped++;
		}

		//if no artifacts are left, return null
		if (i == -1){
			return null;
		}

		cat.probs[i]--;
		return (Artifact) Reflection.newInstance((Class<? extends Artifact>) cat.classes[i]).random();

	}

	public static boolean removeArtifact(Class<?extends Artifact> artifact) {
		Category cat = Category.ARTIFACT;
		for (int i = 0; i < cat.classes.length; i++){
			if (cat.classes[i].equals(artifact) && cat.probs[i] > 0) {
				cat.probs[i] = 0;
				return true;
			}
		}
		return false;
	}

	private static final String FIRST_DECK = "first_deck";
	private static final String GENERAL_PROBS = "general_probs";
	private static final String CATEGORY_PROBS = "_probs";
	private static final String CATEGORY_SEED = "_seed";
	private static final String CATEGORY_DROPPED = "_dropped";

	public static void storeInBundle(Bundle bundle) {
		bundle.put(FIRST_DECK, usingFirstDeck);

		Float[] genProbs = categoryProbs.values().toArray(new Float[0]);
		float[] storeProbs = new float[genProbs.length];
		for (int i = 0; i < storeProbs.length; i++){
			storeProbs[i] = genProbs[i];
		}
		bundle.put( GENERAL_PROBS, storeProbs);

		for (Category cat : Category.values()){
			if (cat.defaultProbs == null) continue;

			bundle.put(cat.name().toLowerCase() + CATEGORY_PROBS,   cat.probs);
			if (cat.seed != null) {
				bundle.put(cat.name().toLowerCase() + CATEGORY_SEED, cat.seed);
				bundle.put(cat.name().toLowerCase() + CATEGORY_DROPPED, cat.dropped);
			}
		}
	}

	public static void restoreFromBundle(Bundle bundle) {
		fullReset();

		usingFirstDeck = bundle.getBoolean(FIRST_DECK);

		if (bundle.contains(GENERAL_PROBS)){
			float[] probs = bundle.getFloatArray(GENERAL_PROBS);
			for (int i = 0; i < probs.length; i++){
				categoryProbs.put(Category.values()[i], probs[i]);
			}
		}

		for (Category cat : Category.values()){
			if (bundle.contains(cat.name().toLowerCase() + CATEGORY_PROBS)){
				float[] probs = bundle.getFloatArray(cat.name().toLowerCase() + CATEGORY_PROBS);
				if (cat.defaultProbs != null && probs.length == cat.defaultProbs.length){
					cat.probs = probs;
				}
				if (bundle.contains(cat.name().toLowerCase() + CATEGORY_SEED)){
					cat.seed = bundle.getLong(cat.name().toLowerCase() + CATEGORY_SEED);
					cat.dropped = bundle.getInt(cat.name().toLowerCase() + CATEGORY_DROPPED);
				}
			}
		}
		
	}
}
