package com.robertx22.mine_and_slash.database.unique_items.hammers;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Hammer;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalFocusFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalHitPercent;
import com.robertx22.mine_and_slash.database.unique_items.IElementalUnique;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class HammerElemental implements IElementalUnique {

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
        return Arrays.asList(new CriticalDamageFlat().size(StatMod.Size.VERY_HIGH), new CriticalHitPercent().size(StatMod.Size.VERY_HIGH), new ElementalFocusFlat(element));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(element).size(StatMod.Size.LOW));
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
        return element.guidName + "_ele_hammer0";
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

    @Override
    public GearItemSlot getGearSlot() {
        return Hammer.INSTANCE;
    }
}

