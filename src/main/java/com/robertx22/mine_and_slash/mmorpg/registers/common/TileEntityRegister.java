package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.blocks.egg_loot_crate.EggLootCrateTileEntity;
import com.robertx22.mine_and_slash.blocks.item_modify_station.TileGearModify;
import com.robertx22.mine_and_slash.blocks.map_device.TileMapDevice;
import com.robertx22.mine_and_slash.blocks.repair_station.TileGearRepair;
import com.robertx22.mine_and_slash.blocks.salvage_station.TileGearSalvage;
import com.robertx22.mine_and_slash.dimensions.blocks.TileMapPortal;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.professions.blocks.alchemy.AlchemyTile;
import com.robertx22.mine_and_slash.professions.blocks.tinkering.TinkeringTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityRegister {

    @SubscribeEvent
    public static void onTileEntityRegistry(
            final RegistryEvent.Register<TileEntityType<?>> e) {

        IForgeRegistry<TileEntityType<?>> r = e.getRegistry();

        r.register(TileEntityType.Builder.create(AlchemyTile::new, BlockRegister.ALCHEMY_BLOCK)
                .build(null)
                .setRegistryName(BlockRegister.ALCHEMY_BLOCK_ID));

        r.register(TileEntityType.Builder.create(TinkeringTile::new, BlockRegister.TINKERING_BLOCK)
                .build(null)
                .setRegistryName(BlockRegister.TINKERING_BLOCK_ID));

        r.register(TileEntityType.Builder.create(TileMapPortal::new, BlockRegister.PORTAL_BLOCK)
                .build(null)
                .setRegistryName(BlockRegister.MAP_PORTAL_BLOCK_ID));

        r.register(TileEntityType.Builder.create(TileGearRepair::new, BlockRegister.BLOCK_GEAR_REPAIR)
                .build(null)
                .setRegistryName(BlockRegister.GEAR_REPAIR_ID));

        r.register(TileEntityType.Builder.create(TileGearModify::new, BlockRegister.BLOCK_GEAR_MODIFY)
                .build(null)
                .setRegistryName(BlockRegister.GEAR_MODIFY_ID));

        r.register(TileEntityType.Builder.create(TileGearSalvage::new, BlockRegister.BLOCK_GEAR_SALVAGE)
                .build(null)
                .setRegistryName(BlockRegister.GEAR_SALVAGE_ID));

        r.register(TileEntityType.Builder.create(TileMapDevice::new, BlockRegister.BLOCK_MAP_DEVICE)
                .build(null)
                .setRegistryName(BlockRegister.MAP_DEVICE_ID));

        r.register(TileEntityType.Builder.create(EggLootCrateTileEntity::new, BlockRegister.EGG_LOOT_CRATE_BLOCK)
                .build(null)
                .setRegistryName(BlockRegister.EGG_LOOT_CRATE_ID));

    }

}
