package com.robertx22.mine_and_slash.saveclasses.talents;

import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryContainer;
import info.loenwind.autosave.annotations.Storable;

@Storable
public class PlayerTalentsData extends BasePerksData<Perk> {

    @Override
    public SlashRegistryContainer getRegistryContainer() {
        return SlashRegistry.Perks();
    }

}
