package com.robertx22.customitems;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.customitems.blocks.BlockOre;
import com.robertx22.mmorpg.Ref;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NewItemCreator {

	public static List<Item> myCustomItems = new ArrayList<>();
	public static List<Block> myCustomBlocks = new ArrayList<>();

	public static final CreativeTabs MyModTab = new CreativeTabs(Ref.NAME) {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Items.DIAMOND_CHESTPLATE);
		}

	};

	static int[] nums = new int[] { 3, 3, 3, 3 };

	static ItemSword.ToolMaterial MAGIC_TOOL_MAT = EnumHelper.addToolMaterial("magic", 0, 500, 1F, 1F, 1);
	static ItemSword.ToolMaterial RARE_TOOL_MAT = EnumHelper.addToolMaterial("rare", 0, 700, 1F, 1F, 1);
	static ItemSword.ToolMaterial EPIC_TOOL_MAT = EnumHelper.addToolMaterial("epic", 0, 900, 1F, 1F, 1);
	static ItemSword.ToolMaterial LEGENDARY_TOOL_MAT = EnumHelper.addToolMaterial("legendary", 0, 1500, 1F, 1F, 1);
	static ItemSword.ToolMaterial MYTHICAL_TOOL_MAT = EnumHelper.addToolMaterial("mythical", 0, 2500, 1F, 1F, 1);

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
				ModelLoader.setCustomModelResourceLocation(item, 0,
						new ModelResourceLocation(item.getRegistryName(), "inventory"));
			}

			for (Block block : myCustomBlocks) {

				Item item = Item.getItemFromBlock((Block) block);

				ModelResourceLocation modelResourceLocation = new ModelResourceLocation(item.getRegistryName(),
						"inventory");

				ModelLoader.setCustomMeshDefinition(item, stack -> modelResourceLocation);

			}
		}
	}

}
