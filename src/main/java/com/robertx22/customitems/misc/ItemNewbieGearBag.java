package com.robertx22.customitems.misc;

import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.database.gearitemslots.Sword;
import com.robertx22.generation.GearGen;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
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
public class ItemNewbieGearBag extends Item {

	@GameRegistry.ObjectHolder(Ref.MODID + ":newbie_gear_bag")
	public static final Item ITEM = null;

	public ItemNewbieGearBag() {
		this.setMaxDamage(0);
		this.setCreativeTab(CurrencyItem.CurrencyTab);
		this.setUnlocalizedName("Newbie Gear Bag");
		this.setRegistryName(Ref.MODID + ":newbie_gear_bag");
	}

	public static int ITEMS_AMOUNT = 6;

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		if (!worldIn.isRemote) {
			try {

				GearBlueprint weaponPrint = new GearBlueprint(1);
				weaponPrint.SetSpecificType(new Sword().Name());
				weaponPrint.LevelRange = false;

				GearBlueprint print = new GearBlueprint(1);
				print.LevelRange = false;

				GearItemData wepData = GearGen.CreateData(weaponPrint);
				wepData.isSalvagable = false;

				ItemStack weaponStack = GearGen.CreateStack(wepData);
				playerIn.dropItem(weaponStack, false, true);

				for (int i = 0; i < ITEMS_AMOUNT; i++) {

					GearItemData data = GearGen.CreateData(print);
					data.isSalvagable = false;
					ItemStack stack = GearGen.CreateStack(data);

					playerIn.dropItem(stack, false, true);
				}

				return new ActionResult<ItemStack>(EnumActionResult.PASS,
						EmptyOrDecrease(playerIn.getHeldItem(handIn)));

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
		event.getRegistry().register(new ItemNewbieGearBag());
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		RegisterUtils.registerRender(ITEM);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

		stack.setStackDisplayName(TextFormatting.YELLOW + "Newbie Gear Bag");

		tooltip.add("If you died and lost your newbie gear, use this");
		tooltip.add("to create more level 1 gear.");
		tooltip.add("At least 1 sword item is guaranteed.");
		tooltip.add("Do note, these items can't be salvaged!");
		tooltip.add(TextFormatting.BLUE + "Right click to use");

	}

}
