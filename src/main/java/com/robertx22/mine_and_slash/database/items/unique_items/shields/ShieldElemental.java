package com.robertx22.mine_and_slash.database.items.unique_items.shields;

import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueShield;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.BlockStrengthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.BlockReflectFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalAffinityFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShieldElemental extends BaseUniqueShield implements IGenerated<IUnique> {

    public Elements element;

    public ShieldElemental(Elements element) {
        this.element = element;
    }

    static StatReq req = new StatReq(LvlPointStat.WISDOM, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalAffinityFlat(element).multi(0.5F), new ElementalResistFlat(element));
    }

    @Override
    public String locDescForLangFile() {
        return "Fear no " + element.dmgName;
    }

    @Override
    public String locNameForLangFile() {
        return TextFormatting.YELLOW + "Shield of " + element.dmgName + " Thorns";
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new BlockStrengthFlat(), new BlockReflectFlat(element));
    }

    @Override
    public String GUID() {
        return element.name().toLowerCase() + "_ele_shield0";
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public int Tier() {
        return 8;
    }

    @Override
    public List<IUnique> generateAllPossibleStatVariations() {
        List<IUnique> list = new ArrayList<>();
        Elements.getAllSingleElements().forEach(x -> list.add(new ShieldElemental(x)));
        return list;
    }
}
