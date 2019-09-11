package com.robertx22.mine_and_slash.database;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public interface IGUID {

    public String GUID();

    public default String formattedGUID() {
        return formatString(GUID());

    }

    public default String formatString(String str) {
        return str.toLowerCase()
                .replaceAll(" ", "_")
                .replaceAll("/", ".")
                .replaceAll(":", ".");
    }

    default Item getFromForgeRegistry() {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(Ref.MODID, GUID()));
    }

}
