package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.math.MathHelper;

public abstract class ItemBlueprint {

    public ItemBlueprint(int level) {

        this.level = level;

    }

    public int MagicFind = 0;

    public int rarity;
    public boolean RandomRarity = true;
    public int level;
    public boolean LevelRange = true;
    public int LevelVariance = 3;

    public int minRarity = -1;
    public int maxRarity = 5;

    public int minLevel = 1;

    public String GUID;
    public boolean randomGUID = true;

    public void SetSpecificType(String type) {

        GUID = type;
        randomGUID = false;

    }

    public abstract RaritiesContainer<? extends Rarity> getRarityContainer();

    public void setSpecificRarity(int i) {

        rarity = i;
        RandomRarity = false;

    }

    public int getRarity() {

        RaritiesContainer<? extends Rarity> rarities = getRarityContainer();

        if (RandomRarity) {

            int rank = MathHelper.clamp(rarities.random().Rank(), minRarity, maxRarity);

            Rarity rarity = rarities.get(rank);

            this.rarity = rarity.Rank();

        }

        return rarity;

    }

    public int getLevel() {

        int lvl = level;

        if (LevelRange) {

            lvl = RandomUtils.RandomRange(level - LevelVariance, level + LevelVariance);

            level = lvl;

        }
        return MathHelper.clamp(lvl, this.minLevel, ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL
                .get());

    }
}
