package com.robertx22.mine_and_slash.database.world_providers;

import net.minecraft.world.GameRules;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraft.world.storage.WorldInfo;

public class MyWorldInfo extends DerivedWorldInfo {

    public MyWorldInfo(WorldInfo info) {
        super(info);
    }

    MyGameRules myRules = new MyGameRules(super.getGameRulesInstance());

    @Override
    public GameRules getGameRulesInstance() {
        return myRules;
    }

}
