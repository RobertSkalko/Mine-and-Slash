package com.robertx22.mine_and_slash.config.compatible_items;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class GenCompItemJsons {

    public static ConfigItems generate() {

        ConfigItems items = new ConfigItems();

        items.map.clear();

        try {
            for (IUnique uniq : SlashRegistry.UniqueGears()
                .getAll()
                .values()) {
                ConfigItem item = new ConfigItem().setAlwaysUnique()
                    .setUniqueId(uniq)
                    .setSalvagable(true)
                    .setType(uniq.getGearSlot());
                Item theitem = (Item) uniq;
                item.dropsAsLoot = false;

                items.add(theitem.getRegistryName()
                    .toString(), item);
            }

            for (GearItemSlot slot : SlashRegistry.GearTypes()
                .getAll()
                .values())
                for (int i = 0; i < 5; i++) {
                    Item item = slot.getItemForRarity(i);

                    if (item == Items.AIR || item.getRegistryName() == null) {
                        continue;
                    }

                    if (item.getRegistryName()
                        .getNamespace()
                        .equals(Ref.MODID)) {

                        ConfigItem config = new ConfigItem().setGenerationWeights(1000, 200, 0)
                            .setMaxRarity(i)
                            .setMinRarity(i)
                            .setSalvagable(true)
                            .setType(slot);
                        config.dropsAsLoot = false;

                        items.add(item.getRegistryName()
                            .toString(), config);
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

}
