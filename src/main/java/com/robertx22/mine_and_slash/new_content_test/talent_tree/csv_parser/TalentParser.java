package com.robertx22.mine_and_slash.new_content_test.talent_tree.csv_parser;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;

public class TalentParser {

    public static void parse() {

        try {
            String path = SerializationUtils.CONFIG_PATH + "talents.csv";

            InputStream input = TalentParser.class.getClassLoader()
                    .getResourceAsStream("assets\\mmorpg\\talents.csv");

            String s = IOUtils.toString(input, "utf-8");

            File file = new File(path);

            if (file.exists()) {
                s = FileUtils.readFileToString(file, "utf-8");
            } else {
                FileUtils.writeStringToFile(file, s, "utf-8");
            }

            TalentGrid grid = new TalentGrid(s);

            grid.createPerks();

            grid.createConnections();

            System.out.println("Registered all" + SlashRegistry.Perks()
                    .getSize() + " perks to the talent tree!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
