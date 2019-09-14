package com.robertx22.mine_and_slash.database.items.unique_items.rings;

import com.robertx22.mine_and_slash.database.items.unique_items.IElementalUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueRing;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.IntelligenceFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
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

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ManaOnHitFlat().multi(3F), new IntelligenceFlat(), new ElementalPeneFlat(element));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(element).multi(1.25F));
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
        return element.name().toLowerCase() + "_ele_ring0";
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

