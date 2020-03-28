package com.robertx22.mine_and_slash.database.gearitemslots;

import com.robertx22.mine_and_slash.database.stats.types.resources.Energy;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;

public class WeaponSwingCost {

    final float energy;
    final float mana;

    public WeaponSwingCost(float energy) {
        this.energy = energy;
        this.mana = 0;
    }

    public WeaponSwingCost(float energy, float mana) {
        this.energy = energy;
        this.mana = mana;
    }

    public final float GetManaCost(int lvl) {
        return Mana.getInstance()
            .calculateScalingStatGrowth(mana, lvl);
    }

    public final float GetEnergyCost(int lvl) {
        return Energy.getInstance()
            .calculateScalingStatGrowth(energy, lvl);
    }
}
