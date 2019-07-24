package com.robertx22.mine_and_slash.blocks.map_device;

import com.robertx22.mine_and_slash.blocks.bases.BaseTile;
import com.robertx22.mine_and_slash.database.world_providers.IWP;
import com.robertx22.mine_and_slash.items.misc.ItemMap;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Map;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.dimension.DimensionType;

import javax.annotation.Nullable;
import java.util.List;

public class TileMapDevice extends BaseTile {

    public static final int size = 4;

    @Override
    public boolean isAutomatable() {
        return false;
    }

    @Override
    public boolean isItemValidInput(ItemStack stack) {
        return false;
    }

    public ItemStack StartSlot() {
        return itemStacks[3];
    }

    public ItemStack TierSlot() {
        return itemStacks[0];
    }

    public ItemStack LevelSlot() {
        return itemStacks[1];
    }

    public ItemStack MapSlot() {
        return itemStacks[2];
    }

    public TileMapDevice() {
        super(BlockRegister.MAP_DEVICE);

        itemStacks = new ItemStack[size];
        clear();
    }

    @Override
    public int ticksRequired() {
        return 0;
    }

    @Override
    public void finishCooking() {

    }

    @Override
    public boolean isCooking() {
        return false;
    }

    @Override
    public int tickRate() {
        return 0;
    }

    @Override
    public void doActionEveryTime() {

    }

    @Override
    public void tick() {
        if (!this.world.isRemote) {

            ticks++;
            if (ticks > 20) {
                ticks = 0;

                doLogic();

            }
        }

    }

    private void doLogic() {

        if (world == null || world.getDimension() == null || world.getDimension() instanceof IWP) {
            return;
        }

        ItemStack start = this.StartSlot();

        MapItemData map = Map.Load(this.MapSlot());
        MapItemData level = Map.Load(this.LevelSlot());
        MapItemData tier = Map.Load(this.TierSlot());

        if (map != null) {

            if (level != null) {

                if (map.increaseLevel(level.rarity + 1)) {
                    this.LevelSlot().shrink(1);
                    Map.Save(this.MapSlot(), map);
                }

            }
            if (tier != null) {

                if (map.increaseTier(tier.rarity + 1)) {
                    this.TierSlot().shrink(1);
                    Map.Save(this.MapSlot(), map);
                }
            }

            if (start != null && start.getItem().equals(Items.WHEAT_SEEDS)) {

                BlockPos p = this.pos;

                PlayerEntity player = this.getWorld()
                        .getClosestPlayer(p.getX(), p.getY(), p.getZ(), 20, EntityPredicates.IS_ALIVE);

                if (player != null) {

                    world.playSound(null, p.getX(), p.getY(), p.getZ(), SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.BLOCKS, 0.6f, 0);
                    world.playSound(null, p.getX(), p.getY(), p.getZ(), SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.BLOCKS, 0.4f, 0);

                    DimensionType type = null;
                    try {

                        type = map.setupPlayerMapData(world, p, player);

                        trySetupMapForGroup(map, player);

                        // start map
                        this.MapSlot().shrink(1);
                        this.StartSlot().shrink(1);

                        BlockPos pos = this.pos.north(4);
                        Boolean spawnedPortal1 = ItemMap.createMapPortal(type, pos, world, map);

                        BlockPos pos1 = this.pos.south(4);
                        Boolean spawnedPortal2 = ItemMap.createMapPortal(type, pos1, world, map);

                        BlockPos pos2 = this.pos.east(4);
                        Boolean spawnedPortal3 = ItemMap.createMapPortal(type, pos2, world, map);

                        BlockPos pos3 = this.pos.west(4);
                        Boolean spawnedPortal4 = ItemMap.createMapPortal(type, pos3, world, map);

                        if (!spawnedPortal1 && !spawnedPortal2 && !spawnedPortal3 && !spawnedPortal4) {
                            AxisAlignedBB aab = new AxisAlignedBB(this.getPos()).grow(10);
                            world.getEntitiesWithinAABB(ServerPlayerEntity.class, aab)
                                    .forEach(x -> x.sendMessage(Chats.NoSpaceForPortal.locName()));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

            markDirty();
        }

    }

    private void trySetupMapForGroup(MapItemData map, PlayerEntity player) {

        AxisAlignedBB aab = new AxisAlignedBB(this.getPos()).grow(15);

        if (map.groupPlay) {
            List<ServerPlayerEntity> players = world.getEntitiesWithinAABB(ServerPlayerEntity.class, aab);

            double distance = Double.MAX_VALUE;

            int added = 0;

            for (ServerPlayerEntity p : players) {
                double dist = p.getDistance(player);

                if (dist <= distance && added < map.maxPlayersInGroup) {
                    map.setupPlayerMapData(world, this.pos, p);

                    distance = dist;
                    added++;

                }
            }

        }

    }

    @Override
    public ITextComponent getDisplayName() {
        return CLOC.blank("block.mmorpg.map_device");

    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory,
                                PlayerEntity playerEntity) {

        return new ContainerMapDevice(i, playerInventory, this);

    }
}