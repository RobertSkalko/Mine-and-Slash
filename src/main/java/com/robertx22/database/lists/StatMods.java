package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.stats.mods.flat.ArmorFlat;
import com.robertx22.database.stats.mods.flat.CriticalDamageFlat;
import com.robertx22.database.stats.mods.flat.CriticalHitFlat;
import com.robertx22.database.stats.mods.flat.DamageFlat;
import com.robertx22.database.stats.mods.flat.HealthFlat;
import com.robertx22.database.stats.mods.flat.LifeOnHitFlat;
import com.robertx22.database.stats.mods.flat.LifestealFlat;
import com.robertx22.database.stats.mods.flat.elemental.FireDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.NatureDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.ThunderDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.WaterDamageFlat;
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
				put(new HealthFlat().GUID(), new HealthFlat());
				put(new ArmorFlat().GUID(), new ArmorFlat());
				put(new CriticalHitFlat().GUID(), new CriticalHitFlat());
				put(new CriticalDamageFlat().GUID(), new CriticalDamageFlat());

				put(new DamageFlat().GUID(), new DamageFlat());

				put(new FireDamageFlat().GUID(), new FireDamageFlat());
				put(new WaterDamageFlat().GUID(), new WaterDamageFlat());
				put(new ThunderDamageFlat().GUID(), new ThunderDamageFlat());
				put(new NatureDamageFlat().GUID(), new NatureDamageFlat());

				put(new FireDamagePercent().GUID(), new FireDamagePercent());
				put(new WaterDamagePercent().GUID(), new WaterDamagePercent());
				put(new ThunderDamagePercent().GUID(), new ThunderDamagePercent());
				put(new NatureDamagePercent().GUID(), new NatureDamagePercent());

				put(new ArmorPercent().GUID(), new ArmorPercent());

				put(new GolemFlat().GUID(), new GolemFlat());

				put(new LifestealFlat().GUID(), new LifestealFlat());
				put(new LifeOnHitFlat().GUID(), new LifeOnHitFlat());

			}
		}
	};
}
