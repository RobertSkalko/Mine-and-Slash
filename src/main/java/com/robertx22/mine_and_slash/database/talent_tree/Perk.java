package com.robertx22.mine_and_slash.database.talent_tree;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerTalentsCap;

public class Perk extends BasePerk<Perk, PlayerTalentsCap.IPlayerTalentsData> {

    public Perk(String guid) {
        super(guid);
    }

    public void render(int x, int y) {
        this.effect.render(x, y);
    }

    public Perk setAsStart() {
        this.isStart = true;
        return this;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.PERK;
    }

}
