package com.robertx22.mine_and_slash.database.rarities.skill_gems;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.SkillGemRarity;
import net.minecraft.util.text.TextFormatting;

public class MagicalGem implements SkillGemRarity {
    @Override
    public MinMax statPercents() {
        return new MinMax(10, 30);
    }

    @Override
    public String GUID() {
        return "magical";
    }

    @Override
    public int Rank() {
        return 1;
    }

    @Override
    public int Weight() {
        return 500;
    }

    @Override
    public TextFormatting textFormatting() {
        return TextFormatting.GREEN;
    }

    @Override
    public String locNameForLangFile() {
        return "Magical";
    }
}
