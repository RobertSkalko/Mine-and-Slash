package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.BlockStrengthFlat;

import java.util.Arrays;
import java.util.List;

public abstract class BaseOffHand extends GearItemSlot {

    @Override
    public List<StatMod> PrimaryStats() {
        return Arrays.asList(new BlockStrengthFlat());
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return Arrays.asList(new ArmorFlat());
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.OffHand;
    }
}
