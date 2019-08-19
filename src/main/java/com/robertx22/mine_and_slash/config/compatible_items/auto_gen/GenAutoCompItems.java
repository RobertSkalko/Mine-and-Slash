package com.robertx22.mine_and_slash.config.compatible_items.auto_gen;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GenAutoCompItems {

    public static class AutoCompItem {

        public AutoCompItem(Item item, GearItemSlot slot) {
            this.item = item;
            this.slot = slot;

        }

        public GearItemSlot slot;
        public Item item;

        public EquipmentSlotType getSlot() {

            ArmorItem armor = (ArmorItem) item;
            return armor.getEquipmentSlot();

        }
    }

    public static HashMap<String, List<AutoCompItem>> getMap() {

        HashMap<String, List<AutoCompItem>> map = new HashMap<>();
        ForgeRegistries.ITEMS.getValues()
                .stream()
                .filter(x -> x.getRegistryName() != null)
                .forEach(x -> map.put(x.getRegistryName()
                        .getNamespace(), new ArrayList<AutoCompItem>()));

        for (Item item : ForgeRegistries.ITEMS) {

            try {
                if (!item.getRegistryName().getNamespace().equals(Ref.MODID)) {

                    for (GearItemSlot slot : SlashRegistry.GearTypes().getList()) {

                        if (slot.isGearOfThisType(item)) {

                            map.get(item.getRegistryName().getNamespace())
                                    .add(new AutoCompItem(item, slot));

                        }

                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return map;
    }
}