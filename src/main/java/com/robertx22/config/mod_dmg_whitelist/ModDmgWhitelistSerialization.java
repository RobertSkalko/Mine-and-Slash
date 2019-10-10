package com.robertx22.config.mod_dmg_whitelist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.SerializationUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.robertx22.config.non_mine_items.ConfigItems;
import com.robertx22.mmorpg.Ref;

import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModDmgWhitelistSerialization {
	
	 private static String config_path = "";

	  public static void generateConfig(FMLPreInitializationEvent event) {

	    config_path = event.getModConfigurationDirectory().getAbsolutePath() + "/" + Ref.MODID + "/";

	    new File(config_path).mkdirs();

	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    String json = gson.toJson(new ConfigItems());

	    makeFileAndDirAndWrite(getItemsConfigPath(), json, false);

	  }

	  public static String getItemsConfigPath() {
	    return config_path + "ModDamageWhitelist.txt";
	  }
	  
	  public static void loadConfig(FMLPreInitializationEvent event) {

		  JsonReader reader;
	        try {
	            reader = new JsonReader(new FileReader(getItemsConfigPath()));

	            ModDmgWhitelistContainer.INSTANCE = new Gson().fromJson(reader, ModDmgWhitelistContainer.class);

	            System.out.println("Mod Damage whitelists added : " + ModDmgWhitelistContainer.INSTANCE.modList
	                    .size());
	        } catch (FileNotFoundException e) {

	            e.printStackTrace();
	        }
		  }

		  private static void makeFileAndDirAndWrite(String path, String text, boolean overwriteAnyway) {

		    try {
		      if (new File(path).exists() == false || overwriteAnyway) {

		        new File(path).createNewFile();
		        FileWriter fileWriter;
		        fileWriter = new FileWriter(path);
		        fileWriter.write(text);
		        fileWriter.close();
		      }
		    } catch (IOException e) {
		      e.printStackTrace();
		    }

		  }

}
