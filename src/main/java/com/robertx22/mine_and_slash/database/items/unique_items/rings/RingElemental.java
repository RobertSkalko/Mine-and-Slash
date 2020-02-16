package com.robertx22.mine_and_slash.database.items.unique_items.rings;

import com.robertx22.mine_and_slash.database.items.unique_items.IElementalUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueRing;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Intelligence;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class RingElemental extends BaseUniqueRing implements IElementalUnique {

    public Elements element;

    public RingElemental(Elements element) {
        this.element = element;
    }

    static StatReq req = new StatReq(LvlPointStat.INTELLIGENCE, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new ManaOnHitFlat(), new CoreStatFlat(Intelligence.INSTANCE).size(StatMod.Size.HIGH), new ElementalPeneFlat(element).size(StatMod.Size.HIGH));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(element));
    }

    @Override
    public String locDescForLangFile() {
        return "I summon " + element.dmgName + " in my name!";
    }

    @Override
    public String locNameForLangFile() {
        return TextFormatting.YELLOW + "Ring of " + element.disasterName;
    }

    @Override
    public String GUID() {
        return element.name()
            .toLowerCase() + "_ele_ring0";
    }

    @Override
    public int getRarityRank() {
        return IRarity.Mythic;
    }

    @Override
    public int Tier() {
        return 5;
    }

    @Override
    public IUnique newInstance(Elements element) {
        return new RingElemental(element);
    }

}

