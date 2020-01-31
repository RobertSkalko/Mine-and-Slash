package com.robertx22.mine_and_slash.database.rarities.base;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.enumclasses.RGB;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TextFormatting;

public abstract class BaseBossRarity implements Rarity {

    @Override
    public int colorInt() {
        return 16733695;
    }

    @Override
    public String GUID() {
        return "Boss";
    }

    @Override
    public int Rank() {
        return IRarity.Boss;
    }

    @Override
    public DropSoundData getDropSound() {
        return new DropSoundData(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE).volume(1).pitch(1);
    }

    @Override
    public TextFormatting textFormatColor() {
        return TextFormatting.GOLD;
    }

    @Override
    public MinMax SpawnDurabilityHit() {
        return new MinMax(85, 95);
    }

    @Override
    public String Color() {
        return TextFormatting.GOLD.toString();
    }

    @Override
    public RGB getRGBColor() {
        return new RGB(204, 0, 255);
    }

    @Override
    public String locNameForLangFile() {
        return "Boss";
    }
}
