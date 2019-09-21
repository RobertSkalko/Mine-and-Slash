package com.robertx22.mine_and_slash.uncommon.stat_calculation;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.*;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.WornSetsContainerData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatModsContainer;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

import java.util.List;
import java.util.Map;

public class PlayerStatUtils {

    public static void AddPlayerBaseStats(UnitData data, Unit unit) {

        addScalingStat(data, SpellDamage.GUID, ModConfig.INSTANCE.PlayerBaseStats.spell_damage
                .get(), ModConfig.INSTANCE.PlayerBaseStats.spell_damage_per_level.get());

        addScalingStat(data, PhysicalDamage.GUID, ModConfig.INSTANCE.PlayerBaseStats.physical_damage
                .get(), ModConfig.INSTANCE.PlayerBaseStats.physical_damage_per_level.get());

        addScalingStat(data, HealthRegen.GUID, ModConfig.INSTANCE.PlayerBaseStats.health_regen
                .get(), ModConfig.INSTANCE.PlayerBaseStats.health_regen_per_level.get());

        addScalingStat(data, Armor.GUID, ModConfig.INSTANCE.PlayerBaseStats.armor.get(), ModConfig.INSTANCE.PlayerBaseStats.armor_per_level
                .get());

        addScalingStat(data, Health.GUID, ModConfig.INSTANCE.PlayerBaseStats.health.get(), ModConfig.INSTANCE.PlayerBaseStats.health_per_level
                .get());

        addScalingStat(data, CriticalHit.GUID, ModConfig.INSTANCE.PlayerBaseStats.critical_hit
                .get(), ModConfig.INSTANCE.PlayerBaseStats.critical_hit_per_level.get());

        addScalingStat(data, CriticalDamage.GUID, ModConfig.INSTANCE.PlayerBaseStats.critical_damage
                .get(), ModConfig.INSTANCE.PlayerBaseStats.critical_damage_per_level.get());

        addScalingStat(data, ManaRegen.GUID, ModConfig.INSTANCE.PlayerBaseStats.mana_regen
                .get(), ModConfig.INSTANCE.PlayerBaseStats.mana_regen_per_level.get());

        addScalingStat(data, EnergyRegen.GUID, ModConfig.INSTANCE.PlayerBaseStats.energy_regen
                .get(), ModConfig.INSTANCE.PlayerBaseStats.energy_regen_per_level.get());

        addScalingStat(data, Energy.GUID, ModConfig.INSTANCE.PlayerBaseStats.energy.get(), ModConfig.INSTANCE.PlayerBaseStats.energy_per_level
                .get());

        addScalingStat(data, Mana.GUID, ModConfig.INSTANCE.PlayerBaseStats.mana.get(), ModConfig.INSTANCE.PlayerBaseStats.mana_per_level
                .get());

    }

    private static void addScalingStat(UnitData data, String stat, double base,
                                       double perlvl) {
        data.getUnit().getStat(stat).Flat += base;
        data.getUnit().getStat(stat).addFlat((float) (perlvl), data.getLevel());
    }

    public static void CountWornSets(Entity entity, List<GearItemData> gears, Unit unit) {

        unit.wornSets = new WornSetsContainerData();

        for (GearItemData gear : gears) {
            unit.wornSets.addSet(gear);
        }

    }

    public static void AddAllSetStats(Entity entity, UnitData data, Unit unit,
                                      int level) {

        unit.wornSets.AddAllSetStats(data);

    }

    // if at end of stat calculation you still don't meet the gear requirements, apply penalty
    public static void applyRequirementsUnmetPenalty(Entity en, UnitData data,
                                                     List<GearItemData> gears) {

        float penalty = 1;
        for (GearItemData gear : gears) {
            if (!gear.meetsRequirements(data)) {
                penalty -= 0.1F;
            }
        }

        penalty = MathHelper.clamp(penalty, 0.25F, 1);

        if (penalty < 1) {

            for (Map.Entry<String, StatData> entry : data.getUnit()
                    .getStats()
                    .entrySet()) {
                if (entry.getValue().Value > 0) {
                    entry.getValue().Value *= penalty;
                }
            }

        }

    }

    public static void AddAllGearStats(Entity entity, List<GearItemData> gears,
                                       UnitData unitdata, int level) {

        boolean gearTooHighLevel = false;

        for (GearItemData gear : gears) {
            if (gear.level > unitdata.getLevel()) {
                gearTooHighLevel = true;

            } else {

                List<IStatModsContainer.LevelAndStats> levelstats = gear.GetAllStats(gear.level);
                for (IStatModsContainer.LevelAndStats datas : levelstats) {
                    for (StatModData data : datas.mods) {

                        StatMod mod = data.getStatMod();

                        if (mod == null) {
                            //  System.out.println(data.baseModName + " is null");
                        } else {
                            Stat stat = data.getStatMod().GetBaseStat();

                            if (stat != null) {
                                StatData statdata = unitdata.getUnit().getStat(stat);
                                if (statdata != null) {
                                    data.Add(statdata, datas.level);
                                }
                            }
                        }
                    }

                }
            }
        }

        if (gearTooHighLevel) {
            if (entity instanceof PlayerEntity) {
                entity.sendMessage(Chats.A_Piece_of_gear_is_too_high_level_for_you.locName());
            }
        }

    }

}
