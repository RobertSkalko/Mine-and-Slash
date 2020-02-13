package com.robertx22.mine_and_slash.database.stats.types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class Vitality extends BaseCoreStat {

    public static final String GUID = "vitality";
    public static final Vitality INSTANCE = new Vitality();

    private Vitality() {

    }

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.DARK_RED;
    }

    @Override
    public String getIconPath() {
        return "core/vit";
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Health, Health percent and Health Regen";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new HealthFlat().multi(0.25F), new HealthPercent(), new HealthRegenFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Vitality";
    }
}
