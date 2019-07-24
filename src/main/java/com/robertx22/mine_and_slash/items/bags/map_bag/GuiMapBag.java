package com.robertx22.mine_and_slash.items.bags.map_bag;

import com.robertx22.mine_and_slash.items.bags.BaseBagGui;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GuiMapBag extends BaseBagGui<ContainerMapBag> {

    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/loot_bag.png");

    public GuiMapBag(ContainerMapBag bag, PlayerInventory inv, ITextComponent comp) {
        super(inv, bag);

        this.xSize = BaseBagGui.bagXSize;
        this.ySize = BaseBagGui.bagYSize;
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