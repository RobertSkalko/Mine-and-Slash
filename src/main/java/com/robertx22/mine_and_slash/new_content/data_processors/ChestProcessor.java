package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.mmorpg.ModLootTables;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModBlocks;
import com.robertx22.mine_and_slash.new_content.chests.MapChestTile;
import com.robertx22.mine_and_slash.new_content.data_processors.bases.ChunkProcessData;
import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class ChestProcessor extends DataProcessor {

    public ChestProcessor() {
        super("chest", Type.CONTAINS);
    }

    @Override
    public void processImplementation(String key, BlockPos pos, IWorld world, ChunkProcessData data) {

        boolean isTrapped = this.data.contains("trap");

        boolean useVanilla = RandomUtils.roll(20);

        if (isTrapped) {
            if (useVanilla) {
                world.setBlockState(pos, Blocks.TRAPPED_CHEST
                    .getDefaultState(), 2);
            } else {
                world.setBlockState(pos, ModBlocks.TRAPPED_MAP_CHEST.get()
                    .getDefaultState(), 2);
            }
        } else {
            if (useVanilla) {
                world.setBlockState(pos, Blocks.CHEST
                    .getDefaultState(), 2);
            } else {
                world.setBlockState(pos, ModBlocks.MAP_CHEST.get()
                    .getDefaultState(), 2);
            }
        }

        TileEntity tile = world.getTileEntity(pos);

        if (useVanilla) {
            if (tile instanceof ChestTileEntity) {
                ChestTileEntity chest = (ChestTileEntity) tile;

                float lvm = (float) Load.world(world.getWorld())
                    .getLevel(pos) / (float) ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get();

                ResourceLocation table = ModLootTables.TIER_1_DUNGEON_CHEST;

                if (lvm > 0.3F) {
                    table = ModLootTables.TIER_2_DUNGEON_CHEST;
                }
                if (lvm > 0.5F) {
                    table = ModLootTables.TIER_3_DUNGEON_CHEST;
                }
                if (lvm > 0.7F) {
                    table = ModLootTables.TIER_4_DUNGEON_CHEST;
                }
                if (lvm > 0.9F) {
                    table = ModLootTables.TIER_5_DUNGEON_CHEST;
                }

                LockableLootTileEntity.setLootTable(world, world.getRandom(), pos, table);

            } else {
                System.out.println("Chest gen failed, tile not instanceof vanilla chest.");
            }
        } else {
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
}
