package com.robertx22.uncommon.capability.bases;

import com.robertx22.database.lists.StatusEffects;
import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.stats.types.defense.Armor;
import com.robertx22.database.stats.types.elementals.damage.FireDamage;
import com.robertx22.database.stats.types.elementals.damage.NatureDamage;
import com.robertx22.database.stats.types.elementals.damage.ThunderDamage;
import com.robertx22.database.stats.types.elementals.damage.WaterDamage;
import com.robertx22.database.stats.types.elementals.resist.FireResist;
import com.robertx22.database.stats.types.elementals.resist.NatureResist;
import com.robertx22.database.stats.types.elementals.resist.ThunderResist;
import com.robertx22.database.stats.types.elementals.resist.WaterResist;
import com.robertx22.database.stats.types.offense.CriticalDamage;
import com.robertx22.database.stats.types.offense.CriticalHit;
import com.robertx22.database.stats.types.offense.PhysicalDamage;
import com.robertx22.database.stats.types.resources.Health;
import com.robertx22.database.status.effects.bases.BaseStatusEffect;
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

		unit.MyStats.get(WaterDamage.GUID).Flat += 10 * level;
		unit.MyStats.get(FireDamage.GUID).Flat += 10 * level;
		unit.MyStats.get(ThunderDamage.GUID).Flat += 10 * level;
		unit.MyStats.get(NatureDamage.GUID).Flat += 10 * level;

		unit.MyStats.get(PhysicalDamage.GUID).Flat += 0.4F * level;

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
