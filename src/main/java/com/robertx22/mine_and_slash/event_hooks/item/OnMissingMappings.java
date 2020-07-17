package com.robertx22.mine_and_slash.event_hooks.item;

import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;

public class OnMissingMappings {

    @SubscribeEvent
    public static void onMissingMappings(RegistryEvent.MissingMappings<Item> event) {

        HashMap<String, Item> map = new HashMap<>();

        System.out.println("Remapping Items that have changed IDs");

        for (Item item : ForgeRegistries.ITEMS) {
            if (item instanceof IRenamed) {
                IRenamed renamed = ((IRenamed) item);
                renamed.oldNames().forEach(x -> map.put(x, item));
            }
        }

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

    }

}
