package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.stats.mods.flat.ArmorFlat;
import com.robertx22.database.stats.mods.flat.CriticalDamageFlat;
import com.robertx22.database.stats.mods.flat.CriticalHitFlat;
import com.robertx22.database.stats.mods.flat.DamageFlat;
import com.robertx22.database.stats.mods.flat.DodgeFlat;
import com.robertx22.database.stats.mods.flat.HealthFlat;
import com.robertx22.database.stats.mods.flat.LifeOnHitFlat;
import com.robertx22.database.stats.mods.flat.LifestealFlat;
import com.robertx22.database.stats.mods.flat.elemental.bonus.BonusFireDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.bonus.BonusNatureDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.bonus.BonusThunderDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.bonus.BonusWaterDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.dmg.FireDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.dmg.NatureDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.dmg.ThunderDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.dmg.WaterDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.pene.FirePeneFlat;
import com.robertx22.database.stats.mods.flat.elemental.pene.NaturePeneFlat;
import com.robertx22.database.stats.mods.flat.elemental.pene.ThunderPeneFlat;
import com.robertx22.database.stats.mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.database.stats.mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stats.mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stats.mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stats.mods.percent.ArmorPercent;
import com.robertx22.database.stats.mods.percent.EnergyRegenPercent;
import com.robertx22.database.stats.mods.percent.HealthPercent;
import com.robertx22.database.stats.mods.percent.LifestealPercent;
import com.robertx22.database.stats.mods.percent.ManaRegenPercent;
import com.robertx22.database.stats.mods.percent.elemental.FireDamagePercent;
import com.robertx22.database.stats.mods.percent.elemental.NatureDamagePercent;
import com.robertx22.database.stats.mods.percent.elemental.ThunderDamagePercent;
import com.robertx22.database.stats.mods.percent.elemental.WaterDamagePercent;
import com.robertx22.database.stats.mods.traits.BarbarianFlat;
import com.robertx22.database.stats.mods.traits.ClumsyScholarFlat;
import com.robertx22.database.stats.mods.traits.CrippledFlat;
import com.robertx22.database.stats.mods.traits.DiseasedFlat;
import com.robertx22.database.stats.mods.traits.EarthAtronachFlat;
import com.robertx22.database.stats.mods.traits.ElementalFlat;
import com.robertx22.database.stats.mods.traits.FireAtronachFlat;
import com.robertx22.database.stats.mods.traits.FrostAtronachFlat;
import com.robertx22.database.stats.mods.traits.GolemFlat;
import com.robertx22.database.stats.mods.traits.LuckyFlat;
import com.robertx22.database.stats.mods.traits.ThunderAtronachFlat;
import com.robertx22.stats.StatMod;

public class StatMods {

	private static HashMap<String, StatMod> mods = null;

	public static <C> HashMap<String, StatMod> All() {

		return All;
		/*
		 * if (mods == null) { mods = Database.All(StatMod.class,
		 * StatModAnot.class.getCanonicalName(), mods); } return mods;
		 */
	}

	private static HashMap<String, StatMod> All = new HashMap<String, StatMod>() {
		{
			{
				put(new ArmorFlat().GUID(), new ArmorFlat());
				put(new CriticalHitFlat().GUID(), new CriticalHitFlat());
				put(new CriticalDamageFlat().GUID(), new CriticalDamageFlat());
				put(new DamageFlat().GUID(), new DamageFlat());

				// Resources
				put(new HealthFlat().GUID(), new HealthFlat());
				put(new HealthPercent().GUID(), new HealthPercent());
				put(new HealthRegenFlat().GUID(), new HealthRegenFlat());
				put(new ManaRegenFlat().GUID(), new ManaRegenFlat());
				put(new EnergyRegenFlat().GUID(), new EnergyRegenFlat());
				put(new EnergyRegenPercent().GUID(), new EnergyRegenPercent());
				put(new ManaRegenPercent().GUID(), new ManaRegenPercent());

				// Resources
				put(new LifestealFlat().GUID(), new LifestealFlat());
				put(new LifestealPercent().GUID(), new LifestealPercent());
				put(new LifeOnHitFlat().GUID(), new LifeOnHitFlat());

				// Elements
				put(new FireDamageFlat().GUID(), new FireDamageFlat());
				put(new WaterDamageFlat().GUID(), new WaterDamageFlat());
				put(new ThunderDamageFlat().GUID(), new ThunderDamageFlat());
				put(new NatureDamageFlat().GUID(), new NatureDamageFlat());

				put(new FireResistFlat().GUID(), new FireResistFlat());
				put(new WaterResistFlat().GUID(), new WaterResistFlat());
				put(new ThunderResistFlat().GUID(), new ThunderResistFlat());
				put(new NatureResistFlat().GUID(), new NatureResistFlat());

				put(new FirePeneFlat().GUID(), new FirePeneFlat());
				put(new WaterPeneFlat().GUID(), new WaterPeneFlat());
				put(new ThunderPeneFlat().GUID(), new ThunderPeneFlat());
				put(new NaturePeneFlat().GUID(), new NaturePeneFlat());

				put(new FireDamagePercent().GUID(), new FireDamagePercent());
				put(new WaterDamagePercent().GUID(), new WaterDamagePercent());
				put(new ThunderDamagePercent().GUID(), new ThunderDamagePercent());
				put(new NatureDamagePercent().GUID(), new NatureDamagePercent()); // Elements

				put(new ArmorPercent().GUID(), new ArmorPercent());
				put(new DodgeFlat().GUID(), new DodgeFlat());

				// bonus dmg
				put(new BonusWaterDamageFlat().GUID(), new BonusWaterDamageFlat());
				put(new BonusFireDamageFlat().GUID(), new BonusFireDamageFlat());
				put(new BonusThunderDamageFlat().GUID(), new BonusThunderDamageFlat());
				put(new BonusNatureDamageFlat().GUID(), new BonusNatureDamageFlat());
				// bonus dmg

				// Traits
				put(new GolemFlat().GUID(), new GolemFlat());
				put(new ElementalFlat().GUID(), new ElementalFlat());
				put(new ClumsyScholarFlat().GUID(), new ClumsyScholarFlat());
				put(new DiseasedFlat().GUID(), new DiseasedFlat());
				put(new CrippledFlat().GUID(), new CrippledFlat());
				put(new BarbarianFlat().GUID(), new BarbarianFlat());
				put(new EarthAtronachFlat().GUID(), new EarthAtronachFlat());
				put(new FireAtronachFlat().GUID(), new FireAtronachFlat());
				put(new FrostAtronachFlat().GUID(), new FrostAtronachFlat());
				put(new ThunderAtronachFlat().GUID(), new ThunderAtronachFlat());
				put(new LuckyFlat().GUID(), new LuckyFlat());

				// Traits

			}
		}
	};

}
