package com.robertx22.mine_and_slash.database.world_providers;

import net.minecraft.world.GameRules;

public class MyGameRules extends GameRules {

    @Override
    public boolean getBoolean(GameRules.RuleKey<GameRules.BooleanValue> val) {
        if (val == GameRules.DO_FIRE_TICK) {
            return false;
        } else {
            return super.getBoolean(val);
        }
    }
}
