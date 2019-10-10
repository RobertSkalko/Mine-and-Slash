package com.robertx22.mine_and_slash.database.talent_tree.gui;

import com.robertx22.mine_and_slash.database.talent_tree.PerkConnection;

public class PerkConnectionRender {

    PerkButton perk1, perk2;
    PerkConnection connection;

    public PerkConnectionRender(PerkButton perk1, PerkButton perk2,
                                PerkConnection connection) {
        this.perk1 = perk1;
        this.perk2 = perk2;
        this.connection = connection;
    }
}
