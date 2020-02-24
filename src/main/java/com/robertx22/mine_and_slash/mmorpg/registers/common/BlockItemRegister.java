package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockItemRegister {

    public static DeferredRegister<Item> REG = new DeferredRegister<>(ForgeRegistries.ITEMS, Ref.MODID);

    public static RegistryObject<Item> GEAR_MODIFY = of(BlockRegister.GEAR_MODIFY);
    public static RegistryObject<Item> GEAR_REPAIR = of(BlockRegister.GEAR_REPAIR);
    public static RegistryObject<Item> GEAR_SALVAGE = of(BlockRegister.GEAR_SALVAGE);
    public static RegistryObject<Item> MAP_DEVICE = of(BlockRegister.MAP_DEVICE);

    public static RegistryObject<Item> TINKERING = of(BlockRegister.TINKERING);
    public static RegistryObject<Item> ALCHEMY = of(BlockRegister.ALCHEMY);

    public static RegistryObject<Item> EGG_CRATE = of(BlockRegister.EGG_LOOT_CRATE);

    public static RegistryObject<Item> MAGMA_FLOWER = of(BlockRegister.MAGMA_FLOWER);
    public static RegistryObject<Item> THORN_BUSH = of(BlockRegister.THORN_BUSH);

    public static RegistryObject<Item> BEACON = of(BlockRegister.BEACON);

    public static RegistryObject<Item> DUNGEON_PORTAL = of(BlockRegister.DUNGEON_PORTAL);

    static Item.Properties stationProp = new Item.Properties().group(CreativeTabs.MyModTab);

    static <T extends Block> RegistryObject<Item> of(RegistryObject<T> block) {
        return REG.register(block.getId()
            .getPath(), () -> new BlockItem(block.get(), stationProp));
    }

}
