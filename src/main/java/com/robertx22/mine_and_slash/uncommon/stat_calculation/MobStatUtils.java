package com.robertx22.mine_and_slash.uncommon.stat_calculation;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfig;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalPene;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.effects.StatusEffectData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import java.util.stream.Collectors;

public class MobStatUtils {

    static int spelldmg = 12;

    public static void increaseMobStatsPerTier(UnitData mobdata, Unit unit) {

        for (StatData data : unit.getStats()
            .values()
            .stream()
            .filter(x -> {
                return x.GetStat()
                    .IsPercent() == false;
            })
            .collect(Collectors.toList())) {

            data.multiplyFlat(mobdata.getMapTier().mob_stat_multi);
        }

    }

    public static void increaseMobStatsPerLevel(UnitData mobdata) {

        float lvlMulti = 1 + (ModConfig.INSTANCE.Server.MOB_STRENGTH_PER_LEVEL_MULTI.get()
            .floatValue() * mobdata.getLevel());

        for (StatData data : mobdata.getUnit()
            .getStats()
            .values()
            .stream()
            .filter(x -> x.GetStat()
                .IsPercent() == false)
            .collect(Collectors.toList())) {

            data.multiplyFlat(lvlMulti);
        }

    }

    public static void worldMultiplierStats(World world, Unit unit) {
        for (StatData stat : unit.getStats()
            .values()) {
            stat.multiplyFlat(SlashRegistry.getDimensionConfig(world).MOB_STRENGTH_MULTIPLIER);
        }

    }

    public static void modifyMobStatsByConfig(LivingEntity entity, UnitData unitdata) {

        Unit unit = unitdata.getUnit();
        ModEntityConfig config = SlashRegistry.getEntityConfig(entity, unitdata);

        for (StatData data : unit.getStats()
            .values()) {
            Stat stat = data.GetStat();
            if (stat instanceof PhysicalDamage || stat instanceof ElementalSpellDamage || stat instanceof CriticalDamage || stat instanceof CriticalHit) {
                data.multiplyFlat(config.DMG_MULTI);
            } else if (data.getId()
                .equals(Health.GUID)) {
                data.multiplyFlat(config.HP_MULTI);
            } else {
                data.multiplyFlat(config.STAT_MULTI);
            }
        }

    }

    public static void AddMobcStats(UnitData unitdata, int level) {

        MobRarity rar = Rarities.Mobs.get(unitdata.getRarity());
        Unit unit = unitdata.getUnit();

        unit.getCreateStat(Armor.GUID)
            .addFlat(Armor.getInstance()
                .AverageStat() * rar.StatMultiplier(), level);
        unit.getCreateStat(CriticalHit.GUID)
            .addFlat(5 * rar.DamageMultiplier());
        unit.getCreateStat(CriticalDamage.GUID)
            .addFlat(5 * rar.DamageMultiplier());

        ElementalResist.MAP.getList()
            .forEach(x -> unit.getCreateStat(x)
                .addFlat(5 * rar.StatMultiplier(), level));

        ElementalSpellDamage.MAP.getList()
            .forEach(x -> unit.getCreateStat(x)
                .addFlat(spelldmg * rar.DamageMultiplier(), level));

        ElementalPene.MAP.getList()
            .forEach(x -> unit.getCreateStat(x)
                .addFlat(4 * rar.DamageMultiplier(), level));

    }

    // this apparently takes 60 ms
    public static void AddRandomMobStatusEffects(LivingEntity entity, Unit unit, MobRarity rarity) {

        int max = rarity.MaxMobEffects();

        if (max > 0) {
            if (max > SlashRegistry.StatusEffects()
                .getSize()) {
                System.out.println("ERROR! Can't have more unique effects than there are effects!");
                max = SlashRegistry.StatusEffects()
                    .getSize() - 1;
            }

            int amount = RandomUtils.RandomRange(0, max);

            SlashRegistry.StatusEffects()
                .getWrapped()
                .randomAmountWithoutDuplicates(amount).list.forEach(
                x -> unit.statusEffects.put(x.GUID(), new StatusEffectData(x)));

        }
    }
}
