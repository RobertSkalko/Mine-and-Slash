package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.stats.types.defense.Armor;
import com.robertx22.database.stats.types.defense.Dodge;
import com.robertx22.database.stats.types.defense.SpellDodge;
import com.robertx22.database.stats.types.elementals.damage.FireDamage;
import com.robertx22.database.stats.types.elementals.damage.NatureDamage;
import com.robertx22.database.stats.types.elementals.damage.ThunderDamage;
import com.robertx22.database.stats.types.elementals.damage.WaterDamage;
import com.robertx22.database.stats.types.elementals.pene.FirePene;
import com.robertx22.database.stats.types.elementals.pene.NaturePene;
import com.robertx22.database.stats.types.elementals.pene.ThunderPene;
import com.robertx22.database.stats.types.elementals.pene.WaterPene;
import com.robertx22.database.stats.types.elementals.resist.FireResist;
import com.robertx22.database.stats.types.elementals.resist.NatureResist;
import com.robertx22.database.stats.types.elementals.resist.ThunderResist;
import com.robertx22.database.stats.types.elementals.resist.WaterResist;
import com.robertx22.database.stats.types.offense.CriticalDamage;
import com.robertx22.database.stats.types.offense.CriticalHit;
import com.robertx22.database.stats.types.offense.LifeOnHit;
import com.robertx22.database.stats.types.offense.Lifesteal;
import com.robertx22.database.stats.types.offense.PhysicalDamage;
import com.robertx22.database.stats.types.resources.Energy;
import com.robertx22.database.stats.types.resources.EnergyRegen;
import com.robertx22.database.stats.types.resources.Health;
import com.robertx22.database.stats.types.resources.HealthRegen;
import com.robertx22.database.stats.types.resources.Mana;
import com.robertx22.database.stats.types.resources.ManaRegen;
import com.robertx22.database.stats.types.traits.Elemental;
import com.robertx22.database.stats.types.traits.Golem;
import com.robertx22.database.stats.types.traits.Lucky;
import com.robertx22.database.stats.types.traits.Stealthy;
import com.robertx22.database.stats.types.traits.bad_and_good.Barbarian;
import com.robertx22.database.stats.types.traits.bad_and_good.ClumsyScholar;
import com.robertx22.database.stats.types.traits.bad_ones.Crippled;
import com.robertx22.database.stats.types.traits.bad_ones.Diseased;
import com.robertx22.stats.Stat;

public class Stats {
	public static HashMap<String, Stat> All = new HashMap<String, Stat>() {
		{
			{

				// Resources
				put(Health.GUID, new Health());
				put(HealthRegen.GUID, new HealthRegen());

				put(Mana.GUID, new Mana());
				put(ManaRegen.GUID, new ManaRegen());

				put(Energy.GUID, new Energy());
				put(EnergyRegen.GUID, new EnergyRegen());
				// Resources

				put(Armor.GUID, new Armor());
				put(CriticalDamage.GUID, new CriticalDamage());
				put(CriticalHit.GUID, new CriticalHit());
				put(PhysicalDamage.GUID, new PhysicalDamage());

				// Elemental
				put(FireDamage.GUID, new FireDamage());
				put(WaterDamage.GUID, new WaterDamage());
				put(ThunderDamage.GUID, new ThunderDamage());
				put(NatureDamage.GUID, new NatureDamage());

				put(FireResist.GUID, new FireResist());
				put(NatureResist.GUID, new NatureResist());
				put(WaterResist.GUID, new WaterResist());
				put(ThunderResist.GUID, new ThunderResist());

				put(FirePene.GUID, new FirePene());
				put(NaturePene.GUID, new NaturePene());
				put(WaterPene.GUID, new WaterPene());
				put(ThunderPene.GUID, new ThunderPene());
				// Elemental

				put(Lifesteal.GUID, new Lifesteal());
				put(LifeOnHit.GUID, new LifeOnHit());

				put(Dodge.GUID, new Dodge());
				put(SpellDodge.GUID, new SpellDodge());

				// traits
				put(Golem.GUID, new Golem());
				put(Elemental.GUID, new Elemental());
				put(Lucky.GUID, new Lucky());
				put(Barbarian.GUID, new Barbarian());
				put(Stealthy.GUID, new Stealthy());
				put(ClumsyScholar.GUID, new ClumsyScholar());
				put(Crippled.GUID, new Crippled());
				put(Diseased.GUID, new Diseased());

				// traits

			}
		}
	};
}
