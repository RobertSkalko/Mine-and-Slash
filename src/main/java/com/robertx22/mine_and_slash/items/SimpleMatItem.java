package com.robertx22.mine_and_slash.items;

import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;

public class SimpleMatItem extends Item {

    public static Item INFUSED_IRON = new SimpleMatItem().setRegistryName(Ref.MODID, "mat/infused_iron");
    public static Item CRYSTALLIZED_ESSENCE = new SimpleMatItem().setRegistryName(Ref.MODID, "mat/crystallized_essence");
    public static Item GOLDEN_ORB = new SimpleMatItem().setRegistryName(Ref.MODID, "mat/golden_orb");
    public static Item MYTHIC_ESSENCE = new SimpleMatItem().setRegistryName(Ref.MODID, "mat/mythic_essence");

    public SimpleMatItem() {
        super(new Item.Properties().group(CreativeTabs.MyModTab)
            .maxStackSize(64));
    }
}

