package com.robertx22.player;

import com.robertx22.capability.EntityData;
import com.robertx22.constants.Tag;
import com.robertx22.item.GearCreator;
import com.robertx22.utilityclasses.GeneralUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class OnLogin {

    @SubscribeEvent
    public void onLogin(PlayerLoggedInEvent event) {

        if (event.player.world.isRemote) {
            return;
        }

        EntityPlayer player = event.player;

        PlayerData.giveExp(player, 0);

        if (player.hasCapability(EntityData.Data, null)
                && !player.getCapability(EntityData.Data,
                null).getNBT().getBoolean(Tag.ENTITY_INFO)) {

            EntityData.IData data = player.getCapability(EntityData.Data, null);

            NBTTagCompound nbt = GeneralUtils.getdefaultEntityNBT();
            nbt.setBoolean(Tag.ENTITY_INFO, true);

            player.addItemStackToInventory(GearCreator.createGear(1, 0, Tag.WEAPON));
            player.addItemStackToInventory(new ItemStack(Blocks.ANVIL));

            data.setNBT(nbt);

        }

    }

}
