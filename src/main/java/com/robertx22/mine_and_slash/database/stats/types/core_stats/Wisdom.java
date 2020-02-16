package com.robertx22.mine_and_slash.database.stats.types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.LowElementalResistFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class Wisdom extends BaseCoreStat {
    public static final String GUID = "wisdom";
    public static final Wisdom INSTANCE = new Wisdom();

    private Wisdom() {

    }

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.LIGHT_PURPLE;
    }

    @Override
    public String getIconPath() {
        return "core/wis";
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Both Mana, Magic Shield Regens and elemental resists";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new MagicShieldRegenFlat(), new LowElementalResistFlat(Elements.Nature),
                             new LowElementalResistFlat(Elements.Fire), new LowElementalResistFlat(Elements.Thunder),
                             new LowElementalResistFlat(Elements.Water)

        )

                ;
    }

    @Override
    public String locNameForLangFile() {
        return "Wisdom";
    }
}
