package com.robertx22.mine_and_slash.blocks.map_device;

import com.robertx22.mine_and_slash.blocks.bases.BaseTile;
import com.robertx22.mine_and_slash.database.world_providers.IWP;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.items.misc.ItemMap;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.MapDeviceSaving;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.dimension.DimensionType;

import javax.annotation.Nullable;

public class TileMapDevice extends BaseTile {

    public static final int size = 0;

    public MapDeviceData mapDeviceData = new MapDeviceData();

    @Override
    public boolean isAutomatable() {
        return false;
    }

    @Override
    public boolean isItemValidInput(ItemStack stack) {
        return false;
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
    public int getCookTime() {
        return 0;
    }

    @Override
    public void tick() {
        if (!this.world.isRemote) {

        }

    }

    private boolean summonPortals(MapItemData map) {

        if (world == null || world.getDimension() == null || world.getDimension() instanceof IWP) {
            return false;
        }

        BlockPos p = this.pos;

        world.playSound(null, p.getX(), p.getY(), p.getZ(), SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.BLOCKS, 0.6f, 0);
        world.playSound(null, p.getX(), p.getY(), p.getZ(), SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.BLOCKS, 0.4f, 0);

        try {

            DimensionType type = MapManager.getDimensionType(map.getIWP()
                    .getResourceLoc());

            // start map

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
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        markDirty();

        return true;

    }

    public void sacrificeMap(PlayerEntity player, MapItemData mapdata, ItemStack map) {

        boolean summoned = summonPortals(mapdata);

        if (summoned) {
            mapdata.setupPlayerMapData(this.pos, player);
            map.setCount(0);

            this.mapDeviceData = new MapDeviceData(mapdata, player);

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

        return new ContainerMapDevice(i, playerInventory, this, this.pos);

    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);

        MapDeviceSaving.Save(nbt, this.mapDeviceData);

        return nbt;
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);

        this.mapDeviceData = MapDeviceSaving.Load(nbt);

    }

}