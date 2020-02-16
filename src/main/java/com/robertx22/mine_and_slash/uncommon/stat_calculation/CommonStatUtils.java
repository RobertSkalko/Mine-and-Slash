package com.robertx22.mine_and_slash.uncommon.stat_calculation;

import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.ICoreStat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.IPreCoreStat;
import com.robertx22.mine_and_slash.db_lists.initializers.Stats;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.effects.StatusEffectData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatModsContainer;
import com.robertx22.mine_and_slash.saveclasses.mapitem.MapAffixData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.WorldMapCap;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsStats;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatConversion;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatTransfer;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;

import java.util.List;

public class CommonStatUtils {

    public static void addCustomStats(UnitData data, Unit unit, int level) {
        for (StatModData stat : data.getCustomStats().stats.values()) {
            stat.useOnPlayer(data);
        }
    }

    public static void addExactCustomStats(UnitData data) {
        for (ExactStatData stat : data.getCustomExactStats().stats.values()) {
            stat.applyStats(data);
        }
    }


    public static void addPotionStats(LivingEntity entity, UnitData data) {

        for (EffectInstance instance : entity.getActivePotionEffects()) {
            if (instance.getPotion() instanceof IApplyStatPotion) {
                IApplyStatPotion stat = (IApplyStatPotion) instance.getPotion();
                try {
                    stat.applyStats(data, instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void AddStatusEffectStats(Unit unit, int level) {

        for (StatusEffectData status : unit.statusEffects.values()) {
            List<IStatModsContainer.LevelAndStats> levelsandstats = status.GetAllStats(level);
            for (IStatModsContainer.LevelAndStats levelstat : levelsandstats) {
                for (StatModData data : levelstat.mods) {

                    StatData stat = unit.getCreateStat(data.getStatMod().GetBaseStat());
                    if (stat == null) {

                    } else {
                        data.Add(stat, level);
                    }
                }
            }
        }

    }

    public static void CalcTraitsAndCoreStats(UnitData unit) {

        Unit theunit = unit.getUnit();

        for (IPreCoreStat core : Stats.allPreGenMapStatLists.get(IPreCoreStat.class)) {

            StatData statdata = theunit.peekAtStat(core.GUID());
            if (statdata.val > 0) {
                core.addToCoreStats(unit, statdata);
            }

        }
        for (ICoreStat core : Stats.allPreGenMapStatLists.get(ICoreStat.class)) {

            StatData statdata = theunit.peekAtStat(core.GUID());
            if (statdata.val > 0) {
                core.addToOtherStats(unit, statdata);
            }

        }
        for (Trait trait : Stats.allPreGenMapStatLists.get(Trait.class)) {

            StatData statdata = theunit.peekAtStat(trait.GUID());
            if (statdata.val > 0) {
                trait.TryAffectOtherStats(unit, statdata);
            }

        }
        for (IAffectsStats trait : Stats.allPreGenMapStatLists.get(IAffectsStats.class)) {

            StatData statdata = theunit.peekAtStat(trait.GUID());
            if (statdata.val > 0) {
                trait.affectStats(unit, statdata);
            }

        }
    }

    /**
     * A unit copy is needed so there's no randomness to stat transfers and
     * conversions. All changes are based on old copy but applied to the unit that's
     * used
     */
    public static void CalcStatConversionsAndTransfers(Unit copy, Unit unit) {

        for (IStatConversion core : Stats.allPreGenMapStatLists.get(IStatConversion.class)) {

            StatData statdata = copy.peekAtStat(core.GUID());
            if (statdata.val > 0) {
                core.convertStats(copy, unit, copy.getCreateStat(core.GUID()));
            }

        }
        for (IStatTransfer core : Stats.allPreGenMapStatLists.get(IStatTransfer.class)) {

            StatData statdata = copy.peekAtStat(core.GUID());
            if (statdata.val > 0) {
                core.transferStats(copy, unit, copy.getCreateStat(core.GUID()));
            }

        }

    }

    public static void AddMapAffixStats(WorldMapCap.IWorldMapData mapdata, Unit unit, int level, LivingEntity entity) {

        for (MapAffixData status : WorldUtils.getAllAffixesThatAffect(mapdata, entity)) {
            List<StatModData> datas = status.GetAllStats();
            for (StatModData data : datas) {
                StatData stat = unit.getCreateStat(data.getStatMod().GetBaseStat());
                if (stat == null) {
                } else {

                    data.Add(stat, level);

                }
            }
        }

    }

}
