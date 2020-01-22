package com.robertx22.mine_and_slash.uncommon.capability.bases;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.packets.sync_cap.CapTypes;
import com.robertx22.mine_and_slash.packets.sync_cap.SyncCapabilityToClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public interface ICommonCapability {

    CompoundNBT getNBT();

    CapTypes getCapType();

    void setNBT(CompoundNBT value);

    default void syncToClient(PlayerEntity player) {
        MMORPG.sendToClient(
                new SyncCapabilityToClient((ServerPlayerEntity) player, getCapType()), (ServerPlayerEntity) player);
    }

}
