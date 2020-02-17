package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import org.jline.utils.Log;

@Storable
public class PlayerWholeMapData {

    @Store
    public boolean isActive = false;
    @Store
    public BlockPos mapDevicePos;
    @Store
    public MapItemData mapdata = new MapItemData();
    @Store
    String originalDimension = "";
    @Store
    String playerID = ""; // for debug purposes
    @Store
    public int minutesPassed = 0;
    @Store
    public boolean isDead = false;
    @Store
    public boolean questFinished = false;

    @Store
    public int minutesInMap = 0;
    @Store
    public int minutesOutsideMap = 0;

    public void setOriginalDimension(DimensionType type) {
        this.originalDimension = DimensionType.getKey(type)
            .toString();
    }

    public void setPlayerId(PlayerEntity player) {

        String id = player.getUniqueID()
            .toString();

        if (this.playerID.isEmpty()) {
            this.playerID = id;
        } else {
            if (!playerID.equals(id)) {
                MMORPG.logError("Player Id changed in player map data, this could be indicative of a bug!");
            }
        }
    }

    public DimensionType getOriginalDimension() {

        DimensionType type = null;

        if (originalDimension.isEmpty()) {
            Log.error("Original dimension in player map data is empty, this shouldn't be possible!");
        } else {
            try {
                type = DimensionType.byName(new ResourceLocation(originalDimension));
            } catch (Exception e) {
                Log.error("Couldn't find the recorded original dimension of player map data.");
                e.printStackTrace();
            }
        }

        if (type == null) {
            return DimensionType.OVERWORLD;
        } else {
            return type;
        }
    }

}
