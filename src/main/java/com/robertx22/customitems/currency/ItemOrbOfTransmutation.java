package com.robertx22.customitems.currency;

import com.robertx22.generation.GearGen;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class ItemOrbOfTransmutation extends CurrencyItem implements ICurrencyItemEffect {
    @Override
    public String GUID() {
	return "orb_of_transmutation";
    }

    private static final String name = "orb_of_transmutation";

    @GameRegistry.ObjectHolder(Ref.MODID + ":orb_of_transmutation")
    public static final Item ITEM = null;

    public ItemOrbOfTransmutation() {

	super(name);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemOrbOfTransmutation());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

	GearItemData gear = Gear.Load(stack);

	GearBlueprint gearPrint = new GearBlueprint(gear.level);
	gearPrint.SetSpecificType(gear.gearTypeName);
	gearPrint.minRarity = 1;
	gearPrint.LevelRange = false;

	GearItemData newgear = GearGen.CreateData(gearPrint);
	gear.WriteOverDataThatShouldStay(newgear);

	ItemStack result = ItemStack.EMPTY;

	if (gear.changesItemStack()) {
	    result = GearGen.CreateStack(newgear);
	} else {
	    result = stack;
	    Gear.Save(result, newgear);
	}

	return stack;
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {

	GearItemData gear = Gear.Load(stack);

	if (gear != null && gear.Rarity == 0) {
	    return true;
	}

	return false;
    }

    @Override
    public int Tier() {
	return 0;
    }

    @Override
    public int Rank() {
	return 0;
    }
}