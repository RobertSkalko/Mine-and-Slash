package com.robertx22.mmorpg.config.non_mine_items;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.mmorpg.Ref;
import com.robertx22.unique_items.IUnique;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Serialization {

    private static String config_path = "";

    public static void generateConfig(FMLPreInitializationEvent event) {

	config_path = event.getModConfigurationDirectory().getAbsolutePath() + "/" + Ref.MODID + "/";

	new File(config_path).mkdirs();

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	String json = gson.toJson(new ConfigItems());

	makeFileAndDirAndWrite(getItemsConfigPath(), json, false);

    }

    public static String getItemsConfigPath() {
	return config_path + "CompatibleItems.txt";
    }

    public static void loadConfig(FMLPostInitializationEvent event) {

	JsonReader reader;
	try {
	    reader = new JsonReader(new FileReader(getItemsConfigPath()));

	    ConfigItems.INSTANCE = new Gson().fromJson(reader, ConfigItems.class);

	    System.out.println("Items added to config: " + ConfigItems.INSTANCE.map.size());
	} catch (FileNotFoundException e) {

	    e.printStackTrace();
	}
    }

    public static void generateConfig(FMLPostInitializationEvent event) {
	genListOfUniqueItems();
	genListOfItemTypes();
    }

    private static void makeFileAndDirAndWrite(String path, String text, boolean overwriteAnyway) {

	try {
	    if (new File(path).exists() == false || overwriteAnyway) {

		new File(path).createNewFile();
		FileWriter fileWriter;
		fileWriter = new FileWriter(path);
		fileWriter.write(text);
		fileWriter.close();

		// System.out.println("Creating new CompatibleItems.txt");
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    private static void genListOfUniqueItems() {

	List<String> list = new ArrayList();

	for (Item item : IUnique.ITEMS.values()) {
	    IUnique uniq = (IUnique) item;
	    list.add(uniq.GUID());
	}

	String text = "// THIS FILE IS A TUTORIAL FILE, IT LETS YOU KNOW THE GUIDS/IDS OF ALL UNIQUE ITEMS\n"
		+ String.join("\n", list);

	makeFileAndDirAndWrite(config_path + "UniqueItemGUIDS-TUTORIAL.txt", text, true);

    }

    private static void genListOfItemTypes() {

	List<String> list = new ArrayList();

	for (GearItemSlot item : GearTypes.All.values()) {
	    list.add(item.GUID());
	}

	String text = "// THIS FILE IS A TUTORIAL FILE, IT LETS YOU KNOW WHAT ITEM TYPES THERE ARE\n"
		+ String.join("\n", list);

	makeFileAndDirAndWrite(config_path + "GearTypeGUIDS-TUTORIAL.txt", text, true);

    }
}
