package com.robertx22.mine_and_slash.database.unique_items.boots.cloth;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.cloth.ClothBoots;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.unique_items.IElementalUnique;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class EleClothBoots implements IElementalUnique, IUnique {

    public Elements element;

    public EleClothBoots(Elements element) {
        this.element = element;
    }

    static StatReq req = new StatReq(
        LvlPointStat.INTELLIGENCE, StatReq.Size.NORMAL, LvlPointStat.WISDOM, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public GearItemSlot getGearSlot() {
        return ClothBoots.INSTANCE;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new MagicShieldRegenFlat().size(StatMod.Size.HALF_LESS), new ManaRegenFlat().size(StatMod.Size.QUARTER), new EnergyRegenFlat().size(StatMod.Size.QUARTER));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(element).size(StatMod.Size.QUARTER));
    }

    @Override
    public String locDescForLangFile() {
        return "Allow time to heal all.";
    }

    @Override
    public String locNameForLangFile() {
        return TextFormatting.YELLOW + element.singularDisasterName + " Priest's Boots";
    }

    @Override
    public String GUID() {
        return element.guidName + "_cloth_boots0";
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public int getTier() {
        return 1;
    }

    @Override
    public IUnique newInstance(Elements element) {
        return new EleClothBoots(element);
    }

}
