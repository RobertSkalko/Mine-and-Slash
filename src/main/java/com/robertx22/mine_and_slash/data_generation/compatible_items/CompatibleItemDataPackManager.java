package com.robertx22.mine_and_slash.data_generation.compatible_items;

import com.robertx22.mine_and_slash.data_generation.BaseDataPackManager;
import com.robertx22.mine_and_slash.database.compatible_item.CompatibleItem;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.SlashDataProvider;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class CompatibleItemDataPackManager extends BaseDataPackManager<CompatibleItem> {
    public static String ID = "compatible_items";

    public CompatibleItemDataPackManager() {
        super(SlashRegistryType.COMPATIBLE_ITEM, ID, x -> CompatibleItem.EMPTY.fromJson(x));
    }

    @Override
    public SlashDataProvider getDataPackCreator(DataGenerator gen) {
        return new CompatibleItemProvider(gen, getList(), ID);
    }

    public List<CompatibleItem> getList() {
        List<CompatibleItem> items = new ArrayList<>();

        try {
            for (IUnique uniq : SlashRegistry.UniqueGears()
                .getSerializable()
            ) {

                CompatibleItem c = new CompatibleItem();

                c.normal_item_weight = 0;
                c.unique_item_weight = 100;
                c.unique_id = uniq.GUID();
                c.can_be_salvaged = true;
                c.item_type = uniq.getGearSlot()
                    .GUID();

                Item theitem = (Item) uniq.getUniqueItem();
                c.item_id = theitem.getRegistryName()
                    .toString();
                c.add_to_loot_drops = false;

                c.guid = theitem.getRegistryName()
                    .toString();

                items.add(c);
            }

            for (GearItemSlot slot : SlashRegistry.GearTypes()
                .getAll()
                .values()) {
                Item item = slot.getItem();

                if (item == Items.AIR || item.getRegistryName() == null) {
                    continue;
                }

                if (item.getRegistryName()
                    .getNamespace()
                    .equals(Ref.MODID)) {

                    CompatibleItem c = new CompatibleItem();
                    c.normal_item_weight = 100;
                    c.can_be_salvaged = true;
                    c.item_type = slot.GUID();
                    c.add_to_loot_drops = false;

                    String id = item.getRegistryName()
                        .toString();

                    c.guid = id;
                    c.item_id = id;

                    items.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

}
