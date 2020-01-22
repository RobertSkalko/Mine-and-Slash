package com.robertx22.mine_and_slash.potion_effects;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.math.MathHelper;

@Storable
public class ExtraPotionData {

    @Store // some potions will only do a thing x amount of times
    public int uses = 1;

    @Store
    private int stacks = 1;

    @Store
    public int casterLvl = 1;

    public void addStacks(int num, BasePotionEffect effect) {
        this.stacks = MathHelper.clamp(stacks + num, 0, effect.maxStacks());
    }

    public void decreaseStacks(int num) {
        stacks -= num;
    }

    public int getStacks() {
        return stacks;
    }

}
