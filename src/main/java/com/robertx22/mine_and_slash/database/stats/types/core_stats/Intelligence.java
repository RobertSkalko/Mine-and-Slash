package com.robertx22.mine_and_slash.database.stats.types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.elemental.AllEleDmgFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.MagicShieldPercent;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class Intelligence extends BaseCoreStat {

    private Intelligence() {

    }

    public static final Intelligence INSTANCE = new Intelligence();
    public static String GUID = "intelligence";

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.BLUE;
    }

    @Override
    public String getIconPath() {
        return "core/int";
    }

    @Override
    public String locDescForLangFile() {
        return "Increase Magic Shield percent, Mana Regen and Mana and All Elemental damage";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(
            new ManaRegenFlat(),
            new ManaFlat(),
            new MagicShieldPercent().size(StatMod.Size.LOW),
            new AllEleDmgFlat().size(StatMod.Size.LOW));
    }

    @Override
    public String locNameForLangFile() {
        return "Intelligence";
    }
}




