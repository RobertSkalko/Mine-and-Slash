package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.database.world_providers.IWP;
import com.robertx22.mine_and_slash.new_content.chests.MapChestBlock;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnBreakBlockStopInDungeon {

    public static boolean canBreakBlock(Block block) {

        try {
            ResourceLocation id = block.getRegistryName();

            String namespace = id.getNamespace();

            if (namespace.contains("grave") || namespace.contains("tomb")) {
                return true;
            }

        } catch (Exception e) {
        }

        if (block instanceof MapChestBlock) {
            return true;
        }

        return false;

    }

    // disable all block breaking in maps, except stuff like my chests
    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event) {

        try {
            if (event.getPlayer() != null &&
                event.getPlayer().world != null && event.getPlayer().world.getDimension() instanceof IWP) {

                boolean can = canBreakBlock(event.getState()
                    .getBlock());

                if (!can) {
                    event.setCanceled(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
