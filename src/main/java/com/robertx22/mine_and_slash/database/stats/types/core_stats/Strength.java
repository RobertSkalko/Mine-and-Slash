package com.robertx22.mine_and_slash.database.stats.types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.elemental.AllEleDmgFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;
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
        return "Increases Physical DMG, Critical DMG, Physical DMG multi";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new PhysicalDamagePercent(), new CriticalDamageFlat(), new AllEleDmgFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Strength";
    }
}
