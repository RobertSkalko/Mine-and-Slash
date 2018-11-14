package com.robertx22.customitems.misc;

import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class ItemPlayerLevelUp extends Item {

	@GameRegistry.ObjectHolder(Ref.MODID + ":player_levelup")
	public static final Item ITEM = null;

	public ItemPlayerLevelUp() {
		this.setMaxDamage(0);
		this.setCreativeTab(CurrencyItem.CurrencyTab);

		RegisterItemUtils.RegisterItemName(this, "player_levelup");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		if (!worldIn.isRemote) {
			try {
				Unit unit = UnitSaving.Load(playerIn);

				if (unit.LevelUp(playerIn)) {

					UnitSaving.Save(playerIn, unit);

					return new ActionResult<ItemStack>(EnumActionResult.PASS,
							EmptyOrDecrease(playerIn.getHeldItem(handIn)));

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}

	private ItemStack EmptyOrDecrease(ItemStack stack) {
		if (stack.getCount() < 2) {
			stack = ItemStack.EMPTY;
		} else {
			stack.setCount(stack.getCount() - 1);
		}
		return stack;
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new ItemPlayerLevelUp());
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		RegisterUtils.registerRender(ITEM);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

		tooltip.add("If your experience bar is full, use this item");
		tooltip.add("to levelup!");

	}

}
