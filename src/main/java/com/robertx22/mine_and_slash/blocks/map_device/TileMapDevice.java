package com.robertx22.mine_and_slash.blocks.map_device;

import com.robertx22.mine_and_slash.blocks.bases.BaseTile;
import com.robertx22.mine_and_slash.blocks.slots.FuelSlot;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.data_generation.unique_dungeons.UniqueDungeon;
import com.robertx22.mine_and_slash.database.world_providers.base.IWP;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.items.misc.ItemMap;
import com.robertx22.mine_and_slash.items.misc.UniqueDungeonKeyItem;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModTileEntities;
import com.robertx22.mine_and_slash.saveclasses.dungeon_dimension.DungeonDimensionData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
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
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import javax.annotation.Nullable;

public class TileMapDevice extends BaseTile {

    public static final int size = 1;

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
        super(ModTileEntities.MAP_DEVICE.get());

        itemStacks = new ItemStack[size];
        clear();
    }

    @Override
    public boolean isOutputSlot(int slot) {
        return false;
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
        return 10;
    }

    public static int MaximumFuel = 50000;

    @Override
    public void doActionEveryTime() {

        ItemStack fuel = itemStacks[0];

        if (this.fuel < MaximumFuel && !fuel.isEmpty()) {

            int val = FuelSlot.FUEL_VALUES.getOrDefault(fuel.getItem(), 0);

            if (val > 0) {
                this.fuel += val;
                fuel.shrink(1);
            }
        }

    }

    @Override
    public int getCookTime() {
        return 2;
    }

    @Override
    public void tick() {
        super.tick();
    }

    private boolean summonPortals(MapItemData map) {

        if (world == null || world.getDimension() == null || world.getDimension() instanceof IWP) {
            return false;
        }

        BlockPos p = this.pos;

        world.playSound(
            null, p.getX(), p.getY(), p.getZ(), SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.BLOCKS, 0.6f, 0);
        world.playSound(
            null, p.getX(), p.getY(), p.getZ(), SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.BLOCKS, 0.4f, 0);

        try {

            DimensionType type = MapManager.getDungeonDimensionType();

            // start map

            ChunkPos cpos = Load.world(MapManager.getWorld(type))
                .getData()
                .randomFree();

            String dungeonID = DungeonDimensionData.getId(cpos);

            Load.world(MapManager.getWorld(type))
                .init(map, cpos);

            if (!WorldUtils.isMapWorld(world)) {

                BlockPos pos = this.pos.north(4);
                Boolean spawnedPortal1 = ItemMap.summonPortal(p, map, world, pos, type, dungeonID);

                BlockPos pos1 = this.pos.south(4);
                Boolean spawnedPortal2 = ItemMap.summonPortal(p, map, world, pos1, type, dungeonID);

                BlockPos pos2 = this.pos.east(4);
                Boolean spawnedPortal3 = ItemMap.summonPortal(p, map, world, pos2, type, dungeonID);

                BlockPos pos3 = this.pos.west(4);
                Boolean spawnedPortal4 = ItemMap.summonPortal(p, map, world, pos3, type, dungeonID);

                if (!spawnedPortal1 && !spawnedPortal2 && !spawnedPortal3 && !spawnedPortal4) {
                    AxisAlignedBB aab = new AxisAlignedBB(this.getPos()).grow(10);
                    world.getEntitiesWithinAABB(ServerPlayerEntity.class, aab)
                        .forEach(x -> x.sendMessage(Chats.NoSpaceForPortal.locName()));
                    return false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        markDirty();

        return true;

    }

    public void sacrificeMap(PlayerEntity player, MapItemData mapdata, ItemStack map) {

        if (!hasEnoughFuel()) {
            player.sendMessage(new SText("Not enough fuel"));
            return;
        }

        boolean summoned = summonPortals(mapdata);

        if (summoned) {
            this.spendFuelForActivation();
            mapdata.setupPlayerMapData(this.pos, player);
            map.shrink(1);
        }
    }

    public void sacrificeKey(PlayerEntity player, UniqueDungeon dungeon, ItemStack key) {

        if (!hasEnoughFuel()) {
            player.sendMessage(new SText("Not enough fuel"));
            return;
        }

        boolean summoned = summonPortals(MapItemData.empty());

        if (summoned) {
            this.spendFuelForActivation();

            UniqueDungeon uniqueDungeon = UniqueDungeonKeyItem.getDungeon(key);

            World world = MapManager.getWorld(MapManager.getUniqueDungeonDimensionType());

            ChunkPos cpos = Load.world(world)
                .getData()
                .randomFree(x -> WorldUtils.getUniqueDungeonAt(x, world)
                    .GUID()
                    .equals(uniqueDungeon.GUID()));

            Load.playerMapData(player)
                .initKey(pos, MapManager.getUniqueDungeonDimensionType(), player, cpos);

            key.shrink(1);
        }
    }

    public boolean hasEnoughFuel() {
        return this.fuel > getFuelNeeded();
    }

    public void spendFuelForActivation() {
        this.fuel -= getFuelNeeded();
    }

    public int getFuelNeeded() {
        return ModConfig.INSTANCE.Server.FUEL_NEEDED_PER_MAP_ACTIVATION.get();
    }

    @Override
    public ITextComponent getDisplayName() {
        return CLOC.blank("block.mmorpg.map_device");

    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {

        return new ContainerMapDevice(i, playerInventory, this, this.pos);

    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);

        return nbt;
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);

    }

}