package com.robertx22.customitems.currency;

import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.db_lists.Rarities;
import com.robertx22.generation.GearGen;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class ItemStoneOfHope extends CurrencyItem implements ICurrencyItemEffect {

	private static final String name = "stone_of_hope";

	@GameRegistry.ObjectHolder(Ref.MODID + ":stone_of_hope")
	public static final Item ITEM = null;

	public ItemStoneOfHope() {

		super(name);

	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new ItemStoneOfHope());
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		RegisterUtils.registerRender(ITEM);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

		tooltip.add("Transforms any rarity item into a higher rarity");

		this.TooltipQuote(tooltip, "When there is hope, there is a way.");
	}

	@Override
	public ItemStack ModifyItem(ItemStack stack) {

		GearItemData gear = Gear.Load(stack);

		GearBlueprint gearPrint = new GearBlueprint(gear.level);
		gearPrint.SetSpecificType(gear.gearTypeName);
		gearPrint.minRarity = gear.Rarity + 1;
		gearPrint.LevelRange = false;

		GearItemData newgear = GearGen.CreateData(gearPrint);
		gear.WriteOverDataThatShouldStay(newgear);

		return GearGen.CreateStack(newgear);

	}

	@Override
	public boolean CanItemBeModified(ItemStack stack) {

		GearItemData gear = Gear.Load(stack);

		if (gear != null && gear.Rarity < Rarities.MAXIMUM_ITEM_RARITY) {
			return true;
		}

		return false;
	}

	@Override
	public int Weight() {
		return this.LegendaryWeight;
	}

	@Override
	public int Tier() {
		return 2;
	}

}