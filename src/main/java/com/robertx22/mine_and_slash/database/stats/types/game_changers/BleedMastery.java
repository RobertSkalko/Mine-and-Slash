package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.game_changers.BleedMasteryEffect;
import com.robertx22.mine_and_slash.database.stats.types.generated.AllElementalDamage;
import com.robertx22.mine_and_slash.potion_effects.all.BleedPotion;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsStats;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class BleedMastery extends BaseGameChangerTrait implements IStatEffects, IAffectsStats {

    private BleedMastery() {
    }

    public static final BleedMastery INSTANCE = new BleedMastery();

    static int LOSE_PERC = 20;

    @Override
    public String getIconPath() {
        return "game_changers/bleed_mastery";
    }

    @Override
    public String locDescForLangFile() {
        return "Your phys basic attacks inflict " + BleedPotion.CALC.getMultiAsPercent() + " percent of your phys dmg as bleed on " + "targets but you lose " +
            LOSE_PERC + " percent of your elemental damage.";
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

    @Override
    public void affectStats(EntityCap.UnitData data, StatData statData) {
        for (Stat stat : AllElementalDamage.MAP.getList()) {
            data.getUnit()
                .getCreateStat(stat).Flat -= LOSE_PERC;
        }
    }
}


