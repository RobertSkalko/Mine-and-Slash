package com.robertx22.mine_and_slash.database.spells.spell_tree;

import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;

public class SpellTreeParser {
    public static void parse() {

        try {
            String path = SerializationUtils.CONFIG_PATH + "spell_tree.csv";

            InputStream input = SpellTreeParser.class.getClassLoader()
                    .getResourceAsStream("assets\\mmorpg\\spell_tree.csv");

            String s = IOUtils.toString(input, "utf-8");

            File file = new File(path);

            SpellPerkGrid grid = new SpellPerkGrid(s);

            grid.createAndRegisterAll();

            grid.createConnections();

            System.out.println("Registered all" + SlashRegistry.SpellPerks().getSize() + " perks to the spell tree!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
