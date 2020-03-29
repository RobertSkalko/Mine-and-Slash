package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.game_changers.ManaBatteryEffect;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class ManaBattery extends BaseGameChangerTrait implements IStatEffects {

    private ManaBattery() {
    }

    public static final Stat INSTANCE = new ManaBattery();

    @Override
    public String locDescForLangFile() {
        return "While mana is above 50 percent absorb 25 percent of damage by spending double that in mana.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/mana_battery";
    }

    @Override
    public String locNameForLangFile() {
        return "Mana Battery";
    }

    @Override
    public String GUID() {
        return "mana_battery_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {
        return Arrays.asList(
            new ExactStatData(-50, StatModTypes.Multi, DodgeRating.getInstance()),
            new ExactStatData(-15, StatModTypes.Multi, Health.getInstance())
        );
    }

    @Override
    public IStatEffect getEffect() {
        return ManaBatteryEffect.INSTANCE;
    }
}


