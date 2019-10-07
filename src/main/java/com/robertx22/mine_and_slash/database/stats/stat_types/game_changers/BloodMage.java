package com.robertx22.mine_and_slash.database.stats.stat_types.game_changers;

import com.robertx22.mine_and_slash.database.stats.stat_effects.game_changers.BloodMageEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class BloodMage extends BaseGameChangerTrait implements IStatEffects {

    private BloodMage() {
    }

    public static final BloodMage INSTANCE = new BloodMage();

    @Override
    public String locDescForLangFile() {
        return "Your mana is transformed into blood. You replenish blood with any non spell related health restoration method like hp regen or lifesteal.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/blood_mage";
    }

    @Override
    public String locNameForLangFile() {
        return "Blood Mage";
    }

    @Override
    public String GUID() {
        return "blood_mage_trait";
    }

    @Override
    public IStatEffect getEffect() {
        return BloodMageEffect.INSTANCE;
    }
}


