package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.*;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;

import java.util.Arrays;
import java.util.List;

public abstract class BaseArmor extends GearItemSlot {

    @Override
    public List<StatMod> PrimaryStats() {
        return Arrays.asList(new HealthFlat());
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return Arrays.asList(new ArmorFlat(), new StrengthFlat(), new VitalityFlat(), new IntelligenceFlat(), new WisdomFlat(), new StaminaFlat(), new DexterityFlat());
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Armor;
    }
}
