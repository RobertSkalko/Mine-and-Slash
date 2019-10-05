package com.robertx22.mine_and_slash.database.stats.stat_types.game_changers;

import com.robertx22.mine_and_slash.database.stats.stat_effects.game_changers.HarmonyEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class Harmony extends BaseGameChangerTrait implements IStatEffects {

    private Harmony() {
    }

    public static final Harmony INSTANCE = new Harmony();

    @Override
    public String locDescForLangFile() {
        return "Half of your hp restoration effects go to your magic shield instead";
    }

    @Override
    public String locNameForLangFile() {
        return "Harmony";
    }

    @Override
    public String GUID() {
        return "harmony_trait";
    }

    @Override
    public IStatEffect getEffect() {
        return HarmonyEffect.INSTANCE;
    }
}


