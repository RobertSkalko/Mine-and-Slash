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
import com.robertx22.database.status.effects.bases.BaseStatusEffect;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.StatusEffects;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.effects.StatusEffectData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.entity.EntityLivingBase;

public class MobStatUtils {

    static int spelldmg = 8;
    static int spellresist = 4;

    public static void AddMobcStats(UnitData unitdata, int level) {

	MobRarity rar = Rarities.Mobs.get(unitdata.getRarity());
	Unit unit = unitdata.getUnit();

	unit.MyStats.get(Armor.GUID).Flat += 10 * level * rar.StatMultiplier();
	unit.MyStats.get(CriticalHit.GUID).Flat += 5 * rar.StatMultiplier();
	unit.MyStats.get(CriticalDamage.GUID).Flat += 5 * rar.StatMultiplier();

	unit.MyStats.get(WaterResist.GUID).Flat += spellresist * level * rar.StatMultiplier();
	unit.MyStats.get(FireResist.GUID).Flat += spellresist * level * rar.StatMultiplier();
	unit.MyStats.get(ThunderResist.GUID).Flat += spellresist * level * rar.StatMultiplier();
	unit.MyStats.get(NatureResist.GUID).Flat += spellresist * level * rar.StatMultiplier();

	unit.MyStats.get(SpellWaterDamage.GUID).Flat += spelldmg * level * rar.DamageMultiplier();
	unit.MyStats.get(SpellFireDamage.GUID).Flat += spelldmg * level * rar.DamageMultiplier();
	unit.MyStats.get(SpellThunderDamage.GUID).Flat += spelldmg * level * rar.DamageMultiplier();
	unit.MyStats.get(SpellNatureDamage.GUID).Flat += spelldmg * level * rar.DamageMultiplier();

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
	return val + (val * tier * 10 / 100);
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
