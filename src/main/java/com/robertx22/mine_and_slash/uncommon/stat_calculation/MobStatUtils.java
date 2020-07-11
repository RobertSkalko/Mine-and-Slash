package com.robertx22.mine_and_slash.uncommon.stat_calculation;

import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfig;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalPenetration;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
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

    public static void addAffixStats(UnitData data) {

        if (data.getUnit()
            .getPrefix() != null) {
            data.getUnit()
                .getPrefix()
                .applyStats(data);
        }
        if (data.getUnit()
            .getSuffix() != null) {
            data.getUnit()
                .getSuffix()
                .applyStats(data);
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
            if (stat instanceof WeaponDamage || stat instanceof ElementalSpellDamage || stat instanceof CriticalDamage || stat instanceof CriticalHit) {
                data.multiplyFlat(config.DMG_MULTI);
            } else if (data.getId()
                .equals(Health.GUID)) {
                data.multiplyFlat(config.HP_MULTI);
            } else {
                data.multiplyFlat(config.STAT_MULTI);
            }
        }

    }

    public static void AddMobcStats(UnitData unitdata, LivingEntity en) {

        MobRarity rar = Rarities.Mobs.get(unitdata.getRarity());
        Unit unit = unitdata.getUnit();

        unit.getCreateStat(Health.getInstance())
            .addFlat(en.getMaxHealth() * rar.HealthMultiplier());

        unit.getCreateStat(Armor.GUID)
            .addFlat(Armor.getInstance()
                .AverageStat() * rar.StatMultiplier());
        unit.getCreateStat(CriticalHit.GUID)
            .addFlat(5 * rar.DamageMultiplier());
        unit.getCreateStat(CriticalDamage.GUID)
            .addFlat(5 * rar.DamageMultiplier());

        ElementalResist.MAP.getList()
            .forEach(x -> unit.getCreateStat(x)
                .addFlat(5 * rar.StatMultiplier()));

        ElementalSpellDamage.MAP.getList()
            .forEach(x -> unit.getCreateStat(x)
                .addFlat(spelldmg * rar.DamageMultiplier()));

        ElementalPenetration.MAP.getList()
            .forEach(x -> unit.getCreateStat(x)
                .addFlat(4 * rar.DamageMultiplier()));

    }

}
