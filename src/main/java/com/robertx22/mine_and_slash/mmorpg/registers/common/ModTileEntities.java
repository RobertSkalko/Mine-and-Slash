package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.blocks.item_modify_station.TileGearModify;
import com.robertx22.mine_and_slash.blocks.map_device.TileMapDevice;
import com.robertx22.mine_and_slash.blocks.repair_station.TileGearRepair;
import com.robertx22.mine_and_slash.blocks.salvage_station.TileGearSalvage;
import com.robertx22.mine_and_slash.blocks.scrabble.ScrabbleTile;
import com.robertx22.mine_and_slash.database.spells.blocks.magma_flower.MagmaFlowerTileEntity;
import com.robertx22.mine_and_slash.database.spells.blocks.thorn_bush.ThornBushTileEntity;
import com.robertx22.mine_and_slash.dimensions.blocks.TileMapPortal;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.new_content.chests.MapChestTile;
import com.robertx22.mine_and_slash.professions.blocks.alchemy.AlchemyTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntities {

    public static DeferredRegister<TileEntityType<?>> REG = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Ref.MODID);

    public static RegistryObject<TileEntityType<TileGearModify>> GEAR_MODIFY =
        REG.register(ModBlocks.GEAR_MODIFY.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(TileGearModify::new, ModBlocks.GEAR_MODIFY.get())
                .build(null));

    public static RegistryObject<TileEntityType<TileGearRepair>> GEAR_REPAIR =
        REG.register(ModBlocks.GEAR_REPAIR.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(TileGearRepair::new, ModBlocks.GEAR_REPAIR.get())
                .build(null));

    public static RegistryObject<TileEntityType<TileGearSalvage>> GEAR_SALVAGE =
        REG.register(ModBlocks.GEAR_SALVAGE.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(TileGearSalvage::new, ModBlocks.GEAR_SALVAGE.get())
                .build(null));

    public static RegistryObject<TileEntityType<AlchemyTile>> ALCHEMY =
        REG.register(ModBlocks.ALCHEMY.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(AlchemyTile::new, ModBlocks.ALCHEMY.get())
                .build(null));

    public static RegistryObject<TileEntityType<TileMapPortal>> MAP_PORTAL =
        REG.register(ModBlocks.MAP_PORTAL.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(TileMapPortal::new, ModBlocks.MAP_PORTAL.get())
                .build(null));

    public static RegistryObject<TileEntityType<TileMapDevice>> MAP_DEVICE =
        REG.register(ModBlocks.MAP_DEVICE.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(TileMapDevice::new, ModBlocks.MAP_DEVICE.get())
                .build(null));

    public static RegistryObject<TileEntityType<MagmaFlowerTileEntity>> MAGMA_FLOWER =
        REG.register(ModBlocks.MAGMA_FLOWER.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(MagmaFlowerTileEntity::new, ModBlocks.MAGMA_FLOWER.get())
                .build(null));

    public static RegistryObject<TileEntityType<ThornBushTileEntity>> THORN_BUSH =
        REG.register(ModBlocks.THORN_BUSH.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(ThornBushTileEntity::new, ModBlocks.THORN_BUSH.get())
                .build(null));

    public static RegistryObject<TileEntityType<MapChestTile>> MAP_CHEST =
        REG.register(ModBlocks.MAP_CHEST.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(MapChestTile::new, ModBlocks.MAP_CHEST.get(), ModBlocks.TRAPPED_MAP_CHEST.get())
                .build(null));

    public static RegistryObject<TileEntityType<ScrabbleTile>> SCRABBLE =
        REG.register(ModBlocks.SCRABBLE.getId()
                .getPath(),
            () -> TileEntityType.Builder.create(ScrabbleTile::new, ModBlocks.SCRABBLE.get())
                .build(null));

}
