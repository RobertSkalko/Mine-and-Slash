package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.game_changers.ManaBatteryEffect;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsStats;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class ManaBattery extends BaseGameChangerTrait implements IStatEffects, IAffectsStats {

    private ManaBattery() {
    }

    public static final Stat INSTANCE = new ManaBattery();

    @Override
    public String locDescForLangFile() {
        return "While mana is above 25% absorb half of damage by spending mana. Dodge rating halved.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/mana_battery";
    }

    @Override
    public String locNameForLangFile() {
        return "Mana Battery";
    }

    @Override
    public String GUID() {
        return "mana_battery_trait";
    }

    @Override
    public void affectStats(EntityCap.UnitData data, StatData statData) {
        data.getUnit().getCreateStat(DodgeRating.GUID).Multi -= 50;
    }

    @Override
    public IStatEffect getEffect() {
        return ManaBatteryEffect.INSTANCE;
    }
}


