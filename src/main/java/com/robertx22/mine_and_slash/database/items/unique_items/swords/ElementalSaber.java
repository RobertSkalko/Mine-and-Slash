package com.robertx22.mine_and_slash.database.items.unique_items.swords;

import com.robertx22.mine_and_slash.database.items.unique_items.IElementalUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueSword;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.ElementalSpellToAttackDMGPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class ElementalSaber extends BaseUniqueSword implements IElementalUnique {

    public Elements element;

    public ElementalSaber(Elements element) {
        this.element = element;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new CriticalHitFlat().multi(2), new CriticalDamagePercent(), new ElementalSpellToAttackDMGPercent(element)
                .multi(3));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(element).multi(0.5F));
    }

    @Override
    public String locDescForLangFile() {
        return "Let " + element.name() + " guide you.";
    }

    @Override
    public String locNameForLangFile() {
        return TextFormatting.YELLOW + "Saber of " + element.dmgName + " madness";
    }

    @Override
    public String GUID() {
        return element.name().toLowerCase() + "_ele_saber0";
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public int Tier() {
        return 14;
    }

    @Override
    public IUnique newInstance(Elements element) {
        return new ElementalSaber(element);
    }

}
