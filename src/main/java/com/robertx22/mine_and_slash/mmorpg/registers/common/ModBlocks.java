package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.blocks.item_modify_station.BlockGearModify;
import com.robertx22.mine_and_slash.blocks.map_device.BlockMapDevice;
import com.robertx22.mine_and_slash.blocks.repair_station.BlockGearRepair;
import com.robertx22.mine_and_slash.blocks.salvage_station.BlockGearSalvage;
import com.robertx22.mine_and_slash.blocks.scrabble.ScrabbleBlock;
import com.robertx22.mine_and_slash.database.spells.blocks.holy_flower.HolyFlowerBlock;
import com.robertx22.mine_and_slash.database.spells.blocks.magma_flower.MagmaFlowerBlock;
import com.robertx22.mine_and_slash.database.spells.blocks.thorn_bush.ThornBushBlock;
import com.robertx22.mine_and_slash.dimensions.blocks.DungeonPortalBlock;
import com.robertx22.mine_and_slash.dimensions.blocks.MapPortalBlock;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.new_content.chests.MapChestBlock;
import com.robertx22.mine_and_slash.new_content.chests.trapped.TrappedMapChestBlock;
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
public class ModBlocks {

    public static DeferredRegister<Block> REG = new DeferredRegister<>(ForgeRegistries.BLOCKS, Ref.MODID);

    public static RegistryObject<BlockGearModify> GEAR_MODIFY = REG.register("modify_station", BlockGearModify::new);
    public static RegistryObject<BlockGearSalvage> GEAR_SALVAGE = REG.register("salvage_station", BlockGearSalvage::new);
    public static RegistryObject<BlockGearRepair> GEAR_REPAIR = REG.register("repair_station", BlockGearRepair::new);
    public static RegistryObject<BlockMapDevice> MAP_DEVICE = REG.register("map_device", BlockMapDevice::new);
    public static RegistryObject<MapPortalBlock> MAP_PORTAL = REG.register("map_portal_block", MapPortalBlock::new);

    public static RegistryObject<MagmaFlowerBlock> MAGMA_FLOWER = REG.register("magma_flower", MagmaFlowerBlock::new);
    public static RegistryObject<HolyFlowerBlock> HOLY_FLOWER = REG.register("holy_flower", HolyFlowerBlock::new);
    public static RegistryObject<ThornBushBlock> THORN_BUSH = REG.register("thorn_bush", ThornBushBlock::new);

    public static RegistryObject<DungeonPortalBlock> DUNGEON_PORTAL = REG.register("dungeon_portal", DungeonPortalBlock::new);

    public static RegistryObject<MapChestBlock> MAP_CHEST = REG.register("map_chest", MapChestBlock::new);
    public static RegistryObject<MapChestBlock> TRAPPED_MAP_CHEST = REG.register("trapped_map_chest", TrappedMapChestBlock::new);
    public static RegistryObject<ScrabbleBlock> SCRABBLE = REG.register("scrabble", ScrabbleBlock::new);

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
