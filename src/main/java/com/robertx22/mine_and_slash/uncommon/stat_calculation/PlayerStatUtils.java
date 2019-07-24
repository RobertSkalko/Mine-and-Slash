package com.robertx22.mine_and_slash.uncommon.stat_calculation;

import com.robertx22.mine_and_slash.a_libraries.curios.MyCurioUtils;
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
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatUtils {

    public static void AddPlayerBaseStats(UnitData data, Unit unit) {

        int lvl = data.getLevel();

        unit.getStat(PhysicalDamage.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.physical_damage
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.physical_damage_per_level
                .get());

        unit.getStat(Mana.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.mana.get() + data
                .getLevel() * ModConfig.INSTANCE.PlayerBaseStats.mana_per_level.get());

        unit.getStat(Energy.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.energy
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.energy_per_level.get());

        unit.getStat(ManaRegen.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.mana_regen
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.mana_regen_per_level.get());

        unit.getStat(EnergyRegen.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.energy_regen
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.energy_regen_per_level.get());

        unit.getStat(HealthRegen.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.health_regen
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.health_regen_per_level.get());

        unit.getStat(Armor.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.armor.get() + data
                .getLevel() * ModConfig.INSTANCE.PlayerBaseStats.armor_per_level.get());

        unit.getStat(Health.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.health
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.health_per_level.get());

        unit.getStat(CriticalHit.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.critical_hit
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.critical_hit_per_level.get());

        unit.getStat(CriticalDamage.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.critical_damage
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.critical_damage_per_level
                .get());

        unit.getStat(SpellDamage.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.spell_damage
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.spell_damage_per_level.get());

    }

    public static List<GearItemData> getEquipsExcludingWeapon(LivingEntity entity) {

        List<ItemStack> list = new ArrayList<ItemStack>();

        for (ItemStack stack : entity.getArmorInventoryList()) {
            if (stack != null) {
                list.add(stack);
            }
        }

        if (entity instanceof PlayerEntity) {

            list.addAll(MyCurioUtils.getAllSlots((PlayerEntity) entity));

        }
        List<GearItemData> gearitems = new ArrayList<GearItemData>();

        for (ItemStack stack : list) {
            GearItemData gear = Gear.Load(stack);
            if (gear != null) {
                gearitems.add(gear);
            }

        }

        return gearitems;

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

    public static void AddAllGearStats(Entity entity, List<GearItemData> gears, Unit unit,
                                       int level) {

        boolean gearTooHighLevel = false;

        for (GearItemData gear : gears) {
            if (gear.level > level) {
                gearTooHighLevel = true;

            } else {

                List<IStatsContainer.LevelAndStats> levelstats = gear.GetAllStats(gear.level);
                for (IStatsContainer.LevelAndStats datas : levelstats) {
                    for (StatModData data : datas.mods) {

                        StatMod mod = data.getStatMod();

                        if (mod == null) {
                            //  System.out.println(data.baseModName + " is null");
                        } else {
                            Stat stat = data.getStatMod().GetBaseStat();

                            if (stat != null) {
                                StatData statdata = unit.getStat(stat);
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
