package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsStats;

public class OverflowingVitality extends BaseGameChangerTrait implements IAffectsStats {

    static int INCREASE = 2;
    static int ELE_DECREASE = 25;

    private OverflowingVitality() {
    }

    public static OverflowingVitality getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases your physical damage by " + INCREASE + " Percent of your Health, but decrease all " +
            "elemental attack damage by " + ELE_DECREASE + " percent.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/overflowing_vitality";
    }

    @Override
    public String locNameForLangFile() {
        return "Overflowing Vitality";
    }

    @Override
    public String GUID() {
        return "overflowing_vitality_trait";
    }

    @Override
    public void affectStats(EntityCap.UnitData data, StatData statData) {

        float num = data.getUnit()
            .getCreateStat(Health.getInstance())
            .getAverageValue() * INCREASE / 100;

        for (Stat stat : ElementalAttackDamage.MAP.getList()) {
            data.getUnit()
                .getCreateStat(stat)
                .addMulti(-ELE_DECREASE);
        }

        data.getUnit()
            .getCreateStat(PhysicalDamage.getInstance())
            .addFlat(+num);
    }

    private static class SingletonHolder {
        private static final OverflowingVitality INSTANCE = new OverflowingVitality();
    }
}
