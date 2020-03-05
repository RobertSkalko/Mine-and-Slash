package com.robertx22.mine_and_slash.registry;

import com.robertx22.mine_and_slash.onevent.data_gen.ISerializedRegistryEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SlashRegistryPackets {

    private static HashMap<SlashRegistryType, List<ISerializedRegistryEntry>> map = new HashMap<>();

    public static boolean allPacketsRecieved = false;

    public static void add(ISerializedRegistryEntry entry) {

        if (!map.containsKey(entry.getSlashRegistryType())) {
            map.put(entry.getSlashRegistryType(), new ArrayList<>());
        }

        List<ISerializedRegistryEntry> list = map.get(entry.getSlashRegistryType());
        list.add(entry);

    }

    public static void registerAll() {

        map.entrySet()
            .forEach(entry -> {

                SlashRegistryType type = entry.getKey();

                SlashRegistryContainer reg = SlashRegistry.getRegistry(type);

                reg.unregisterAllEntriesFromDatapacks();

                List<ISerializedRegistryEntry> list = entry.getValue();

                if (list
                    .isEmpty()) {
                    throw new RuntimeException("Registry list sent from server is empty!");
                }

                System.out.println(
                    "Starting to register " + type.name() + " from server packet for Mine and Slash.");

                list.forEach(x -> x.registerToSlashRegistry());

                if (reg
                    .isEmpty()) {
                    throw new RuntimeException("Mine and Slash Registry of type " + reg.getType() + " is EMPTY after datapack loading!");
                } else {
                    System.out.println("Loading registry on client succeeded with: " + reg.getSize() + " entries.");
                }
            });

        map.clear();
    }

}
