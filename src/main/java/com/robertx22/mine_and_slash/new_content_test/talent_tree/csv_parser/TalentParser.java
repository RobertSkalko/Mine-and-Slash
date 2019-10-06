package com.robertx22.mine_and_slash.new_content_test.talent_tree.csv_parser;

import com.robertx22.mine_and_slash.new_content_test.talent_tree.Perk;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkBuilder;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.data.PerkEffects;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.DirUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TalentParser {

    private static boolean isTalent(String str) {
        return str.length() > 1;
    }

    private static boolean isConnector(String str) {
        return str.equals("O") || str.equals("o");
    }

    private static String getID(int x, int y) {
        return x + "_" + y;
    }

    public static void parse() {

        try {
            File file = new File(DirUtils.talentsCSV());

            String str = FileUtils.readFileToString(file, "utf-8");

            TalentGrid grid = new TalentGrid(str);

            List<List<String>> monster = new ArrayList<>();

            int x = 0;
            for (String line : str.split("\n")) {
                int y = 0;
                for (String s : line.split(",")) {

                    if (monster.size() <= x) {
                        monster.add(new ArrayList<>());
                    }

                    monster.get(x).add(s);

                    if (isTalent(s)) {
                        Perk perk = PerkBuilder.create(getID(x, y))
                                .pos(x, y)
                                .effect(PerkEffects.MAGIC_SHIELD_PERCENT.small())
                                .connections()
                                .build();

                    }

                    y++;

                }

                x++;
            }

            System.out.println("didnt crahs");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<String> getConnections(List<List<String>> grid) {

        int x = 0;
        for (String line : str.split("\n")) {
            int y = 0;
            for (String s : line.split(",")) {

            }
        }

        return null;
    }

    private List<String> getConnections(int x, int y) {

        return null;
    }

}
