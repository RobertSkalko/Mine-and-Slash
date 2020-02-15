package com.robertx22.mine_and_slash.error_checks;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.requirements.bases.GearRequestedFor;
import com.robertx22.mine_and_slash.db_lists.initializers.Prefixes;
import com.robertx22.mine_and_slash.db_lists.initializers.Suffixes;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.error_checks.base.IErrorCheck;

public class AllGearsHavePossibleAffixCheck implements IErrorCheck {

    public void check() {

        for (GearItemSlot slot : SlashRegistry.GearTypes().getAll().values()) {

            Prefix prefix = Prefixes.INSTANCE.random(new GearRequestedFor(slot));
            Suffix suffix = Suffixes.INSTANCE.random(new GearRequestedFor(slot));

            if (prefix == null) {
                throw new RuntimeException(slot.GUID() + " has no possible prefix!");
            }
            if (suffix == null) {
                throw new RuntimeException(slot.GUID() + " has no possible suffix!");
            }

        }

    }

}
