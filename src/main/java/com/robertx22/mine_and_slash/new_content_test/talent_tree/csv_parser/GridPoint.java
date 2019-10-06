package com.robertx22.mine_and_slash.new_content_test.talent_tree.csv_parser;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.Perk;

public class GridPoint {

    public int x;
    public int y;
    public String str;

    public GridPoint(int x, int y, String str) {
        this.x = x;
        this.y = y;
        this.str = str;
    }

    public Perk getPerk() {
        return SlashRegistry.Talents().get(this.getID());
    }

    public boolean isTalent() {
        return str.length() > 1;
    }

    public boolean isConnector() {
        return str.equals("O") || str.equals("o");
    }

    public String getID() {
        return x + "_" + y;
    }

}
