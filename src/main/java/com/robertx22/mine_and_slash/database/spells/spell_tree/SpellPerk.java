package com.robertx22.mine_and_slash.database.spells.spell_tree;

import com.robertx22.mine_and_slash.database.talent_tree.BasePerk;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.uncommon.capability.bases.IPerkCap;

public class SpellPerk extends BasePerk<SpellPerk, IPerkCap> {

    public SpellPerk(String guid) {
        super(guid);
    }

    public void render(int x, int y) {
        this.effect.render(x, y);
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.SPELL_PERK;
    }

}
