package com.robertx22.mine_and_slash.database.requirements;

import com.robertx22.mine_and_slash.database.unique_items.IUnique;

import java.util.ArrayList;
import java.util.List;

public class ExactUniquesRequierement extends UniqueItemRequirement {

    List<String> uniquesGUIDS = new ArrayList<>();

    public ExactUniquesRequierement(IUnique unique) {
        uniquesGUIDS.add(unique.GUID());
    }

    public ExactUniquesRequierement(List<IUnique> uniques) {
        for (IUnique uniq : uniques) {
            uniquesGUIDS.add(uniq.GUID());
        }
    }

    @Override
    public boolean meetsRequierment(GearRequestedFor requested) {

        Boolean prev = super.meetsRequierment(requested);

        if (prev == false) {
            return false;
        }

        if (uniquesGUIDS.contains(requested.gearData.uniqueStats.getUniqueItem()
                .GUID()) == false) {
            return false;
        }

        return true;

    }
}


