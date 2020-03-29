package com.robertx22.mine_and_slash.database.stats.types.misc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.generated.LootTypeBonus;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsStats;

public class LuckStat extends Stat implements IAffectsStats {

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases bonus loot and critical hit chance.";
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public String locNameForLangFile() {
        return "Luck";
    }

    @Override
    public String GUID() {
        return "luck";
    }

    @Override
    public void affectStats(EntityCap.UnitData data, StatData statData) {
        data.getUnit()
            .getCreateStat(CriticalHit.getInstance()).Flat += statData.Flat;
        new LootTypeBonus(LootType.Currency).generateAllPossibleStatVariations()
            .forEach(x -> data.getUnit()
                .getCreateStat(x).Flat += statData.Flat);
    }
}
