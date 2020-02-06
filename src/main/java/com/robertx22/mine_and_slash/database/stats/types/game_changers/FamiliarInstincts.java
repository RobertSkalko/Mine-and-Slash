package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsStats;

public class FamiliarInstincts extends BaseGameChangerTrait implements IAffectsStats {

    static int INC = 10;
    static int DEC = 20;

    private FamiliarInstincts() {
    }

    public static FamiliarInstincts getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String locDescForLangFile() {
        return "Adds " + INC + " percent of your dodge to all your elemental resistances, but reduces health regen " + "by" + " " + DEC + " percent";
    }

    @Override
    public String getIconPath() {
        return "game_changers/familiar_instincts";
    }

    @Override
    public String locNameForLangFile() {
        return "Familiar Instincts";
    }

    @Override
    public String GUID() {
        return "familiar_instincts_trait";
    }

    @Override
    public void affectStats(EntityCap.UnitData data, StatData statData) {

        float num = data.getUnit().getCreateStat(DodgeRating.INSTANCE).Flat * INC / 100;

        for (Stat stat : ElementalResist.MAP.getList()) {
            data.getUnit().getCreateStat(stat).Flat += num;
        }

        data.getUnit().getCreateStat(HealthRegen.INSTANCE).Multi -= DEC;
    }

    private static class SingletonHolder {
        private static final FamiliarInstincts INSTANCE = new FamiliarInstincts();
    }
}

