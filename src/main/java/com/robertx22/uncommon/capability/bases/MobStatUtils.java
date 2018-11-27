package com.robertx22.uncommon.capability.bases;

import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.stat_types.defense.Armor;
import com.robertx22.database.stat_types.elementals.resist.FireResist;
import com.robertx22.database.stat_types.elementals.resist.NatureResist;
import com.robertx22.database.stat_types.elementals.resist.ThunderResist;
import com.robertx22.database.stat_types.elementals.resist.WaterResist;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.database.stat_types.offense.CriticalDamage;
import com.robertx22.database.stat_types.offense.CriticalHit;
import com.robertx22.database.stat_types.offense.PhysicalDamage;
import com.robertx22.database.stat_types.resources.Health;
import com.robertx22.database.status.effects.bases.BaseStatusEffect;
import com.robertx22.db_lists.StatusEffects;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.effects.StatusEffectData;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.entity.EntityLivingBase;

public class MobStatUtils {

	public static void AddMobcStats(Unit unit, int level) {

		unit.MyStats.get(Health.GUID).Flat += 10 * level;
		unit.MyStats.get(Armor.GUID).Flat += 10 * level;
		unit.MyStats.get(CriticalHit.GUID).Flat += 5;
		unit.MyStats.get(CriticalDamage.GUID).Flat += 20;

		unit.MyStats.get(WaterResist.GUID).Flat += 10 * level;
		unit.MyStats.get(FireResist.GUID).Flat += 10 * level;
		unit.MyStats.get(ThunderResist.GUID).Flat += 10 * level;
		unit.MyStats.get(NatureResist.GUID).Flat += 10 * level;

		unit.MyStats.get(SpellWaterDamage.GUID).Flat += 10 * level;
		unit.MyStats.get(SpellFireDamage.GUID).Flat += 10 * level;
		unit.MyStats.get(SpellThunderDamage.GUID).Flat += 10 * level;
		unit.MyStats.get(SpellNatureDamage.GUID).Flat += 10 * level;

		unit.MyStats.get(PhysicalDamage.GUID).Flat += 0.4F * level;

	}

	public static void AddMobTierStats(Unit unit, int tier) {
		for (StatData data : unit.MyStats.values()) {
			if (data.GetStat().IsPercent() == false) {
				float newval = IncByPercent(data.Flat, tier);
				unit.MyStats.get(data.Name).Flat = newval;
			}
		}
	}

	private static float IncByPercent(float val, int tier) {
		return val + (val * tier * 25 / 100);
	}

	public static void SetMobStrengthMultiplier(Unit unit, MobRarity rarity) {

		float stat_multi = rarity.StatMultiplier();
		float hpmulti = rarity.HealthMultiplier();
		float damagemulti = rarity.DamageMultiplier();

		for (StatData stat : unit.MyStats.values()) {
			if (stat.GetStat() instanceof PhysicalDamage) {
				stat.Flat *= damagemulti;
			} else if (stat.GetStat() instanceof Health) {
				stat.Flat *= hpmulti;
			} else {
				stat.Flat *= stat_multi;
			}
		}

	}

	public static void AddRandomMobStatusEffects(EntityLivingBase entity, Unit unit, MobRarity rarity) {

		int max = rarity.MaxMobEffects();

		if (max > 0) {
			if (max > StatusEffects.All.values().size()) {
				System.out.println("ERROR! Can't have more unique effects than there are effects!");
				max = StatusEffects.All.values().size() - 1;
			}

			int amount = RandomUtils.RandomRange(0, max);

			while (amount > 0) {

				BaseStatusEffect effect = null;

				while (effect == null || unit.statusEffects.containsKey(effect.GUID())) {
					effect = (BaseStatusEffect) RandomUtils
							.WeightedRandom(ListUtils.CollectionToList(StatusEffects.All.values()));
				}
				amount--;
				unit.statusEffects.put(effect.GUID(), new StatusEffectData(effect));

			}
		}
	}
}
