package com.robertx22.mine_and_slash.database.rarities.serialization;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import net.minecraft.util.text.TextFormatting;

import java.util.Locale;

public class SerializedBaseRarity implements Rarity {

    public SerializedBaseRarity(SerializedBaseRarity o) {

        this.colorNumber = o.colorNumber;
        this.rank = o.rank;
        this.weight = o.weight;
        this.textFormatting = o.textFormatting;
        this.locNameID = o.locNameID;
        this.spawnDurabilityHit = o.spawnDurabilityHit;
    }

    public SerializedBaseRarity() {

    }

    public int colorNumber;
    public int rank;
    public int weight;
    public TextFormatting textFormatting;
    public String locNameID;
    public MinMax spawnDurabilityHit;

    @Override
    public int colorInt() {
        return colorNumber;
    }

    @Override
    public MinMax SpawnDurabilityHit() {
        return spawnDurabilityHit;
    }

    @Override
    public String locNameLangFileGUID() {
        return locNameID;
    }

    @Override
    public String GUID() {
        return locNameForLangFile().toLowerCase(Locale.ROOT);
    }

    @Override
    public int Rank() {
        return rank;
    }

    @Override
    public int Weight() {
        return weight;
    }

    @Override
    public TextFormatting textFormatting() {
        return textFormatting;
    }

    @Override
    public String locNameForLangFile() {
        return "";
    }

}

