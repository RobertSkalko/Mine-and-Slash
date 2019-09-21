package com.robertx22.mine_and_slash.database.items.unique_items.shields;

import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueShield;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.BlockStrengthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.WisdomFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class ShieldWisdom extends BaseUniqueShield implements IUnique {

    public static ShieldWisdom INSTANCE = new ShieldWisdom();

    private ShieldWisdom() {
        super();
    }
    
    static StatReq req = new StatReq(LvlPointStat.WISDOM, StatReq.Size.BIG);

    @Override
    public StatReq getRequirements() {
        return req;
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
        return Arrays.asList(new WisdomFlat().multi(1.5F), new ManaFlat().multi(2));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new BlockStrengthFlat());
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

