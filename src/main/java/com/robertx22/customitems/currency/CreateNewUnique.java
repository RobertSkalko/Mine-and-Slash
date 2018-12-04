package com.robertx22.customitems.currency;

import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.generation.UniqueGearGen;
import com.robertx22.generation.blueprints.UniqueBlueprint;
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
public class CreateNewUnique extends CurrencyItem implements ICurrencyItemEffect {

    private static final String name = "create_new_unique";

    @GameRegistry.ObjectHolder(Ref.MODID + ":create_new_unique")
    public static final Item ITEM = null;

    public CreateNewUnique() {

	super(name);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new CreateNewUnique());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

	tooltip.add("Transforms a Unique item into another Unique");
	tooltip.add("item of the same tier.");

	this.TooltipQuote(tooltip, "Don't want it? Transform it!");
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack) {

	GearItemData gear = Gear.Load(stack);

	UniqueBlueprint gearPrint = new UniqueBlueprint(gear.level, gear.uniqueStats.getUniqueItem().Tier(), false);
	gearPrint.SetSpecificRarity(new UniqueItem().Rank());
	gearPrint.LevelRange = false;

	GearItemData newgear = UniqueGearGen.CreateData(gearPrint);

	int tries = 0; // in case there's only 1 unique in a tier
	while (newgear.gearTypeName.equals(gear.gearTypeName) && tries < 10) {
	    newgear = UniqueGearGen.CreateData(gearPrint);
	    tries++;
	}

	gear.WriteOverDataThatShouldStay(newgear);

	return UniqueGearGen.CreateStack(newgear);
    }

    @Override
    public boolean CanItemBeModified(ItemStack stack) {

	GearItemData gear = Gear.Load(stack);

	if (gear != null && gear.isUnique) {
	    return true;
	}

	return false;
    }

    @Override
    public int Tier() {
	return 13;
    }

    @Override
    public int Weight() {
	return this.LegendaryWeight;
    }
}