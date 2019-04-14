package com.robertx22.customitems.bags.currency_bag;

import com.robertx22.customitems.bags.BaseBagGui;
import com.robertx22.mmorpg.Ref;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCurrencyBag extends BaseBagGui {

    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/loot_bag.png");

    public GuiCurrencyBag(InventoryPlayer playerInv, InventoryCurrencyBag baginv) {
	super(new ContainerCurrencyBag(playerInv, baginv));
    }

    @Override
    public ResourceLocation texture() {
	return texture;
    }

    @Override
    public int rows() {
	return 6;
    }

    @Override
    public String name() {
	return "Currency Bag";
    }

}