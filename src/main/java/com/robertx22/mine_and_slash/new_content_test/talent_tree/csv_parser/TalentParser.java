package com.robertx22.mine_and_slash.new_content_test.talent_tree.csv_parser;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.DirUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class TalentParser {

    public static void parse() {

        try {
            File file = new File(DirUtils.talentsCSV());

            String str = FileUtils.readFileToString(file, "utf-8");

            TalentGrid grid = new TalentGrid(str);

            grid.createPerks();

            grid.createConnections();

            System.out.println("didnt crahs");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
