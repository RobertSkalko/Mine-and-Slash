package com.robertx22.mine_and_slash.saveclasses.talents;

import com.robertx22.mine_and_slash.database.spells.spell_tree.SpellPerk;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryContainer;

public class SpellPerksData extends BasePerksData<SpellPerk> {

    @Override
    public SlashRegistryContainer getRegistryContainer() {
        return SlashRegistry.SpellPerks();
    }
}
