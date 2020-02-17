package com.robertx22.mine_and_slash.database.items.unique_items.shields;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.offhand.Shield;
import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.BlockStrengthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Wisdom;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class ShieldWisdom implements IUnique {

    public static ShieldWisdom INSTANCE = new ShieldWisdom();

    private ShieldWisdom() {
        super();
    }

    static StatReq req = new StatReq(LvlPointStat.WISDOM, StatReq.Size.NORMAL);

    @Override
    public GearItemSlot getGearSlot() {
        return Shield.INSTANCE;
    }

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
        return Arrays.asList(new CoreStatFlat(Wisdom.INSTANCE).size(StatMod.Size.HIGH), new MagicShieldFlat());
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

