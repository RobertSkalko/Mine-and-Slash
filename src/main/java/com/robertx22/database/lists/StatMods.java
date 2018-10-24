package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.stats.mods.flat.ArmorFlat;
import com.robertx22.database.stats.mods.flat.CriticalDamageFlat;
import com.robertx22.database.stats.mods.flat.CriticalHitFlat;
import com.robertx22.database.stats.mods.flat.DamageFlat;
import com.robertx22.database.stats.mods.flat.HealthFlat;
import com.robertx22.database.stats.mods.flat.LifeOnHitFlat;
import com.robertx22.database.stats.mods.flat.LifestealFlat;
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
import com.robertx22.database.stats.mods.percent.elemental.FireDamagePercent;
import com.robertx22.database.stats.mods.percent.elemental.NatureDamagePercent;
import com.robertx22.database.stats.mods.percent.elemental.ThunderDamagePercent;
import com.robertx22.database.stats.mods.percent.elemental.WaterDamagePercent;
import com.robertx22.database.stats.mods.traits.GolemFlat;
import com.robertx22.stats.StatMod;

public class StatMods {
	public static HashMap<String, StatMod> All = new HashMap<String, StatMod>() {
		{
			{
				put(new ArmorFlat().GUID(), new ArmorFlat());
				put(new CriticalHitFlat().GUID(), new CriticalHitFlat());
				put(new CriticalDamageFlat().GUID(), new CriticalDamageFlat());
				put(new DamageFlat().GUID(), new DamageFlat());

				// Resources
				put(new HealthFlat().GUID(), new HealthFlat());
				put(new HealthRegenFlat().GUID(), new HealthRegenFlat());
				put(new ManaRegenFlat().GUID(), new ManaRegenFlat());
				put(new EnergyRegenFlat().GUID(), new EnergyRegenFlat());
				// Resources

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
				put(new NatureDamagePercent().GUID(), new NatureDamagePercent());
				// Elements

				put(new ArmorPercent().GUID(), new ArmorPercent());

				// Traits
				put(new GolemFlat().GUID(), new GolemFlat());
				// Traits

				put(new LifestealFlat().GUID(), new LifestealFlat());
				put(new LifeOnHitFlat().GUID(), new LifeOnHitFlat());

			}
		}
	};
}
