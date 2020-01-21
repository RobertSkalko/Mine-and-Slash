package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.spells.spell_tree.SpellTreeParser;
import com.robertx22.mine_and_slash.database.spells.spell_tree.data.SpellPerkEffects;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;

public class SpellPerks implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        SpellTreeParser.parse();

        // all perks are made through parsing csv, this also makes it configurable for users! - if synced to client

        SlashRegistry.SpellPerks()
                .getList()
                .stream()
                .filter(x -> x.effect == SpellPerkEffects.START)
                .forEach(x -> x.isStart = true);

    }

}
