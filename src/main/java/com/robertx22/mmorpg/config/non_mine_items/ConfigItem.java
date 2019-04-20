package com.robertx22.mmorpg.config.non_mine_items;

public class ConfigItem {

    // public String itemID = "modid:itemid";
    public String itemType = "Sword";

    public int uniqueItemWeight = 0;
    public int normalItemWeight = 80;
    public int runedItemWeight = 20;

    public int minRarity = 0;
    public int maxRarity = 5;

    public boolean itemIsPlayerLevel = true;

    public int itemLevelIfDoesntUsePlayerLevel = 1;

    public int levelVariance = 0;

    public String uniqueId = "";
    public boolean uniqueIsRandom = true;

}
