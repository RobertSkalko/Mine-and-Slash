package com.robertx22.mine_and_slash.uncommon.stat_calculation;

import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.ICoreStat;
import com.robertx22.mine_and_slash.db_lists.initializers.Stats;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.unit.StatData;
import com.robertx22.mine_and_slash.saveclasses.unit.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsStats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;

public class CommonStatUtils {

    public static void addExactCustomStats(UnitData data) {
        for (ExactStatData stat : data.getCustomExactStats().stats.values()) {
            stat.applyStats(data);
        }
    }

    public static void addPotionStats(LivingEntity entity, UnitData data) {

        PlayerSpellCap.ISpellsCap cap = Load.spells(entity);

        for (EffectInstance instance : entity.getActivePotionEffects()) {
            if (instance.getPotion() instanceof IApplyStatPotion) {
                IApplyStatPotion stat = (IApplyStatPotion) instance.getPotion();
                try {
                    stat.applyStats(data, cap, instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void CalcTraitsAndCoreStats(UnitData unit) {

        Unit theunit = unit.getUnit();

        for (ICoreStat core : Stats.allPreGenMapStatLists.get(ICoreStat.class)) {

            StatData statdata = theunit.peekAtStat(core.GUID());
            if (statdata.isMoreThanZero()) {
                core.addToOtherStats(unit, statdata);
            }

        }

        for (IAffectsStats trait : Stats.allPreGenMapStatLists.get(IAffectsStats.class)) {

            StatData statdata = theunit.peekAtStat(trait.GUID());
            if (statdata.isMoreThanZero()) {
                trait.affectStats(unit, statdata);
            }

        }
    }

}
