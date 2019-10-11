package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.effects.game_changers.RefreshingBreezeEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class RefreshingBreeze extends BaseGameChangerTrait implements IStatEffects {

    private RefreshingBreeze() {
    }

    public static final RefreshingBreeze INSTANCE = new RefreshingBreeze();

    public static int PERCENT = 50;

    @Override
    public String locDescForLangFile() {
        return "Dodging restores " + PERCENT + "% of your energy regen";
    }

    @Override
    public String getIconPath() {
        return "game_changers/breeze";
    }

    @Override
    public String locNameForLangFile() {
        return "Refreshing Breeze";
    }

    @Override
    public String GUID() {
        return "refreshing_breeze_trait";
    }

    @Override
    public IStatEffect getEffect() {
        return RefreshingBreezeEffect.INSTANCE;
    }
}
