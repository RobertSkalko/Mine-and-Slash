package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.blocks.beacon.CosmeticBeaconTile;
import com.robertx22.mine_and_slash.blocks.egg_loot_crate.EggLootCrateTileEntity;
import com.robertx22.mine_and_slash.blocks.item_modify_station.TileGearModify;
import com.robertx22.mine_and_slash.blocks.map_device.TileMapDevice;
import com.robertx22.mine_and_slash.blocks.repair_station.TileGearRepair;
import com.robertx22.mine_and_slash.blocks.salvage_station.TileGearSalvage;
import com.robertx22.mine_and_slash.database.spells.blocks.magma_flower.MagmaFlowerTileEntity;
import com.robertx22.mine_and_slash.database.spells.blocks.thorn_bush.ThornBushTileEntity;
import com.robertx22.mine_and_slash.dimensions.blocks.TileMapPortal;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.new_content.chests.MapChestTile;
import com.robertx22.mine_and_slash.professions.blocks.alchemy.AlchemyTile;
import com.robertx22.mine_and_slash.professions.blocks.tinkering.TinkeringTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityRegister {

    public static DeferredRegister<TileEntityType<?>> REG = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Ref.MODID);

    public static RegistryObject<TileEntityType<TileGearModify>> GEAR_MODIFY =
        REG.register(BlockRegister.GEAR_MODIFY.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(TileGearModify::new, BlockRegister.GEAR_MODIFY.get())
                .build(null));

    public static RegistryObject<TileEntityType<TileGearRepair>> GEAR_REPAIR =
        REG.register(BlockRegister.GEAR_REPAIR.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(TileGearRepair::new, BlockRegister.GEAR_REPAIR.get())
                .build(null));

    public static RegistryObject<TileEntityType<TileGearSalvage>> GEAR_SALVAGE =
        REG.register(BlockRegister.GEAR_SALVAGE.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(TileGearSalvage::new, BlockRegister.GEAR_SALVAGE.get())
                .build(null));

    public static RegistryObject<TileEntityType<AlchemyTile>> ALCHEMY =
        REG.register(BlockRegister.ALCHEMY.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(AlchemyTile::new, BlockRegister.ALCHEMY.get())
                .build(null));

    public static RegistryObject<TileEntityType<TinkeringTile>> TINKERING =
        REG.register(BlockRegister.TINKERING.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(TinkeringTile::new, BlockRegister.TINKERING.get())
                .build(null));

    public static RegistryObject<TileEntityType<TileMapPortal>> MAP_PORTAL =
        REG.register(BlockRegister.MAP_PORTAL.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(TileMapPortal::new, BlockRegister.MAP_PORTAL.get())
                .build(null));

    public static RegistryObject<TileEntityType<TileMapDevice>> MAP_DEVICE =
        REG.register(BlockRegister.MAP_DEVICE.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(TileMapDevice::new, BlockRegister.MAP_DEVICE.get())
                .build(null));

    public static RegistryObject<TileEntityType<EggLootCrateTileEntity>> EGG_LOOT_CRATE =
        REG.register(BlockRegister.EGG_LOOT_CRATE.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(EggLootCrateTileEntity::new, BlockRegister.EGG_LOOT_CRATE.get())
                .build(null));

    public static RegistryObject<TileEntityType<MagmaFlowerTileEntity>> MAGMA_FLOWER =
        REG.register(BlockRegister.MAGMA_FLOWER.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(MagmaFlowerTileEntity::new, BlockRegister.MAGMA_FLOWER.get())
                .build(null));

    public static RegistryObject<TileEntityType<ThornBushTileEntity>> THORN_BUSH =
        REG.register(BlockRegister.THORN_BUSH.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(ThornBushTileEntity::new, BlockRegister.THORN_BUSH.get())
                .build(null));

    public static RegistryObject<TileEntityType<CosmeticBeaconTile>> BEACON =
        REG.register(BlockRegister.BEACON.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(CosmeticBeaconTile::new, BlockRegister.BEACON.get())
                .build(null));

    public static RegistryObject<TileEntityType<MapChestTile>> MAP_CHEST =
        REG.register(BlockRegister.MAP_CHEST.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(MapChestTile::new, BlockRegister.MAP_CHEST.get())
                .build(null));

}
