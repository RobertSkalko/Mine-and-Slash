package com.robertx22.mine_and_slash.database.unique_items.staffs;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Staff;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CompletePhysDispersionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalInfusionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ElementalInfusionPercent;
import com.robertx22.mine_and_slash.database.unique_items.IElementalUnique;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
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
        return Arrays.asList(
            new ElementalInfusionFlat(element).size(StatMod.Size.HALF_MORE),
            new ElementalInfusionPercent(element).size(StatMod.Size.HALF_MORE),
            new CompletePhysDispersionFlat()
        );
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Staff.INSTANCE;
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(element));
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
    public int getTier() {
        return 4;
    }

    @Override
    public IUnique newInstance(Elements element) {
        return new EleSpellDmgStaff(element);
    }

}
