package com.robertx22.mine_and_slash.database.stats.types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
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
        return Arrays.asList(new MagicShieldRegenFlat(), new ElementalResistFlat(Elements.Nature).size(StatMod.Size.LOW),
            new ElementalResistFlat(Elements.Fire).size(StatMod.Size.LOW), new ElementalResistFlat(Elements.Thunder).size(StatMod.Size.LOW),
            new ElementalResistFlat(Elements.Water).size(StatMod.Size.LOW)

        )

            ;
    }

    @Override
    public String locNameForLangFile() {
        return "Wisdom";
    }
}
