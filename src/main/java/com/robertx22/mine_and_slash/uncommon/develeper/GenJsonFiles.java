package com.robertx22.mine_and_slash.uncommon.develeper;

import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class GenJsonFiles {

    public static void Gen() throws IOException {

        //List<String> names = Arrays.asList("boots", "helmet", "chest", "pants", "ring", "sword");

        List<String> names = Arrays.asList("shield");

        List<String> list = Files.readAllLines(Paths.get("C:\\Users\\User\\Desktop\\jsonfile.json"));

        String json = "";
        for (String str : list) {
            json += str + "\n";
        }

        for (String name : names) {

            for (ItemRarity rarity : Rarities.Items.rarities()) {

                String finalname = name + rarity.Rank();

                byte[] file = json.replaceAll("NAME", finalname).getBytes();

                Path path = Paths.get("C:\\Users\\User\\Desktop\\Jsons\\" + finalname + ".json");

                Files.createDirectory(Paths.get("C:\\Users\\User\\Desktop\\Jsons\\"));

                new File(path.toString()).createNewFile();

                Files.write(path, file);

            }

        }

    }
}
