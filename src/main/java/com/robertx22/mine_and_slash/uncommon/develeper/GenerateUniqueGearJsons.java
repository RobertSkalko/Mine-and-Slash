package com.robertx22.mine_and_slash.uncommon.develeper;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Bow;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.DirUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;
import net.minecraft.item.Item;

public class GenerateUniqueGearJsons {

    static String JSON = "{\n    \"parent\": \"item/generated\",\n    \"textures\": {\n        \"layer0\": " +
            "\"REPLACE\"\n    }\n}\n";

    public static void gen() {

        SlashRegistry.UniqueGears().getList().forEach(x -> {
            if (x.getGearSlot() != Bow.INSTANCE) {
                String path = DirUtils.modelsPath() + "item/" + x.getGeneratedResourceFolderPath();

                Item item = (Item) x;
                String texLoc = item.getRegistryName().getNamespace() + ":" + "items/" + item.getRegistryName()
                        .getPath();

                String json = JSON.replace("REPLACE", texLoc);

                if (x.getGearSlot().slotType() == GearItemSlot.GearSlotType.Weapon) {
                    json = json.replaceAll("generated", "handheld");
                }

                SerializationUtils.makeFileAndDirAndWrite(path, x.GUID().toLowerCase() + ".json", json, true);
            }
        });

    }
}
