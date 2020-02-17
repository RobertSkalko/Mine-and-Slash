package com.robertx22.mine_and_slash.database.unique_items.staffs;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Staff;
import com.robertx22.mine_and_slash.database.unique_items.IElementalUnique;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CompletePhysDispersionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ElementalSpellToAttackDMGPercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class EleSpellDmgStaff implements IElementalUnique {

    public Elements element;

    public EleSpellDmgStaff(Elements element) {
        this.element = element;
    }

    static StatReq req = new StatReq(LvlPointStat.INTELLIGENCE, StatReq.Size.MAJOR);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalSpellToAttackDMGFlat(element).size(StatMod.Size.HIGH), new ElementalSpellToAttackDMGPercent(element)
            , new CompletePhysDispersionFlat());
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Staff.INSTANCE;
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(element).size(StatMod.Size.LOW));
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
        return element.guidName + "_ele_staff0";
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
