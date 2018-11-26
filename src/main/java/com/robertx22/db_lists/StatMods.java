package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.database.map_mods.bonus.BonusHealthMap;
import com.robertx22.database.map_mods.bonus.BonusLifestealMap;
import com.robertx22.database.map_mods.bonus.ele_dmg.BonusFireDamageMap;
import com.robertx22.database.map_mods.bonus.ele_dmg.BonusNatureDamageMap;
import com.robertx22.database.map_mods.bonus.ele_dmg.BonusThunderDamageMap;
import com.robertx22.database.map_mods.bonus.ele_dmg.BonusWaterDamageMap;
import com.robertx22.database.map_mods.bonus.ele_res.BonusFireResistMap;
import com.robertx22.database.map_mods.bonus.ele_res.BonusNatureResistMap;
import com.robertx22.database.map_mods.bonus.ele_res.BonusThunderResistMap;
import com.robertx22.database.map_mods.bonus.ele_res.BonusWaterResistMap;
import com.robertx22.database.map_mods.minus.LessCriticalHitMap;
import com.robertx22.database.map_mods.minus.LessDodgeMap;
import com.robertx22.database.map_mods.minus.LessEnergyRegenMap;
import com.robertx22.database.map_mods.minus.LessHealthMap;
import com.robertx22.database.map_mods.minus.LessHealthRegenMap;
import com.robertx22.database.map_mods.minus.LessLifeOnHitMap;
import com.robertx22.database.map_mods.minus.LessLifestealMap;
import com.robertx22.database.map_mods.minus.LessManaOnHitMap;
import com.robertx22.database.map_mods.minus.LessManaRegenMap;
import com.robertx22.database.map_mods.minus.all_ele_dmg.LessAllFireDamageMap;
import com.robertx22.database.map_mods.minus.all_ele_dmg.LessAllNatureDamageMap;
import com.robertx22.database.map_mods.minus.all_ele_dmg.LessAllThunderDamageMap;
import com.robertx22.database.map_mods.minus.all_ele_dmg.LessAllWaterDamageMap;
import com.robertx22.database.stats.mods.flat.ArmorFlat;
import com.robertx22.database.stats.mods.flat.ArmorPeneFlat;
import com.robertx22.database.stats.mods.flat.CriticalDamageFlat;
import com.robertx22.database.stats.mods.flat.CriticalHitFlat;
import com.robertx22.database.stats.mods.flat.DamageFlat;
import com.robertx22.database.stats.mods.flat.DodgeFlat;
import com.robertx22.database.stats.mods.flat.elemental.attack_dmg.AttackFireDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.attack_dmg.AttackNatureDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.attack_dmg.AttackThunderDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.attack_dmg.AttackWaterDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.bonus.BonusFireDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.bonus.BonusNatureDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.bonus.BonusThunderDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.bonus.BonusWaterDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.pene.FirePeneFlat;
import com.robertx22.database.stats.mods.flat.elemental.pene.NaturePeneFlat;
import com.robertx22.database.stats.mods.flat.elemental.pene.ThunderPeneFlat;
import com.robertx22.database.stats.mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.database.stats.mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stats.mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stats.mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.spell_dmg.SpellNatureDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.spell_dmg.SpellThunderDamageFlat;
import com.robertx22.database.stats.mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
import com.robertx22.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stats.mods.flat.resources.LifeOnHitFlat;
import com.robertx22.database.stats.mods.flat.resources.LifestealFlat;
import com.robertx22.database.stats.mods.flat.resources.ManaFlat;
import com.robertx22.database.stats.mods.flat.resources.ManaOnHitFlat;
import com.robertx22.database.stats.mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stats.mods.percent.ArmorPercent;
import com.robertx22.database.stats.mods.percent.CriticalHitPercent;
import com.robertx22.database.stats.mods.percent.DamagePercent;
import com.robertx22.database.stats.mods.percent.EnergyRegenPercent;
import com.robertx22.database.stats.mods.percent.HealthPercent;
import com.robertx22.database.stats.mods.percent.LifestealPercent;
import com.robertx22.database.stats.mods.percent.ManaRegenPercent;
import com.robertx22.database.stats.mods.percent.elemental.FireDamagePercent;
import com.robertx22.database.stats.mods.percent.elemental.NatureDamagePercent;
import com.robertx22.database.stats.mods.percent.elemental.ThunderDamagePercent;
import com.robertx22.database.stats.mods.percent.elemental.WaterDamagePercent;
import com.robertx22.database.stats.mods.traits.ArmoredFlat;
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

	public static HashMap<String, StatMod> All = new HashMap<String, StatMod>() {
		{
			{
				put(new ArmorFlat().GUID(), new ArmorFlat());
				put(new ArmorPeneFlat().GUID(), new ArmorPeneFlat());
				put(new CriticalHitFlat().GUID(), new CriticalHitFlat());
				put(new CriticalDamageFlat().GUID(), new CriticalDamageFlat());
				put(new DamageFlat().GUID(), new DamageFlat());
				put(new CriticalHitPercent().GUID(), new CriticalHitPercent());
				put(new DamagePercent().GUID(), new DamagePercent());

				// Resources
				put(new HealthFlat().GUID(), new HealthFlat());
				put(new HealthPercent().GUID(), new HealthPercent());
				put(new HealthRegenFlat().GUID(), new HealthRegenFlat());
				put(new ManaRegenFlat().GUID(), new ManaRegenFlat());
				put(new EnergyRegenFlat().GUID(), new EnergyRegenFlat());
				put(new EnergyRegenPercent().GUID(), new EnergyRegenPercent());
				put(new ManaRegenPercent().GUID(), new ManaRegenPercent());

				put(new LifestealFlat().GUID(), new LifestealFlat());
				put(new LifestealPercent().GUID(), new LifestealPercent());
				put(new LifeOnHitFlat().GUID(), new LifeOnHitFlat());
				put(new ManaFlat().GUID(), new ManaFlat());
				put(new ManaOnHitFlat().GUID(), new ManaOnHitFlat());
				// Resources

				// Elements
				put(new SpellFireDamageFlat().GUID(), new SpellFireDamageFlat());
				put(new SpellWaterDamageFlat().GUID(), new SpellWaterDamageFlat());
				put(new SpellThunderDamageFlat().GUID(), new SpellThunderDamageFlat());
				put(new SpellNatureDamageFlat().GUID(), new SpellNatureDamageFlat());

				put(new AttackFireDamageFlat().GUID(), new AttackFireDamageFlat());
				put(new AttackWaterDamageFlat().GUID(), new AttackWaterDamageFlat());
				put(new AttackThunderDamageFlat().GUID(), new AttackThunderDamageFlat());
				put(new AttackNatureDamageFlat().GUID(), new AttackNatureDamageFlat());

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
				put(new ArmoredFlat().GUID(), new ArmoredFlat());

				// Traits

				// Map mods

				put(new BonusHealthMap().GUID(), new BonusHealthMap());
				put(new BonusLifestealMap().GUID(), new BonusLifestealMap());

				put(new LessCriticalHitMap().GUID(), new LessCriticalHitMap());
				put(new LessDodgeMap().GUID(), new LessDodgeMap());

				put(new BonusFireDamageMap().GUID(), new BonusFireDamageMap());
				put(new BonusNatureDamageMap().GUID(), new BonusNatureDamageMap());
				put(new BonusThunderDamageMap().GUID(), new BonusThunderDamageMap());
				put(new BonusWaterDamageMap().GUID(), new BonusWaterDamageMap());

				put(new BonusFireResistMap().GUID(), new BonusFireResistMap());
				put(new BonusNatureResistMap().GUID(), new BonusNatureResistMap());
				put(new BonusThunderResistMap().GUID(), new BonusThunderResistMap());
				put(new BonusWaterResistMap().GUID(), new BonusWaterResistMap());

				put(new LessAllFireDamageMap().GUID(), new LessAllFireDamageMap());
				put(new LessAllNatureDamageMap().GUID(), new LessAllNatureDamageMap());
				put(new LessAllThunderDamageMap().GUID(), new LessAllThunderDamageMap());
				put(new LessAllWaterDamageMap().GUID(), new LessAllWaterDamageMap());

				put(new LessEnergyRegenMap().GUID(), new LessEnergyRegenMap());
				put(new LessManaRegenMap().GUID(), new LessManaRegenMap());
				put(new LessHealthRegenMap().GUID(), new LessHealthRegenMap());
				put(new LessLifeOnHitMap().GUID(), new LessLifeOnHitMap());
				put(new LessLifestealMap().GUID(), new LessLifestealMap());
				put(new LessHealthMap().GUID(), new LessHealthMap());
				put(new LessManaOnHitMap().GUID(), new LessManaOnHitMap());
				// Map mods

			}
		}
	};

}
