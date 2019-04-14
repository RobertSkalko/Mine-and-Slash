package com.robertx22.customitems.bags.map_bag;

import com.robertx22.customitems.bags.BaseBagGui;
import com.robertx22.mmorpg.Ref;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiMapBag extends BaseBagGui {

    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/loot_bag.png");

    public GuiMapBag(InventoryPlayer playerInv, InventoryMapBag baginv) {
	super(new ContainerMapBag(playerInv, baginv));
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
	return "Map Bag";
    }

}