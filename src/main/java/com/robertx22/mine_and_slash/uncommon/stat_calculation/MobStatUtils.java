package com.robertx22.mine_and_slash.uncommon.stat_calculation;

import com.robertx22.mine_and_slash.config.dimension_configs.DimensionsContainer;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfig;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfigs;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.ElementalPene;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.ElementalResist;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.Health;
import com.robertx22.mine_and_slash.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.effects.StatusEffectData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import java.util.stream.Collectors;

public class MobStatUtils {

    static int spelldmg = 10;
    static int elePene = 8;
    static int spellresist = 3;

    public static void increaseMobStatsPerTier(UnitData mobdata, Unit unit) {

        for (StatData data : unit.getStats()
                .values()
                .stream()
                .filter(x -> x.GetStat().IsPercent() == false)
                .collect(Collectors.toList())) {

            data.Flat = data.Flat * mobdata.getStatMultiplierIncreaseByTier();
        }

    }

    public static void worldMultiplierStats(World world, Unit unit) {

        for (StatData stat : unit.getStats().values()) {
            stat.Flat *= DimensionsContainer.INSTANCE.getConfig(world).MOB_STRENGTH_MULTIPLIER;
        }

    }

    public static void modifyMobStatsByConfig(Entity entity, UnitData unitdata,
                                              int level) {

        Unit unit = unitdata.getUnit();
        ModEntityConfig config = ModEntityConfigs.INSTANCE.getConfig(entity);

        for (StatData data : unit.getStats().values()) {
            Stat stat = data.GetStat();
            if (stat instanceof PhysicalDamage || stat instanceof ElementalSpellDamage || stat instanceof CriticalDamage || stat instanceof CriticalHit) {
                data.Flat *= config.DMG_MULTI;
            } else if (data.Name.equals(Health.GUID)) {
                data.Flat *= config.HP_MULTI;
            } else {
                data.Flat *= config.STAT_MULTI;
            }
        }

    }

    public static void AddMobcStats(UnitData unitdata, int level) {

        MobRarity rar = Rarities.Mobs.get(unitdata.getRarity());
        Unit unit = unitdata.getUnit();

        unit.getStat(Armor.GUID).Flat += 10 * level * rar.StatMultiplier();
        unit.getStat(CriticalHit.GUID).Flat += 5 * rar.DamageMultiplier();
        unit.getStat(CriticalDamage.GUID).Flat += 10 * rar.DamageMultiplier();

        for (Elements element : Elements.getAllSingleElements()) {

            unit.getStat(new ElementalResist(element).GUID()).Flat += spellresist * level * rar
                    .StatMultiplier();
            unit.getStat(new ElementalSpellDamage(element).GUID()).Flat += spelldmg * level * rar
                    .DamageMultiplier();
        }

        for (Elements element : Elements.getAllExceptNone()) {
            unit.getStat(new ElementalPene(element).GUID()).Flat += elePene * level * rar.DamageMultiplier();

        }

    }

    // this apparently takes 60 ms
    public static void AddRandomMobStatusEffects(LivingEntity entity, Unit unit,
                                                 MobRarity rarity) {

        int max = rarity.MaxMobEffects();

        if (max > 0) {
            if (max > SlashRegistry.StatusEffects().getAll().values().size()) {
                System.out.println("ERROR! Can't have more unique effects than there are effects!");
                max = SlashRegistry.StatusEffects().getAll().values().size() - 1;
            }

            int amount = RandomUtils.RandomRange(0, max);

            while (amount > 0) {

                BaseStatusEffect effect = null;

                while (effect == null || unit.statusEffects.containsKey(effect.GUID())) {
                    effect = RandomUtils.weightedRandom(SlashRegistry.StatusEffects()
                            .getAll()
                            .values());
                }
                amount--;
                unit.statusEffects.put(effect.GUID(), new StatusEffectData(effect));

            }
        }
    }
}
