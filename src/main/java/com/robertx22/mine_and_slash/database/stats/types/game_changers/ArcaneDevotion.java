package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsStats;

public class ArcaneDevotion extends BaseGameChangerTrait implements IAffectsStats {

    static int MS = 50;

    private ArcaneDevotion() {
    }

    public static ArcaneDevotion getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String locDescForLangFile() {
        return "Your health, health regen and dodge are set to minimum. Multiplies Magic shield by " + MS + " percent";
    }

    @Override
    public String getIconPath() {
        return "game_changers/arcane_devotion";
    }

    @Override
    public String locNameForLangFile() {
        return "Arcane Devotion";
    }

    @Override
    public String GUID() {
        return "arcane_devotion_trait";
    }

    @Override
    public void affectStats(EntityCap.UnitData data, StatData statData) {

        data.getUnit().getCreateStat(Health.getInstance()).Multi -= 1000;
        data.getUnit().getCreateStat(HealthRegen.getInstance()).Multi -= 1000;
        data.getUnit().getCreateStat(DodgeRating.getInstance()).Multi -= 1000;

        data.getUnit().getCreateStat(MagicShield.getInstance()).Multi += MS;

    }

    private static class SingletonHolder {
        private static final ArcaneDevotion INSTANCE = new ArcaneDevotion();
    }
}
