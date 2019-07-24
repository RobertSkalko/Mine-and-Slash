package com.robertx22.mine_and_slash.database.unique_items.shields;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.BlockStrengthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.WisdomFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueShield;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class ShieldWisdom extends BaseUniqueShield implements IUnique {

    public ShieldWisdom() {
        super();
    }

    @Override
    public boolean canGetSet() {
        return true;
    }

    @Override
    public String locDescForLangFile() {
        return "Let your wounds become wisdom.";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new BlockStrengthFlat(), new WisdomFlat().multi(1.5F), new ManaFlat()
                .multi(2));
    }

    @Override
    public String locNameForLangFile() {
        return TextFormatting.YELLOW + "Wisdom Shield";
    }

    @Override
    public String GUID() {
        return "shield_wisdom0";
    }

    @Override
    public int Tier() {
        return 12;
    }
}

