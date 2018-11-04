package com.robertx22.customitems.misc;

import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
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
		this.setUnlocalizedName("Player Level Up Token");
		this.setRegistryName(Ref.MODID + ":player_levelup");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		if (!worldIn.isRemote) {
			try {
				Unit unit = UnitSaving.Load(playerIn);

				if (unit.CheckIfCanLevelUp()) {

					unit.LevelUp();

					playerIn.sendMessage(new TextComponentString(
							TextFormatting.GREEN + "You have Leveled up! Current lvl: " + unit.level));

					UnitSaving.Save(playerIn, unit);

				} else {

					playerIn.sendMessage(new TextComponentString(
							TextFormatting.RED + "You don't have enough experience to Level Up."));

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, EmptyOrDecrease(playerIn.getHeldItem(handIn)));
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

		stack.setStackDisplayName(TextFormatting.YELLOW + "Player Levelup Token");

		tooltip.add("If your experience bar is full, use this item");
		tooltip.add("to levelup!");

	}

}
