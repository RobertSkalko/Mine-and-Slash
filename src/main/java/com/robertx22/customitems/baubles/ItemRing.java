package com.robertx22.customitems.baubles;

import java.util.HashMap;

import com.robertx22.baubles.api.BaubleType;
import com.robertx22.customitems.bases.BaseBaublesItem;
import com.robertx22.database.lists.Rarities;
import com.robertx22.uncommon.utilityclasses.ModelUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ItemRing extends BaseBaublesItem {
	public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

	public ItemRing(int i, HashMap<Integer, Item> map) {
		super(i, map);

	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		Rarities.Items.forEach((x) -> Items.put(x.Rank(), new ItemRing(x.Rank(), Items)));
		Items.values().forEach((x) -> event.getRegistry().register(x));
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		Items.values().forEach((x) -> ModelUtils.registerRender(x));
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.RING;
	}

	@Override
	public String Name() {
		return "Ring";
	}

	/*
	 * @Override public ActionResult<ItemStack> onItemRightClick(World worldIn,
	 * EntityPlayer playerIn, EnumHand handIn) { new FrostBolt().cast(worldIn,
	 * playerIn, handIn, 50);
	 * 
	 * return new ActionResult<ItemStack>(EnumActionResult.PASS,
	 * playerIn.getHeldItem(handIn)); }
	 */
}
