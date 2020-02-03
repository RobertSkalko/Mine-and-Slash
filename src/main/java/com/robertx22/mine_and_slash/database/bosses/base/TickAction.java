package com.robertx22.mine_and_slash.database.bosses.base;

import net.minecraft.entity.LivingEntity;

import java.util.function.Function;

public class TickAction {

    private int ticks;
    Function<LivingEntity, LivingEntity> action;

    public TickAction(int ticks, Function<LivingEntity, LivingEntity> action) {
        this.ticks = ticks;
        this.action = action;
    }

    public final void onTick(LivingEntity en) {
        if (en.ticksExisted % ticks == 0) {
            action.apply(en);
        }
    }

}
