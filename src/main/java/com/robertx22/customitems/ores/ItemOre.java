package com.robertx22.customitems.ores;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.customitems.blocks.BlockOre;
import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.database.lists.Rarities;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class ItemOre extends Item {
	public static HashMap<Integer, Item> ItemOres = new HashMap<Integer, Item>();
	public static HashMap<Integer, ItemBlock> ItemBlocks = new HashMap<Integer, ItemBlock>();
	public static HashMap<Integer, Block> Blocks = new HashMap<Integer, Block>();

	int rarity;

	public List<Integer> RepairValues = Arrays.asList(10, 25, 50, 100, 250, 500);

	public int GetFuelValue() {

		return RepairValues.get(rarity);

	}

	public ItemOre(String name, int rarity) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxDamage(0);
		maxStackSize = 64;
		setCreativeTab(CurrencyItem.CurrencyTab);
		this.rarity = rarity;

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		try {
			ItemRarity rar = Rarities.Items.get(this.rarity);
			stack.setStackDisplayName(rar.Color() + rar.Name() + " Ore");

			tooltip.add("Precious Ore used to repair gear.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void Register() {
		Rarities.Items.forEach((x) -> ItemOres.put(x.Rank(), new ItemOre(Ref.MODID + ":ore" + x.Rank(), x.Rank())));

		for (int i = 0; i < ItemOres.size(); i++) {
			BlockOre block = new BlockOre("ore_block" + i, Material.ROCK, ItemOres.get(i), 1);
			Blocks.put(i, block);
			ItemBlock itemblock = (ItemBlock) new ItemBlock(block).setRegistryName(Ref.MODID + ":ore_block" + i);
			ItemBlocks.put(i, itemblock);
		}

	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		ItemOres.values().forEach((x) -> event.getRegistry().register(x));
		ItemBlocks.values().forEach((x) -> event.getRegistry().register(x));
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		Blocks.values().forEach((x) -> event.getRegistry().register(x));

	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {

		ItemOres.values().forEach((x) -> RegisterUtils.registerRender(x));
		ItemBlocks.values().forEach((x) -> RegisterUtils.registerRender(x));

		for (Block block : Blocks.values()) {
			Item item = Item.getItemFromBlock((Block) block);
			ModelResourceLocation modelResourceLocation = new ModelResourceLocation(item.getRegistryName(),
					"inventory");
			ModelLoader.setCustomMeshDefinition(item, stack -> modelResourceLocation);

		}
	}

}
