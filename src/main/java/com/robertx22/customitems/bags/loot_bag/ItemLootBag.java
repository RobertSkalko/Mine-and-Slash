package com.robertx22.customitems.bags.loot_bag;

import com.robertx22.customitems.bags.BaseBagItem;
import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.gui.GuiHandler;
import com.robertx22.mmorpg.gui.GuiHandlerRegistry;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Spell;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class ItemLootBag extends BaseBagItem {

    public static final int GUI_NUMBER = 356515;

    @GameRegistry.ObjectHolder(Ref.MODID + ":loot_bag")
    public static final Item ITEM = null;

    private static final String TAG_ITEMS = "InvItems";

    public ItemLootBag() {
	super("loot_bag");

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemLootBag());

	NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, GuiHandlerRegistry.getInstance());
	GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandler(), GUI_NUMBER);
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

    public boolean IsValidItem(ItemStack stack) {

	GearItemData gear = Gear.Load(stack);

	if (gear != null) {
	    return true;

	}

	SpellItemData spell = Spell.Load(stack);

	if (spell != null) {
	    return true;

	}

	return false;
    }

    @Override
    public int GuiNumber() {
	return GUI_NUMBER;
    }

}
