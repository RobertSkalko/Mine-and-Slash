package com.robertx22.items.bags.currency_bag;

import com.robertx22.items.bags.BaseBagItem;
import com.robertx22.items.currency.CurrencyItem;
import com.robertx22.items.currency.ICurrencyItemEffect;
import com.robertx22.items.ores.ItemOre;
import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.gui.GuiHandler;
import com.robertx22.mmorpg.gui.GuiHandlerRegistry;
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
public class ItemCurrencyBag extends BaseBagItem {

    public static final int GUI_NUMBER = 356514;

    @GameRegistry.ObjectHolder(Ref.MODID + ":currency_bag")
    public static final Item ITEM = null;

    private static final String TAG_ITEMS = "InvItems";

    public ItemCurrencyBag() {
	super("currency_bag");

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemCurrencyBag());

	NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, GuiHandlerRegistry.getInstance());
	GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandler(), GUI_NUMBER);
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

    public boolean IsValidItem(ItemStack stack) {

	return stack.getItem() instanceof ICurrencyItemEffect || stack.getItem() instanceof CurrencyItem
		|| stack.getItem() instanceof ItemOre;
    }

    @Override
    public int GuiNumber() {
	return GUI_NUMBER;
    }

}
