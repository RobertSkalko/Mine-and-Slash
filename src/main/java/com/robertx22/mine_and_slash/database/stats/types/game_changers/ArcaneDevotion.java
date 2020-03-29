package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.Arrays;
import java.util.List;

public class ArcaneDevotion extends BaseGameChangerTrait {

    static int MS = 50;

    private ArcaneDevotion() {
    }

    public static ArcaneDevotion getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String locDescForLangFile() {
        return "Completely devote yourself to the arcane.";
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
    public List<ExactStatData> getExactStats() {
        return Arrays.asList(
            new ExactStatData(-1000, StatModTypes.Multi, Health.getInstance()),
            new ExactStatData(-1000, StatModTypes.Multi, HealthRegen.getInstance()),
            new ExactStatData(-1000, StatModTypes.Multi, DodgeRating.getInstance()),
            new ExactStatData(MS, StatModTypes.Multi, MagicShield.getInstance())
        );
    }

    private static class SingletonHolder {
        private static final ArcaneDevotion INSTANCE = new ArcaneDevotion();
    }
}
