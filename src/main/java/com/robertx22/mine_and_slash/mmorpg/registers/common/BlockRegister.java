package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.blocks.egg_loot_crate.EggLootCrateBlock;
import com.robertx22.mine_and_slash.blocks.gear_factory_station.BlockGearFactory;
import com.robertx22.mine_and_slash.blocks.item_modify_station.BlockGearModify;
import com.robertx22.mine_and_slash.blocks.map_device.BlockMapDevice;
import com.robertx22.mine_and_slash.blocks.repair_station.BlockGearRepair;
import com.robertx22.mine_and_slash.blocks.salvage_station.BlockGearSalvage;
import com.robertx22.mine_and_slash.blocks.simple.AttunementBlock;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.dimensions.blocks.MapPortalBlock;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegister {

    public static final String GEAR_FACTORY_ID = Ref.MODID + ":gear_factory_station";
    public static final String GEAR_MODIFY_ID = Ref.MODID + ":modify_station";
    public static final String MAP_DEVICE_ID = Ref.MODID + ":map_device";
    public static final String GEAR_SALVAGE_ID = Ref.MODID + ":salvage_station";
    public static final String GEAR_REPAIR_ID = Ref.MODID + ":repair_station";
    public static final String ATTUNEMENT_ALTAR_ID = Ref.MODID + ":attunement_altar";
    public static final String EGG_LOOT_CRATE_ID = Ref.MODID + ":egg_loot_crate";
    public static final String MAP_PORTAL_BLOCK_ID = Ref.MODID + ":map_portal_block";

    // NEW BLOCK
    @ObjectHolder(EGG_LOOT_CRATE_ID)
    public static TileEntityType<?> EGG_LOOT_CRATE;
    @ObjectHolder(EGG_LOOT_CRATE_ID)
    public static Block EGG_LOOT_CRATE_BLOCK;
    //NEW BLOCK
    @ObjectHolder(MAP_PORTAL_BLOCK_ID)
    public static Block PORTAL_BLOCK;
    @ObjectHolder(MAP_PORTAL_BLOCK_ID)
    public static TileEntityType<?> TILE_PORTAL_BLOCK;
    // NEW BLOCK
    @ObjectHolder(ATTUNEMENT_ALTAR_ID)
    public static Block ATTUNEMENT_ALTAR_BLOCK;
    @ObjectHolder(ATTUNEMENT_ALTAR_ID)
    public static BlockItem ATTUNEMENT_ALTAR;
    // NEW BLOCK
    @ObjectHolder(GEAR_FACTORY_ID)
    public static TileEntityType<?> GEAR_FACTORY;
    @ObjectHolder(GEAR_FACTORY_ID)
    public static Block BLOCK_GEAR_FACTORY;
    @ObjectHolder(GEAR_FACTORY_ID)
    public static BlockItem ITEMBLOCK_GEAR_FACTORY;
    // NEW BLOCK
    @ObjectHolder(GEAR_MODIFY_ID)
    public static TileEntityType<?> GEAR_MODIFY;
    @ObjectHolder(GEAR_MODIFY_ID)
    public static Block BLOCK_GEAR_MODIFY;
    @ObjectHolder(GEAR_MODIFY_ID)
    public static BlockItem ITEMBLOCK_GEAR_MODIFY;
    // NEW BLOCK
    @ObjectHolder(GEAR_REPAIR_ID)
    public static TileEntityType<?> GEAR_REPAIR;
    @ObjectHolder(GEAR_REPAIR_ID)
    public static Block BLOCK_GEAR_REPAIR;
    @ObjectHolder(GEAR_REPAIR_ID)
    public static BlockItem ITEMBLOCK_GEAR_REPAIR;
    // NEW BLOCK
    @ObjectHolder(GEAR_SALVAGE_ID)
    public static TileEntityType<?> GEAR_SALVAGE;
    @ObjectHolder(GEAR_SALVAGE_ID)
    public static Block BLOCK_GEAR_SALVAGE;
    @ObjectHolder(GEAR_SALVAGE_ID)
    public static BlockItem ITEMBLOCK_GEAR_SALVAGE;
    // NEW BLOCK
    @ObjectHolder(MAP_DEVICE_ID)
    public static TileEntityType<?> MAP_DEVICE;
    @ObjectHolder(MAP_DEVICE_ID)
    public static Block BLOCK_MAP_DEVICE;
    @ObjectHolder(MAP_DEVICE_ID)
    public static BlockItem ITEMBLOCK_MAP_DEVICE;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> reg = event.getRegistry();

        reg.register(new BlockGearFactory().setRegistryName(GEAR_FACTORY_ID));
        reg.register(new BlockMapDevice().setRegistryName(MAP_DEVICE_ID));
        reg.register(new BlockGearSalvage().setRegistryName(GEAR_SALVAGE_ID));
        reg.register(new BlockGearRepair().setRegistryName(GEAR_REPAIR_ID));
        reg.register(new BlockGearModify().setRegistryName(GEAR_MODIFY_ID));
        reg.register(new AttunementBlock().setRegistryName(ATTUNEMENT_ALTAR_ID));
        reg.register(new MapPortalBlock().setRegistryName(MAP_PORTAL_BLOCK_ID));
        reg.register(new EggLootCrateBlock().setRegistryName(EGG_LOOT_CRATE_ID));

        ItemOre.RegisterBlocks(event);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> reg = event.getRegistry();
        Item.Properties stationProp = new Item.Properties().group(CreativeTabs.MyModTab);

        reg.register(new BlockItem(BLOCK_GEAR_FACTORY, stationProp).setRegistryName(GEAR_FACTORY_ID));
        reg.register(new BlockItem(BLOCK_GEAR_REPAIR, stationProp).setRegistryName(GEAR_REPAIR_ID));
        reg.register(new BlockItem(BLOCK_GEAR_MODIFY, stationProp).setRegistryName(GEAR_MODIFY_ID));
        reg.register(new BlockItem(BLOCK_MAP_DEVICE, stationProp).setRegistryName(MAP_DEVICE_ID));
        reg.register(new BlockItem(BLOCK_GEAR_SALVAGE, stationProp).setRegistryName(GEAR_SALVAGE_ID));
        reg.register(new BlockItem(ATTUNEMENT_ALTAR_BLOCK, stationProp).setRegistryName(ATTUNEMENT_ALTAR_ID));
        reg.register(new BlockItem(EGG_LOOT_CRATE_BLOCK, stationProp).setRegistryName(EGG_LOOT_CRATE_ID));

        ItemOre.RegisterItems(event);
    }
}
