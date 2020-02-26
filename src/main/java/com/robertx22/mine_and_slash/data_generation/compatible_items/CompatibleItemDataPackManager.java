package com.robertx22.mine_and_slash.data_generation.compatible_items;

import com.robertx22.mine_and_slash.config.compatible_items.OldConfigItem;
import com.robertx22.mine_and_slash.data_generation.BaseDataPackManager;
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
                OldConfigItem old = new OldConfigItem().setAlwaysUnique()
                    .setUniqueId(uniq)
                    .setSalvagable(true)
                    .setType(uniq.getGearSlot());

                Item theitem = (Item) uniq.getUniqueItem();
                old.registryName = theitem.getRegistryName()
                    .toString();
                old.dropsAsLoot = false;

                CompatibleItem neww = old.convertToNewFormat();

                neww.guid = theitem.getRegistryName()
                    .toString();

                items.add(neww);
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

                        OldConfigItem config = new OldConfigItem().setGenerationWeights(1000, 200, 0)
                            .setMaxRarity(i)
                            .setMinRarity(i)
                            .setSalvagable(true)
                            .setType(slot);
                        config.dropsAsLoot = false;

                        String id = item.getRegistryName()
                            .toString();

                        CompatibleItem neww = config.convertToNewFormat();
                        neww.guid = id;
                        neww.item_id = id;

                        items.add(neww);
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

}
