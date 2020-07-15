package com.robertx22.mine_and_slash.error_checks;

import com.robertx22.mine_and_slash.database.affixes.Affix;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseGearType;
import com.robertx22.mine_and_slash.database.requirements.bases.GearRequestedFor;
import com.robertx22.mine_and_slash.db_lists.initializers.Prefixes;
import com.robertx22.mine_and_slash.db_lists.initializers.Suffixes;
import com.robertx22.mine_and_slash.error_checks.base.IErrorCheck;
import com.robertx22.mine_and_slash.registry.SlashRegistry;

public class AllGearsHavePossibleAffixCheck implements IErrorCheck {

    public void check() {

        for (BaseGearType slot : SlashRegistry.GearTypes()
            .getAll()
            .values()) {

            Affix prefix = Prefixes.INSTANCE.random(new GearRequestedFor(slot));
            Affix suffix = Suffixes.INSTANCE.random(new GearRequestedFor(slot));

            if (prefix == null) {
                throw new RuntimeException(slot.GUID() + " has no possible prefix!");
            }
            if (suffix == null) {
                throw new RuntimeException(slot.GUID() + " has no possible suffix!");
            }

        }

    }

}
