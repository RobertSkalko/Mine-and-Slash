package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.generated.AllElementalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsStats;

public class ElementalPurity extends BaseGameChangerTrait implements IAffectsStats {

    private ElementalPurity() {
    }

    public static ElementalPurity getInstance() {
        return SingletonHolder.INSTANCE;
    }

    static int ELE_INCREASE = 25;
    static int PHYS_DECREASE = 50;

    @Override
    public String locDescForLangFile() {
        return "Decreases Physical damage by " + PHYS_DECREASE + " percent but increases all elemental damage by " + ELE_INCREASE + " percent";
    }

    @Override
    public String getIconPath() {
        return "game_changers/elemental_purity";
    }

    @Override
    public String locNameForLangFile() {
        return "Elemental Purity";
    }

    @Override
    public String GUID() {
        return "elemental_purity_trait";
    }

    @Override
    public void affectStats(EntityCap.UnitData data, StatData statData) {

        for (Stat stat : AllElementalDamage.MAP.getList()) {
            data.getUnit()
                .getCreateStat(stat).Flat += ELE_INCREASE;
        }

        data.getUnit()
            .getCreateStat(PhysicalDamage.getInstance()).Multi -= PHYS_DECREASE;
    }

    private static class SingletonHolder {
        private static final ElementalPurity INSTANCE = new ElementalPurity();
    }
}
