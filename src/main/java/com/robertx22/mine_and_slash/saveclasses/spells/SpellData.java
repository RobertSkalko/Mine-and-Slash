package com.robertx22.mine_and_slash.saveclasses.spells;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.math.MathHelper;

@Storable
public class SpellData {

    @Store
    private int cooldownInTicks = 0;

    public boolean cooldownIsReady() {
        return cooldownInTicks <= 0;
    }

    public void setCooldown(int ticks) {
        this.cooldownInTicks = ticks;
    }

    public void tickCooldown(int ticks) {
        cooldownInTicks = MathHelper.clamp(cooldownInTicks - ticks, 0, Integer.MAX_VALUE);
    }
}

