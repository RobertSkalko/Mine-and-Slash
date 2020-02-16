package com.robertx22.mine_and_slash.database.items.unique_items.hammers;

import com.robertx22.mine_and_slash.database.items.unique_items.IElementalUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueHammer;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.HighCriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalFocusFlat;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.CriticalHitMulti;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class HammerElemental extends BaseUniqueHammer implements IElementalUnique {

    public Elements element;

    public HammerElemental(Elements element) {
        this.element = element;
    }

    static StatReq req = new StatReq(
            LvlPointStat.STRENGTH, StatReq.Size.SMALL, LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HighCriticalDamageFlat(), new CriticalHitMulti(), new ElementalFocusFlat(element));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(element));
    }

    @Override
    public String locDescForLangFile() {
        return "I can feel it at my fingertips.";
    }

    @Override
    public String locNameForLangFile() {
        return TextFormatting.YELLOW + "Convergence, " + element.dmgName + "'s Infusion";
    }

    @Override
    public String GUID() {
        return element.name().toLowerCase() + "_ele_hammer0";
    }

    @Override
    public int getRarityRank() {
        return IRarity.Mythic;
    }

    @Override
    public int Tier() {
        return 7;
    }

    @Override
    public IUnique newInstance(Elements element) {
        return new HammerElemental(element);
    }

}

