package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModBlocks;
import com.robertx22.mine_and_slash.new_content.chests.MapChestTile;
import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class ChestProcessor extends DataProcessor {

    public ChestProcessor() {
        super("chest", Type.CONTAINS);
    }

    @Override
    public void processImplementation(String key, BlockPos pos, IWorld world, ChunkProcessData data) {

        world.setBlockState(pos, ModBlocks.MAP_CHEST.get()
            .getDefaultState(), 2);

        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof MapChestTile) {

            MapChestTile chest = (MapChestTile) tile;

            NonNullList<ItemStack> items = NonNullList.create();

            LootCrate crate = SlashRegistry.LootCrates()
                .random();

            int times = 1;

            if (key.contains("big")) {
                times = 3;
            }

            for (int i = 0; i < times; i++) {
                crate.generateItems(new LootInfo(world.getWorld(), pos))
                    .forEach(x -> items.add(x));
            }

            chest.addItems(items);

        } else {
            System.out.println("Chest gen failed, tile not instanceof map chest.");
        }

    }
}
