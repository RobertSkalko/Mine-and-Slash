package com.robertx22.mine_and_slash.database.stats.stat_types.game_changers;

import com.robertx22.mine_and_slash.database.stats.stat_effects.game_changers.BleedMasteryEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class BleedMastery extends BaseGameChangerTrait implements IStatEffects {

    private BleedMastery() {
    }

    public static final BleedMastery INSTANCE = new BleedMastery();

    @Override
    public String getIconPath() {
        return "game_changers/bleed_mastery";
    }

    @Override
    public String locDescForLangFile() {
        return "Your phys basic attacks inflict % of your phys dmg as bleed on targets";
    }

    @Override
    public String locNameForLangFile() {
        return "Bleed Mastery";
    }

    @Override
    public String GUID() {
        return "bleed_mastery_trait";
    }

    @Override
    public IStatEffect getEffect() {
        return BleedMasteryEffect.INSTANCE;
    }
}


