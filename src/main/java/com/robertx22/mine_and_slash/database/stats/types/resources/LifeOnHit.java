package com.robertx22.mine_and_slash.database.stats.types.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.resource.LifeOnHitEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class LifeOnHit extends Stat implements IStatEffects {

    public static String GUID = "life_on_hit";

    public static LifeOnHit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Regeneration;
    }

    @Override
    public String locDescForLangFile() {
        return "Gives health on basic attack hit";
    }

    @Override
    public IStatEffect getEffect() {
        return new LifeOnHitEffect();
    }

    private LifeOnHit() {
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public String locNameForLangFile() {
        return "Life on Hit";
    }

    private static class SingletonHolder {
        private static final LifeOnHit INSTANCE = new LifeOnHit();
    }
}
