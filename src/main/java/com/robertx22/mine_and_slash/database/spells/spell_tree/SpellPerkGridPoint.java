package com.robertx22.mine_and_slash.database.spells.spell_tree;

import com.robertx22.mine_and_slash.database.talent_tree.csv_parser.GridPoint;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;

public class SpellPerkGridPoint<T extends SpellPerk> extends GridPoint {

    public SpellPerkGridPoint(int x, int y, String str) {
        super(x, y, str);
    }

    @Override
    public T getPerk() {
        // handle both caps and lowercase
        String id = getID();

        if (!SlashRegistry.SpellPerks().isRegistered(id)) {
            id = id.toLowerCase();
            if (!SlashRegistry.SpellPerks().isRegistered(id)) {
                id = id.toUpperCase();
            }
        }

        return (T) SlashRegistry.SpellPerks().get(id);
    }

}
