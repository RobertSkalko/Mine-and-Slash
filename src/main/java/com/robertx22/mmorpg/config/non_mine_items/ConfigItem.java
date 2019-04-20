package com.robertx22.mmorpg.config.non_mine_items;

public class ConfigItem {

    public enum PlayerOrConfig {
	PLAYER_LEVEL, CONFIG_LEVEL
    }

    public String itemID = "";
    public String itemType = "";

    public int uniqueItemWeight = 0;
    public int normalItemWeight = 80;
    public int runedItemWeight = 20;

    public int minRarity = 0;
    public int maxRarity = 5;

    public PlayerOrConfig itemLevelMethod = PlayerOrConfig.PLAYER_LEVEL;
    public int configLevel = 1;
    public int levelVariance = 0;

    public String uniqueId = "";
    public boolean uniqueIsRandom = true;

}
