package com.robertx22.mine_and_slash.database.rarities;

public interface RuneRarity extends ItemRarity, SalvagableItem {

    int minimumRunewordPower();

    int maximumRunewordPower();

}
