package com.robertx22.customitems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.mmorpg.Ref;
import com.robertx22.utilityclasses.Utils;

public class NewItemCreator {

    public static List<Item> myCustomItems = new ArrayList<>();
    public static List<Block> myCustomBlocks = new ArrayList<>();

    public static final CreativeTabs MyModTab = new CreativeTabs(Ref.NAME) {

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.DIAMOND_CHESTPLATE);
        }

    };

    static int[] nums = new int[]{
            3,
            3,
            3,
            3};

    static ItemSword.ToolMaterial MAGIC_TOOL_MAT = EnumHelper.addToolMaterial("magic", 0, 500, 1F, 1F, 1);
    static ItemSword.ToolMaterial RARE_TOOL_MAT = EnumHelper.addToolMaterial("rare", 0, 700, 1F, 1F, 1);
    static ItemSword.ToolMaterial EPIC_TOOL_MAT = EnumHelper.addToolMaterial("epic", 0, 900, 1F, 1F, 1);
    static ItemSword.ToolMaterial LEGENDARY_TOOL_MAT = EnumHelper.addToolMaterial("legendary", 0, 1500, 1F, 1F, 1);
    static ItemSword.ToolMaterial MYTHICAL_TOOL_MAT = EnumHelper.addToolMaterial("mythical", 0, 2500, 1F, 1F, 1);

    static ItemArmor.ArmorMaterial MAGIC_MAT = EnumHelper.addArmorMaterial("magic", Utils.setLocation
                    ("magic"), 20,
            nums, 0, null, 0);

    static ItemArmor.ArmorMaterial RARE_MAT = EnumHelper.addArmorMaterial("rare", Utils.setLocation
                    ("rare"), 20,
            nums, 0, null, 0);

    static ItemArmor.ArmorMaterial EPIC_MAT = EnumHelper.addArmorMaterial("epic", Utils.setLocation
                    ("epic"), 20,
            nums, 0, null, 0);

    static ItemArmor.ArmorMaterial LEGENDARY_MAT = EnumHelper.addArmorMaterial("legendary", Utils.setLocation
                    ("legendary"), 20,
            nums, 0, null, 0);

    static ItemArmor.ArmorMaterial MYTHICAL_MAT = EnumHelper.addArmorMaterial("mythical", Utils.setLocation
                    ("mythical"), 20,
            nums, 0, null, 0);

    public static void createCustomItems() {
        createCustomItemsAndAddToClass();
        addAllCustomItemsToArray();
    }

    private static void createCustomItemsAndAddToClass() {
        MyItems.magic_powder = new ItemBasic("magic_powder");
        MyItems.rare_powder = new ItemBasic("rare_powder");
        MyItems.epic_powder = new ItemBasic("epic_powder");
        MyItems.legendary_powder = new ItemBasic("legendary_powder");
        MyItems.mythical_powder = new ItemBasic("mythical_powder");

        MyItems.magic_ore = new ItemBasic("magic_ore");
        MyItems.rare_ore = new ItemBasic("rare_ore");
        MyItems.epic_ore = new ItemBasic("epic_ore");
        MyItems.legendary_ore = new ItemBasic("legendary_ore");
        MyItems.mythical_ore = new ItemBasic("mythical_ore");

        MyItems.magic_socket = new ItemBasic("magic_socket").setMaxStackSize(1);
        MyItems.rare_socket = new ItemBasic("rare_socket").setMaxStackSize(1);
        MyItems.epic_socket = new ItemBasic("epic_socket").setMaxStackSize(1);
        MyItems.legendary_socket = new ItemBasic("legendary_socket").setMaxStackSize(1);
        MyItems.mythical_socket = new ItemBasic("mythical_socket").setMaxStackSize(1);

        MyItems.magic_sword = new SwordBasic(MAGIC_TOOL_MAT);
        MyItems.rare_sword = new SwordBasic(RARE_TOOL_MAT);
        MyItems.epic_sword = new SwordBasic(EPIC_TOOL_MAT);
        MyItems.legendary_sword = new SwordBasic(LEGENDARY_TOOL_MAT);
        MyItems.mythical_sword = new SwordBasic(MYTHICAL_TOOL_MAT);

        MyItems.magic_helmet = new ArmorBasic(MAGIC_MAT, 0, EntityEquipmentSlot.HEAD);
        MyItems.magic_chestplate = new ArmorBasic(MAGIC_MAT, 0, EntityEquipmentSlot.CHEST);
        MyItems.magic_boots = new ArmorBasic(MAGIC_MAT, 0, EntityEquipmentSlot.FEET);
        MyItems.magic_leggins = new ArmorBasic(MAGIC_MAT, 0, EntityEquipmentSlot.LEGS);

        MyItems.rare_helmet = new ArmorBasic(RARE_MAT, 0, EntityEquipmentSlot.HEAD);
        MyItems.rare_chestplate = new ArmorBasic(RARE_MAT, 0, EntityEquipmentSlot.CHEST);
        MyItems.rare_boots = new ArmorBasic(RARE_MAT, 0, EntityEquipmentSlot.FEET);
        MyItems.rare_leggins = new ArmorBasic(RARE_MAT, 0, EntityEquipmentSlot.LEGS);

        MyItems.epic_helmet = new ArmorBasic(EPIC_MAT, 0, EntityEquipmentSlot.HEAD);
        MyItems.epic_chestplate = new ArmorBasic(EPIC_MAT, 0, EntityEquipmentSlot.CHEST);
        MyItems.epic_boots = new ArmorBasic(EPIC_MAT, 0, EntityEquipmentSlot.FEET);
        MyItems.epic_leggins = new ArmorBasic(EPIC_MAT, 0, EntityEquipmentSlot.LEGS);

        MyItems.legendary_helmet = new ArmorBasic(LEGENDARY_MAT, 0, EntityEquipmentSlot.HEAD);
        MyItems.legendary_chestplate = new ArmorBasic(LEGENDARY_MAT, 0, EntityEquipmentSlot.CHEST);
        MyItems.legendary_boots = new ArmorBasic(LEGENDARY_MAT, 0, EntityEquipmentSlot.FEET);
        MyItems.legendary_leggins = new ArmorBasic(LEGENDARY_MAT, 0, EntityEquipmentSlot.LEGS);

        MyItems.mythical_helmet = new ArmorBasic(MYTHICAL_MAT, 0, EntityEquipmentSlot.HEAD);
        MyItems.mythical_chestplate = new ArmorBasic(MYTHICAL_MAT, 0, EntityEquipmentSlot.CHEST);
        MyItems.mythical_boots = new ArmorBasic(MYTHICAL_MAT, 0, EntityEquipmentSlot.FEET);
        MyItems.mythical_leggins = new ArmorBasic(MYTHICAL_MAT, 0, EntityEquipmentSlot.LEGS);

        MyItems.magic_ore_block = new BlockOre("magic_ore_block", Material.ROCK, MyItems.magic_ore, 1);

        MyItems.magic_ore_block_item = (ItemBlock) new ItemBlock(MyItems.magic_ore_block)
                .setRegistryName(MyItems.magic_ore_block.getRegistryName());

        MyItems.rare_ore_block = new BlockOre("rare_ore_block", Material.ROCK, MyItems.rare_ore, 1);

        MyItems.rare_ore_block_item = (ItemBlock) new ItemBlock(MyItems.rare_ore_block)
                .setRegistryName(MyItems.rare_ore_block.getRegistryName());

        MyItems.epic_ore_block = new BlockOre("epic_ore_block", Material.ROCK, MyItems.epic_ore, 1);

        MyItems.epic_ore_block_item = (ItemBlock) new ItemBlock(MyItems.epic_ore_block)
                .setRegistryName(MyItems.epic_ore_block.getRegistryName());

        MyItems.legendary_ore_block = new BlockOre("legendary_ore_block", Material.ROCK, MyItems.legendary_ore, 1);

        MyItems.legendary_ore_block_item = (ItemBlock) new ItemBlock(MyItems.legendary_ore_block)
                .setRegistryName(MyItems.legendary_ore_block.getRegistryName());

        MyItems.mythical_ore_block = new BlockOre("mythical_ore_block", Material.ROCK, MyItems.mythical_ore, 1);

        MyItems.mythical_ore_block_item = (ItemBlock) new ItemBlock(MyItems.mythical_ore_block)
                .setRegistryName(MyItems.mythical_ore_block.getRegistryName());

    }

    private static void addAllCustomItemsToArray() {
        myCustomItems.add(MyItems.magic_powder);
        myCustomItems.add(MyItems.rare_powder);
        myCustomItems.add(MyItems.epic_powder);
        myCustomItems.add(MyItems.legendary_powder);
        myCustomItems.add(MyItems.mythical_powder);

        myCustomItems.add(MyItems.magic_ore);
        myCustomItems.add(MyItems.rare_ore);
        myCustomItems.add(MyItems.epic_ore);
        myCustomItems.add(MyItems.legendary_ore);
        myCustomItems.add(MyItems.mythical_ore);

        myCustomItems.add(MyItems.magic_socket);
        myCustomItems.add(MyItems.rare_socket);
        myCustomItems.add(MyItems.epic_socket);
        myCustomItems.add(MyItems.legendary_socket);
        myCustomItems.add(MyItems.mythical_socket);

        myCustomItems.add((MyItems.magic_helmet));
        myCustomItems.add((MyItems.magic_chestplate));
        myCustomItems.add((MyItems.magic_boots));
        myCustomItems.add((MyItems.magic_leggins));

        myCustomItems.add((MyItems.rare_helmet));
        myCustomItems.add((MyItems.rare_chestplate));
        myCustomItems.add((MyItems.rare_boots));
        myCustomItems.add((MyItems.rare_leggins));

        myCustomItems.add((MyItems.epic_helmet));
        myCustomItems.add((MyItems.epic_chestplate));
        myCustomItems.add((MyItems.epic_boots));
        myCustomItems.add((MyItems.epic_leggins));

        myCustomItems.add((MyItems.legendary_helmet));
        myCustomItems.add((MyItems.legendary_chestplate));
        myCustomItems.add((MyItems.legendary_boots));
        myCustomItems.add((MyItems.legendary_leggins));

        myCustomItems.add((MyItems.mythical_helmet));
        myCustomItems.add((MyItems.mythical_chestplate));
        myCustomItems.add((MyItems.mythical_boots));
        myCustomItems.add((MyItems.mythical_leggins));

        myCustomItems.add((MyItems.magic_sword));
        myCustomItems.add((MyItems.rare_sword));
        myCustomItems.add((MyItems.epic_sword));
        myCustomItems.add((MyItems.legendary_sword));
        myCustomItems.add((MyItems.mythical_sword));

        myCustomBlocks.add(MyItems.magic_ore_block);
        myCustomItems.add(MyItems.magic_ore_block_item);

        myCustomBlocks.add(MyItems.rare_ore_block);
        myCustomItems.add(MyItems.rare_ore_block_item);

        myCustomBlocks.add(MyItems.epic_ore_block);
        myCustomItems.add(MyItems.epic_ore_block_item);

        myCustomBlocks.add(MyItems.legendary_ore_block);
        myCustomItems.add(MyItems.legendary_ore_block_item);

        myCustomBlocks.add(MyItems.mythical_ore_block);
        myCustomItems.add(MyItems.mythical_ore_block_item);

    }

    @Mod.EventBusSubscriber
    private static class EventHandler {

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {

            for (Item item : myCustomItems) {
                event.getRegistry().register(item);
            }

        }

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {

            for (Block block : myCustomBlocks) {
                event.getRegistry().register(block);
            }

        }

        @SubscribeEvent
        public static void registerRenders(ModelRegistryEvent event) {

            for (Item item : myCustomItems) {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item
                        .getRegistryName(),
                        "inventory"));
            }

            for (Block block : myCustomBlocks) {

                Item item = Item.getItemFromBlock((Block) block);

                ModelResourceLocation modelResourceLocation = new ModelResourceLocation(item
                        .getRegistryName(),
                        "inventory");

                ModelLoader.setCustomMeshDefinition(item, stack -> modelResourceLocation);

            }
        }
    }

}
