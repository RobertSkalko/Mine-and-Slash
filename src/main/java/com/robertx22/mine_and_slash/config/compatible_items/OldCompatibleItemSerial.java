package com.robertx22.mine_and_slash.config.compatible_items;

import com.google.gson.Gson;
import com.robertx22.mine_and_slash.config.base.ISerializedConfig;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ConfigRegister;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OldCompatibleItemSerial implements ISerializedConfig<OldConfigItems> {

    public static OldCompatibleItemSerial INSTANCE = new OldCompatibleItemSerial();

    @Override
    public String fileName() {
        return "CompatibleItems.txt";
    }

    @Override
    public String folder() {
        return SerializationUtils.CONFIG_PATH + "compatible_items/";
    }

    @Override
    public List<String> getAllJsons() {

        List<String> list = new ArrayList<>();
        for (File file : Objects.requireNonNull(new File(folder()).listFiles())) {
            try {
                String json = getJsonFromFile(file.getPath());
                if (json != null) {
                    list.add(json);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;

    }

    @Override
    public void loadOnServer() {

        List<String> jsons = getAllJsons();

        getAllJsons().forEach(x -> {
            loadFromString(x).registerAll();
        });

    }

    @Override
    public ConfigRegister.Config getConfigType() {
        return null;
    }

    @Override
    public OldConfigItems getDefaultObject() {
        return new OldConfigItems();
    }

    @Override
    public OldConfigItems loadFromString(String string) {
        return new Gson().fromJson(string, OldConfigItems.class);
    }

    // needs to be done after unique items are actually registered.
    public void generateConfigTutorials() {
        genListOfItemTypes();
    }

    private void genListOfItemTypes() {

        List<String> list = new ArrayList();

        for (GearItemSlot item : SlashRegistry.GearTypes()
            .getAll()
            .values()) {
            list.add(item.GUID());
        }

        String text = "// THIS FILE IS A TUTORIAL FILE, IT LETS YOU KNOW WHAT ITEM TYPES THERE ARE\n" + String.join(
            "\n", list);

        SerializationUtils.makeFileAndDirAndWrite("tutorials", "GearTypeGUIDS-TUTORIAL.txt", text);

    }

}
