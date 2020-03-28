package com.robertx22.mine_and_slash.data_generation.wrappers;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;

public class ItemPerRarityHolder implements ISerializable<ItemPerRarityHolder> {

    public static ISerializable<ItemPerRarityHolder> SERIALIZER = new ItemPerRarityHolder();

    HashMap<Integer, String> map = new HashMap<>();

    HashMap<Integer, Item> itemMapForRegistration = new HashMap<>(); // literally just there so i can register my runes

    public HashMap<Integer, Item> getForRegistration() {
        return itemMapForRegistration;
    }

    public ItemPerRarityHolder(HashMap<Integer, Item> itemMap) {
        this.itemMapForRegistration = new HashMap<>(itemMap);
        itemMap.entrySet()
            .forEach(e -> map.put(e.getKey(), e.getValue()
                .getRegistryName()
                .toString()));

    }

    private ItemPerRarityHolder() {

    }

    public Item get(int rarity) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(map.get(rarity)));
    }

    @Override
    public JsonObject toJson() {

        JsonObject json = new JsonObject();
        map.entrySet()
            .forEach(x -> {
                json.addProperty(x.getKey() + "", x.getValue());
            });
        return json;
    }

    @Override
    public ItemPerRarityHolder fromJson(JsonObject json) {
        HashMap<Integer, Item> itemMap = new HashMap<>();

        for (RuneRarity rar : Rarities.Runes.getAllRarities()) {
            String id = rar.Rank() + "";
            if (json.has(id)) {

                String itemid = json.get(id)
                    .getAsString();
                Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemid));
                itemMap.put(rar.Rank(), item);
            }
        }

        return new ItemPerRarityHolder(itemMap);

    }
}
