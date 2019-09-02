package com.robertx22.mine_and_slash.database.items.unique_items.staffs;

import com.robertx22.mine_and_slash.database.items.unique_items.IElementalUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueStaff;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CompletePhysDispersionFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.ElementalSpellToAttackDMGPercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class EleSpellDmgStaff extends BaseUniqueStaff implements IElementalUnique {

    public Elements element;

    public EleSpellDmgStaff(Elements element) {
        this.element = element;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalSpellToAttackDMGFlat(element).multi(2), new ElementalSpellToAttackDMGPercent(element)
                .multi(5F), new CompletePhysDispersionFlat());
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(element).multi(0.25F));
    }

    @Override
    public String locDescForLangFile() {
        return "Do not be fooled from a glance.";
    }

    @Override
    public String locNameForLangFile() {
        return TextFormatting.YELLOW + "Rod of " + element.dmgName + "'s Requiem";
    }

    @Override
    public String GUID() {
        return element.name().toLowerCase() + "_ele_staff0";
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public int Tier() {
        return 16;
    }

    @Override
    public IUnique newInstance(Elements element) {
        return new EleSpellDmgStaff(element);
    }

}
