package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.MapRarity;
import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.math.MathHelper;

public class MapBlueprint extends ItemBlueprint {

    public static final int PERMADEATH_CHANCE = 10;

    public MapBlueprint(int level, int worldTier) {
        super(level);
        this.setTier(worldTier);

    }

    private int tier = 0;
    private boolean tierRange = true;
    private int tierVariance = 2;

    public void setTier(int i) {
        tier = i;
    }

    public int getTier() {

        if (tierRange) {
            int thetier = RandomUtils.RandomRange(tier - tierVariance, tier + tierVariance);

            return MathHelper.clamp(thetier, 1, ITiered.MAX_TIER);

        } else {
            return tier;
        }

    }

    public boolean getIsPermaDeath() {

        return RandomUtils.roll(PERMADEATH_CHANCE);

    }

    public void rollSetupGrouPlay(MapItemData data, MapRarity rarity) {

        if (RandomUtils.roll(rarity.groupPlayChance())) {
            data.groupPlay = true;
            data.maxPlayersInGroup = RandomUtils.RandomRange(3, 8);

        }

    }

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {

        return Rarities.Maps;

    }

}
