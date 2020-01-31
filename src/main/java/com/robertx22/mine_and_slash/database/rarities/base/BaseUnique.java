package com.robertx22.mine_and_slash.database.rarities.base;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.enumclasses.RGB;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TextFormatting;

public abstract class BaseUnique implements Rarity {
    @Override
    public int colorInt() {
        return 16777045;
    }

    @Override
    public String GUID() {

        return "Unique";
    }

    @Override
    public int Rank() {

        return IRarity.Unique;
    }

    @Override
    public DropSoundData getDropSound() {
        return new DropSoundData(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE).volume(0.7F);
    }

    @Override
    public TextFormatting textFormatColor() {
        return TextFormatting.YELLOW;
    }

    @Override
    public MinMax SpawnDurabilityHit() {
        return new MinMax(75, 90);
    }

    @Override
    public String Color() {
        return TextFormatting.YELLOW.toString();
    }

    @Override
    public int Weight() {
        return 0;
    }

    @Override
    public RGB getRGBColor() {
        return new RGB(255, 255, 0);
    }

    @Override
    public String locNameForLangFile() {
        return "Unique";
    }

}