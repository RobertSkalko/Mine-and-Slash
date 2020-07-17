package com.robertx22.mine_and_slash.database.data.rarities.skill_gems;

import com.robertx22.mine_and_slash.database.data.MinMax;
import com.robertx22.mine_and_slash.database.data.rarities.SkillGemRarity;
import net.minecraft.util.text.TextFormatting;

public class RareGem implements SkillGemRarity {
    @Override
    public MinMax statPercents() {
        return new MinMax(30, 100);
    }

    @Override
    public String GUID() {
        return "rare";
    }

    @Override
    public int Rank() {
        return 2;
    }

    @Override
    public int Weight() {
        return 250;
    }

    @Override
    public TextFormatting textFormatting() {
        return TextFormatting.YELLOW;
    }

    @Override
    public String locNameForLangFile() {
        return "Rar";
    }
}
