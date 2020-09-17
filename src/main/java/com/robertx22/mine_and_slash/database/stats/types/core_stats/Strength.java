package com.robertx22.mine_and_slash.database.stats.types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ElementalAttackDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.BaseCoreStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class Strength extends BaseCoreStat {

    public static final String GUID = "strength";
    public static final Strength INSTANCE = new Strength();

    private Strength() {

    }

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.RED;
    }

    @Override
    public String getIconPath() {
        return "core/str";
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Physical DMG, Critical DMG, Elemental Attack DMG";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new PhysicalDamagePercent(), new CriticalDamageFlat(), new ElementalAttackDamagePercent(Elements.Elemental));
    }

    @Override
    public String locNameForLangFile() {
        return "Strength";
    }
}
