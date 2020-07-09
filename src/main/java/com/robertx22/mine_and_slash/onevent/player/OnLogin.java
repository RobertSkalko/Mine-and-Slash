package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.items.misc.ItemNewbieGearBag;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ConfigRegister;
import com.robertx22.mine_and_slash.packets.OnLoginClientPacket;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnLogin {

    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {

        if (event.getPlayer().world.isRemote) {
            return;
        }

        try {

            ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();

            MMORPG.sendToClient(new OnLoginClientPacket(OnLoginClientPacket.When.BEFORE), player);
            ConfigRegister.CONFIGS.values()
                .forEach(x -> x.sendToClient(player));
            Rarities.sendAllPacketsToClientOnLogin(player);
            SlashRegistry.sendAllPacketsToClientOnLogin(player);

            MMORPG.sendToClient(new OnLoginClientPacket(OnLoginClientPacket.When.AFTER), player);

            SlashRegistry.restoreFromBackupifEmpty();

            if (MMORPG.RUN_DEV_TOOLS) {
                player.sendMessage(Chats.Dev_tools_enabled_contact_the_author.locName()
                    .setStyle(new Style().setColor(Styles.RED)));
            }

            if (Load.hasUnit(player)) {

                UnitData data = Load.Unit(player);

                SlashRegistry.restoreFromBackupifEmpty();
                data.onLogin(player);

                Load.spells(player)
                    .getAbilitiesData()
                    .clean();

                data.syncToClient(player);

            } else {
                player.sendMessage(
                    new StringTextComponent("Error, player has no capability!" + Ref.MOD_NAME + " mod is broken!"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        SlashRegistry.restoreFromBackupifEmpty();

    }

    public static void GiveStarterItems(PlayerEntity player) {

        if (player.world.isRemote) {
            return;
        }

        player.inventory.addItemStackToInventory(new ItemStack(ItemNewbieGearBag.ITEM));

    }

}
