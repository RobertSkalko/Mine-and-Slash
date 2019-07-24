package com.robertx22.mine_and_slash.items.bags.map_bag;

import com.robertx22.mine_and_slash.items.bags.BaseBagItem;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.item_filters.bases.ItemFilterGroup;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ObjectHolder;

public class ItemMapBag extends BaseBagItem {

    public static final String ID = Ref.MODID + ":map_bag";

    @ObjectHolder(ID)
    public static final Item ITEM = null;

    public ItemMapBag() {
        super(ID);

    }

    @Override
    public INamedContainerProvider getNamedContainer(ItemStack stack) {
        return new InteractMapBag(stack);
    }

    @Override
    public ItemFilterGroup filterGroup() {
        return ItemFilterGroup.ANY_MAP;
    }

}
