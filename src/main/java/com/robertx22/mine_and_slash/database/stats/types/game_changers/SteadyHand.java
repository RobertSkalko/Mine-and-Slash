package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.effects.game_changers.SteadyHandEffect;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsStats;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class SteadyHand extends BaseGameChangerTrait implements IStatEffects, IAffectsStats {

    private SteadyHand() {
    }

    public static final SteadyHand INSTANCE = new SteadyHand();

    public static int DMG_INCREASE_PERCENT = 50;

    @Override
    public String locDescForLangFile() {
        return "Your crit chance and crit dmg is 0. All your attacks deal " + DMG_INCREASE_PERCENT + " percent more " + "damage.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/steady_hand";
    }

    @Override
    public String locNameForLangFile() {
        return "Steady Hand";
    }

    @Override
    public String GUID() {
        return "steady_hand_trait";
    }

    @Override
    public IStatEffect getEffect() {
        return SteadyHandEffect.INSTANCE;
    }

    @Override
    public void affectStats(EntityCap.UnitData data, StatData statData) {
        data.getUnit().getCreateStat(CriticalHit.GUID).Flat -= 1000;
        data.getUnit().getCreateStat(CriticalDamage.GUID).Flat -= 1000;
    }
}
