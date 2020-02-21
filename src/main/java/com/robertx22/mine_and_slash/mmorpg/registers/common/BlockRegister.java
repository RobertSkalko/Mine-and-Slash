package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.blocks.beacon.CosmeticBeaconBlock;
import com.robertx22.mine_and_slash.blocks.egg_loot_crate.EggLootCrateBlock;
import com.robertx22.mine_and_slash.blocks.item_modify_station.BlockGearModify;
import com.robertx22.mine_and_slash.blocks.map_device.BlockMapDevice;
import com.robertx22.mine_and_slash.blocks.repair_station.BlockGearRepair;
import com.robertx22.mine_and_slash.blocks.salvage_station.BlockGearSalvage;
import com.robertx22.mine_and_slash.dimensions.blocks.MapPortalBlock;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.professions.blocks.alchemy.AlchemyBlock;
import com.robertx22.mine_and_slash.professions.blocks.tinkering.TinkeringBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegister {

    public static DeferredRegister<Block> REG = new DeferredRegister<>(ForgeRegistries.BLOCKS, Ref.MODID);

    public static RegistryObject<BlockGearModify> GEAR_MODIFY = REG.register("modify_station", BlockGearModify::new);
    public static RegistryObject<BlockGearSalvage> GEAR_SALVAGE = REG.register("salvage_station", BlockGearSalvage::new);
    public static RegistryObject<BlockGearRepair> GEAR_REPAIR = REG.register("repair_station", BlockGearRepair::new);
    public static RegistryObject<BlockMapDevice> MAP_DEVICE = REG.register("map_device", BlockMapDevice::new);
    public static RegistryObject<EggLootCrateBlock> EGG_LOOT_CRATE = REG.register("egg_loot_crate", EggLootCrateBlock::new);
    public static RegistryObject<MapPortalBlock> MAP_PORTAL = REG.register("map_portal_block", MapPortalBlock::new);
    public static RegistryObject<CosmeticBeaconBlock> BEACON = REG.register("beacon", CosmeticBeaconBlock::new);

    public static RegistryObject<AlchemyBlock> ALCHEMY = REG.register("alchemy_station", AlchemyBlock::new);
    public static RegistryObject<TinkeringBlock> TINKERING = REG.register("tinkering_station", TinkeringBlock::new);

    public static RegistryObject<TinkeringBlock> MAGMA_FLOWER = REG.register("magma_flower", TinkeringBlock::new);
    public static RegistryObject<TinkeringBlock> THORN_BUSH = REG.register("thorn_bush", TinkeringBlock::new);

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> reg = event.getRegistry();

        ItemOre.RegisterBlocks(event);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> reg = event.getRegistry();
        ItemOre.RegisterItems(event);
    }
}
