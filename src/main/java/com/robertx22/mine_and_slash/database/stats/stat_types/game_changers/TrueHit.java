package com.robertx22.mine_and_slash.database.stats.stat_types.game_changers;

import com.robertx22.mine_and_slash.database.stats.stat_effects.game_changers.HalveNonCritDmgEffect;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsStats;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class TrueHit extends BaseGameChangerTrait implements IStatEffects, IAffectsStats {

    private TrueHit() {
    }

    public static final TrueHit INSTANCE = new TrueHit();

    @Override
    public String locDescForLangFile() {
        return "Crit damage doubled, but non crit dmg is halved.";
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
    public void affectStats(EntityCap.UnitData data, StatData statData) {
        data.getUnit().getStat(CriticalDamage.GUID).Flat *= 2;
    }

    @Override
    public IStatEffect getEffect() {
        return HalveNonCritDmgEffect.INSTANCE;
    }
}
