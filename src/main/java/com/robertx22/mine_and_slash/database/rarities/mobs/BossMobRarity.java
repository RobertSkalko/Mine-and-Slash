package com.robertx22.mine_and_slash.database.rarities.mobs;

import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseBossRarity;

public class BossMobRarity extends BaseBossRarity implements MobRarity {

    private BossMobRarity() {
    }

    public static BossMobRarity getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public float StatMultiplier() {
        return 5F;
    }

    @Override
    public float DamageMultiplier() {
        return 3F;
    }

    @Override
    public float HealthMultiplier() {
        return 20F;
    }

    @Override
    public float LootMultiplier() {
        return 30F;
    }

    @Override
    public int MaxMobEffects() {
        return 2;
    }

    @Override
    public float ExpOnKill() {
        return 300;
    }

    @Override
    public int Weight() {
        return 0;
    }

    private static class SingletonHolder {
        private static final BossMobRarity INSTANCE = new BossMobRarity();
    }
}

