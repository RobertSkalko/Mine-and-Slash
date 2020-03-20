package com.robertx22.mine_and_slash.database.rarities.base;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.TextFormatting;

public abstract class BaseUncommon implements Rarity {
    @Override
    public int colorInt() {
        return 5635925;
    }

    @Override
    public String GUID() {
        return "uncommon";
    }

    @Override
    public int Rank() {
        return IRarity.Uncommon;
    }

    @Override
    public TextFormatting textFormatting() {
        return TextFormatting.GREEN;
    }

    @Override
    public MinMax SpawnDurabilityHit() {
        return new MinMax(65, 85);
    }

    @Override
    public String locNameForLangFile() {
        return "Uncommon";
    }
}
