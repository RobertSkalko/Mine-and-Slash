package com.robertx22.mine_and_slash.database.unique_items.shields;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.BlockStrengthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.StaminaFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.VitalityFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueShield;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class ShieldEleResist extends BaseUniqueShield implements IUnique {

    public ShieldEleResist() {
        super();
    }

    @Override
    public String locDescForLangFile() {
        return "Fear no elements.";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new BlockStrengthFlat(), new ElementalResistFlat(Elements.Elemental), new VitalityFlat()
                .multi(2), new StaminaFlat().multi(1.5F));
    }

    @Override
    public String locNameForLangFile() {
        return TextFormatting.YELLOW + "Shield of Resistance";
    }

    @Override
    public String GUID() {
        return "shieldresist0";
    }

    @Override
    public int Tier() {
        return 5;
    }
}

