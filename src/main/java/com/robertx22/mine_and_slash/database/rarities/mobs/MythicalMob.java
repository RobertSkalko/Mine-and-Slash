package com.robertx22.mine_and_slash.database.rarities.mobs;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseMythical;

public class MythicalMob extends BaseMythical implements MobRarity {

    private MythicalMob() {
    }

    public static MythicalMob getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public float StatMultiplier() {
        return 4F;
    }

    @Override
    public float DamageMultiplier() {
        return 2.3F;
    }

    @Override
    public float HealthMultiplier() {
        return 6F;
    }

    @Override
    public float LootMultiplier() {
        return 13F;
    }

    @Override
    public int MaxMobEffects() {
        return 3;
    }

    @Override
    public float ExpOnKill() {
        return 80;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MOBS.MYTHICAL_WEIGHT.get();
    }

    private static class SingletonHolder {
        private static final MythicalMob INSTANCE = new MythicalMob();
    }
}
