package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.tiers.impl.*;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

public class Tiers implements ISlashRegistryInit {

    @Override
    public void registerAll() {
        new TierZero().addToSerializables();
        new TierOne().addToSerializables();
        new TierTwo().addToSerializables();
        new TierThree().addToSerializables();
        new TierFour().addToSerializables();
        new TierFive().addToSerializables();
    }
}
