package com.robertx22.mine_and_slash.database.rarities.skill_gems;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.SkillGemRarity;
import net.minecraft.util.text.TextFormatting;

public class CommonGem implements SkillGemRarity {
    @Override
    public MinMax statPercents() {
        return new MinMax(0, 20);
    }

    @Override
    public String GUID() {
        return "common";
    }

    @Override
    public int Rank() {
        return 0;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public TextFormatting textFormatting() {
        return TextFormatting.GRAY;
    }

    @Override
    public String locNameForLangFile() {
        return "Common";
    }
}
