package com.robertx22.mine_and_slash.commands.bases;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;

import java.util.ArrayList;
import java.util.List;

public class GearTypeSuggestions extends CommandSuggestions {

    @Override
    public List<String> suggestions() {

        List<String> list = new ArrayList();
        for (GearItemSlot slot : SlashRegistry.GearTypes().getAll().values()) {
            list.add(slot.GUID());
        }
        list.add("random");

        return list;
    }

}

