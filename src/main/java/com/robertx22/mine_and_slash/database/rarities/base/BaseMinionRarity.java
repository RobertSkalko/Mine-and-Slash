package com.robertx22.mine_and_slash.database.rarities.base;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.TextFormatting;

public abstract class BaseMinionRarity implements Rarity {

    @Override
    public int colorInt() {
        return 16733695;
    }

    @Override
    public String GUID() {
        return "minion";
    }

    @Override
    public int Rank() {
        return IRarity.Minion;
    }

    @Override
    public TextFormatting textFormatting() {
        return TextFormatting.GRAY;
    }

    @Override
    public MinMax SpawnDurabilityHit() {
        return new MinMax(85, 95);
    }

    @Override
    public String locNameForLangFile() {
        return "Minion";
    }
}
