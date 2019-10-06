package com.robertx22.mine_and_slash.new_content_test.talent_tree.csv_parser;

import java.util.ArrayList;
import java.util.List;

public class TalentGrid {

    List<List<String>> grid = new ArrayList<>();

    public String get(int x, int y) {
        return grid.get(x).get(y);
    }

    public TalentGrid(String str) {

        int x = 0;
        for (String line : str.split("\n")) {
            int y = 0;
            for (String s : line.split(",")) {

                if (grid.size() <= x) {
                    grid.add(new ArrayList<>());
                }

                grid.get(x).add(s);

                y++;

            }

            x++;
        }
    }
}
