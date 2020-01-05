package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.blocks.egg_loot_crate.EggLootCrateBlock;
import com.robertx22.mine_and_slash.blocks.item_modify_station.BlockGearModify;
import com.robertx22.mine_and_slash.blocks.map_device.BlockMapDevice;
import com.robertx22.mine_and_slash.blocks.repair_station.BlockGearRepair;
import com.robertx22.mine_and_slash.blocks.salvage_station.BlockGearSalvage;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.dimensions.blocks.MapPortalBlock;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.professions.blocks.alchemy.AlchemyBlock;
import com.robertx22.mine_and_slash.professions.blocks.tinkering.TinkeringBlock;
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

    public static final String GEAR_MODIFY_ID = Ref.MODID + ":modify_station";
    public static final String MAP_DEVICE_ID = Ref.MODID + ":map_device";
    public static final String GEAR_SALVAGE_ID = Ref.MODID + ":salvage_station";
    public static final String GEAR_REPAIR_ID = Ref.MODID + ":repair_station";
    public static final String EGG_LOOT_CRATE_ID = Ref.MODID + ":egg_loot_crate";
    public static final String MAP_PORTAL_BLOCK_ID = Ref.MODID + ":map_portal_block";

    static final String ALCHEMY_BLOCK_ID = Ref.MODID + ":" + "alchemy_station";
    static final String TINKERING_BLOCK_ID = Ref.MODID + ":" + "tinkering_station";

    // NEW BLOCK
    @ObjectHolder(TINKERING_BLOCK_ID)
    public static TileEntityType<?> TINKERING_TILE;
    @ObjectHolder(TINKERING_BLOCK_ID)
    public static Block TINKERING_BLOCK;
    // NEW BLOCK
    @ObjectHolder(ALCHEMY_BLOCK_ID)
    public static TileEntityType<?> ALCHEMY_TILE;
    @ObjectHolder(ALCHEMY_BLOCK_ID)
    public static Block ALCHEMY_BLOCK;
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

        reg.register(new BlockMapDevice().setRegistryName(MAP_DEVICE_ID));
        reg.register(new BlockGearSalvage().setRegistryName(GEAR_SALVAGE_ID));
        reg.register(new BlockGearRepair().setRegistryName(GEAR_REPAIR_ID));
        reg.register(new BlockGearModify().setRegistryName(GEAR_MODIFY_ID));
        reg.register(new MapPortalBlock().setRegistryName(MAP_PORTAL_BLOCK_ID));
        reg.register(new EggLootCrateBlock().setRegistryName(EGG_LOOT_CRATE_ID));

        reg.register(new AlchemyBlock().setRegistryName(ALCHEMY_BLOCK_ID));
        reg.register(new TinkeringBlock().setRegistryName(TINKERING_BLOCK_ID));

        ItemOre.RegisterBlocks(event);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> reg = event.getRegistry();
        Item.Properties stationProp = new Item.Properties().group(CreativeTabs.MyModTab);

        reg.register(new BlockItem(BLOCK_GEAR_REPAIR, stationProp).setRegistryName(GEAR_REPAIR_ID));
        reg.register(new BlockItem(BLOCK_GEAR_MODIFY, stationProp).setRegistryName(GEAR_MODIFY_ID));
        reg.register(new BlockItem(BLOCK_MAP_DEVICE, stationProp).setRegistryName(MAP_DEVICE_ID));
        reg.register(new BlockItem(BLOCK_GEAR_SALVAGE, stationProp).setRegistryName(GEAR_SALVAGE_ID));
        reg.register(new BlockItem(EGG_LOOT_CRATE_BLOCK, stationProp).setRegistryName(EGG_LOOT_CRATE_ID));

        reg.register(new BlockItem(ALCHEMY_BLOCK, stationProp).setRegistryName(ALCHEMY_BLOCK_ID));
        reg.register(new BlockItem(TINKERING_BLOCK, stationProp).setRegistryName(TINKERING_BLOCK_ID));

        ItemOre.RegisterItems(event);
    }
}
