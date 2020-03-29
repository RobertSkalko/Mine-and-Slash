package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.effects.game_changers.HalveNonCritDmgEffect;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class TrueHit extends BaseGameChangerTrait implements IStatEffects {

    private TrueHit() {
    }

    public static final TrueHit INSTANCE = new TrueHit();

    @Override
    public String locDescForLangFile() {
        return "Non crit dmg is halved.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/true_hit";
    }

    @Override
    public String locNameForLangFile() {
        return "True Hit";
    }

    @Override
    public String GUID() {
        return "true_hit_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {
        return Arrays.asList(
            new ExactStatData(30, StatModTypes.Multi, CriticalDamage.getInstance())
        );
    }

    @Override
    public IStatEffect getEffect() {
        return HalveNonCritDmgEffect.INSTANCE;
    }
}
