package com.robertx22.mine_and_slash.database.quests.actions;

import com.robertx22.mine_and_slash.blocks.bases.BaseLootCrateTileEntity;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.entity.player.PlayerEntity;

public class OpenedCrateData extends ActionDoneData {

    public PlayerEntity player;
    public EntityCap.UnitData playerData;
    public BaseLootCrateTileEntity crate;

    public OpenedCrateData(PlayerEntity player, EntityCap.UnitData data, BaseLootCrateTileEntity crate) {
        super(getGroupAmount(player));

        this.player = player;
        this.playerData = data;
        this.crate = crate;
    }
}
