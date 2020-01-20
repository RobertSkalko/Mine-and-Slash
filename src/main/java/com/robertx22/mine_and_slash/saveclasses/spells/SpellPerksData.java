package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.database.spells.spell_tree.SpellPerk;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryContainer;
import com.robertx22.mine_and_slash.saveclasses.talents.BasePerksData;
import info.loenwind.autosave.annotations.Storable;

@Storable
public class SpellPerksData extends BasePerksData<SpellPerk> {

    @Override

    public SlashRegistryContainer getRegistryContainer() {
        return SlashRegistry.SpellPerks();
    }
}
