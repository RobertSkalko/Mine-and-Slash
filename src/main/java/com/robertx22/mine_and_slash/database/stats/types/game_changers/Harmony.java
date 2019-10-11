package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.effects.game_changers.HarmonyEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class Harmony extends BaseGameChangerTrait implements IStatEffects {

    private Harmony() {
    }

    public static final Harmony INSTANCE = new Harmony();

    @Override
    public String locDescForLangFile() {
        return "Half of your hp restoration effects go to your magic shield instead. This for example means your heal spells only heal half of the value to health and other half to magic shield. ";
    }

    @Override
    public String getIconPath() {
        return "game_changers/harmony";
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


