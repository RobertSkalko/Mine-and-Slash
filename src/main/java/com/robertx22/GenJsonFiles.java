package com.robertx22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.robertx22.database.lists.Rarities;
import com.robertx22.gearitem.ItemRarity;

public class GenJsonFiles {

	public static void Gen() throws IOException {

		List<String> names = Arrays.asList("boots", "helmet", "chest", "pants", "ring", "sword");

		List<String> list = Files.readAllLines(Paths.get("C:\\Users\\User\\Desktop\\jsonfile.json"));

		String json = "";
		for (String str : list) {
			json += str + "\n";
		}

		for (String name : names) {

			for (ItemRarity rarity : Rarities.Items) {

				String finalname = name + rarity.Rank();

				byte[] file = json.replaceAll("NAME", finalname).getBytes();

				Path path = Paths.get("C:\\Users\\User\\Desktop\\Jsons\\" + finalname + ".json");

				// Files.createDirectory(path);

				Files.write(path, file);

			}

		}

	}
}
