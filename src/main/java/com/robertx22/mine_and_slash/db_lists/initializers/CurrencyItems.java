package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.items.currency.*;
import com.robertx22.mine_and_slash.database.items.currency.infusions.AttackInfusionItem;
import com.robertx22.mine_and_slash.database.items.currency.infusions.DefenseInfusionItem;
import com.robertx22.mine_and_slash.database.items.currency.infusions.ResourceInfusionItem;
import com.robertx22.mine_and_slash.database.items.currency.infusions.upgrade.NormalUpgradeInfusion;
import com.robertx22.mine_and_slash.database.items.currency.infusions.upgrade.SuperiorUpgradeInfusion;
import com.robertx22.mine_and_slash.database.items.currency.infusions.upgrade.WondrousUpgradeInfusion;
import com.robertx22.mine_and_slash.database.items.currency.map.ItemAddGroupMap;
import com.robertx22.mine_and_slash.database.items.currency.map.ItemAddMapLevel;
import com.robertx22.mine_and_slash.database.items.currency.map.ItemAddMapTier;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ItemRegister;

public class CurrencyItems implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        add(new ItemAddMapTier());
        add(new ItemAddMapLevel());

        add(new ItemCheapGearLvl());
        add(new ItemRerollPrimaryStats());
        add(new ItemAddMajorArcana());
        add(new ItemAddSet());
        add(new ItemAddGroupMap());
        add(new ItemClearRunes());
        add(new ItemRerollSet());
        add(new CreateNewUnique());
        add(new ItemAddPrefix());
        add(new ItemAddSecondaryStat());
        add(new ItemAddSuffix());
        add(new ItemChaosOrb());
        add(new ItemLevelUpGear());
        add(new ItemNumberReroll());
        add(new ItemOrbOfTransmutation());
        add(new ItemRandomizePrefix());
        add(new ItemRandomizeSuffix());
        add(new ItemStoneOfHope());
        add(new RerollPrefixNumbers());
        add(new RerollSuffixNumbers());
        add(new RerollUniqueNumbers());

        add(new NormalUpgradeInfusion());
        add(new SuperiorUpgradeInfusion());
        add(new WondrousUpgradeInfusion());
        add(new AttackInfusionItem());
        add(new DefenseInfusionItem());
        add(new ResourceInfusionItem());
    }

    private void add(CurrencyItem item) {
        item.registerToSlashRegistry();
        ItemRegister.shcheduleToRegister(item);

    }
}
