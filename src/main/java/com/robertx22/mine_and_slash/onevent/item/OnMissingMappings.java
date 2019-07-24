package com.robertx22.mine_and_slash.onevent.item;

import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;

public class OnMissingMappings {

    static final HashMap<String, Item> map = new HashMap<>();

    @SubscribeEvent
    public static void onMissingMappings(RegistryEvent.MissingMappings<Item> event) {

        System.out.println("Remapping Items that have changed IDs");

        generateHashMap();

        for (RegistryEvent.MissingMappings.Mapping<Item> mapping : event.getAllMappings()) {

            if (mapping != null) {
                ResourceLocation res = mapping.key;
                if (res != null) {
                    String key = res.toString();
                    if (map.containsKey(key)) {
                        mapping.remap(map.get(key));
                    }
                }
            }

        }

        map.clear();

    }

    private static void generateHashMap() {

        for (Item item : ForgeRegistries.ITEMS) {
            if (item instanceof IRenamed) {
                IRenamed renamed = ((IRenamed) item);
                renamed.oldNames().forEach(x -> map.put(x, item));
            }
        }

    }

}
