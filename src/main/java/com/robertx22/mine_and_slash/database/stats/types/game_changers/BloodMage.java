package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.effects.game_changers.BloodMageRestoreManaEffect;
import com.robertx22.mine_and_slash.database.stats.effects.game_changers.BloodMageSpendBloodInsteadEffect;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.database.stats.types.resources.ManaRegen;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatMultipleEffects;

import java.util.Arrays;
import java.util.List;

public class BloodMage extends BaseGameChangerTrait implements IStatMultipleEffects {

    private BloodMage() {
    }

    public static final BloodMage INSTANCE = new BloodMage();

    static int HP_DECREASE = 25;

    @Override
    public String locDescForLangFile() {
        return "You have no mana, you use blood instead. Max blood is half of your health. You replenish blood with "
            + "any non spell related health restoration method like hp regen or lifesteal.";
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
    public List<IStatEffect> getEffects() {
        return Arrays.asList(BloodMageSpendBloodInsteadEffect.getInstance(), BloodMageRestoreManaEffect.getInstance());
    }

    @Override
    public List<ExactStatData> getExactStats() {
        return Arrays.asList(
            new ExactStatData(-HP_DECREASE, StatModTypes.Multi, Health.getInstance()),
            new ExactStatData(-1000, StatModTypes.Multi, Mana.getInstance()),
            new ExactStatData(-1000, StatModTypes.Multi, ManaRegen.getInstance()),
            new ExactStatData(-HP_DECREASE, StatModTypes.Multi, HealthRegen.getInstance())
        );
    }

}


