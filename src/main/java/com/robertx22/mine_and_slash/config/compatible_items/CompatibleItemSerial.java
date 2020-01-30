package com.robertx22.mine_and_slash.config.compatible_items;

import com.google.gson.Gson;
import com.robertx22.mine_and_slash.config.base.ISerializedConfig;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ConfigRegister;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompatibleItemSerial implements ISerializedConfig<ConfigItems> {

    public static CompatibleItemSerial INSTANCE = new CompatibleItemSerial();

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
            list.add(getJsonFromFile(file.getPath()));
        }

        return list;

    }

    @Override
    public ConfigRegister.Config getConfigType() {
        return ConfigRegister.Config.COMPATIBLE_ITEM;
    }

    @Override
    public ConfigItems getDefaultObject() {
        return GenCompItemJsons.generate();
    }

    @Override
    public ConfigItems loadFromString(String string) {
        return new Gson().fromJson(string, ConfigItems.class);
    }

    // needs to be done after unique items are actually registered.
    public void generateConfigTutorials() {
        genListOfUniqueItems();
        genListOfItemTypes();
    }

    private void genListOfUniqueItems() {

        String text =
                "// THIS FILE IS A TUTORIAL FILE, IT LETS YOU KNOW THE GUIDS/IDS OF ALL UNIQUE ITEMS\n" + String.join(
                "\n", SlashRegistry.UniqueGears().getList().stream().map(x -> x.GUID()).collect(Collectors.toList()));

        SerializationUtils.makeFileAndDirAndWrite("tutorials", "UniqueItemGUIDS-TUTORIAL.txt", text);

    }

    private void genListOfItemTypes() {

        List<String> list = new ArrayList();

        for (GearItemSlot item : SlashRegistry.GearTypes().getAll().values()) {
            list.add(item.GUID());
        }

        String text = "// THIS FILE IS A TUTORIAL FILE, IT LETS YOU KNOW WHAT ITEM TYPES THERE ARE\n" + String.join(
                "\n", list);

        SerializationUtils.makeFileAndDirAndWrite("tutorials", "GearTypeGUIDS-TUTORIAL.txt", text);

    }

}
