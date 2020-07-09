package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Storable
public class AllocatedAbilitiesData {

    @Store
    public HashMap<String, AbilityData> map = new HashMap<>();

    public HashMap<String, AbilityData> getAbilityMap() {
        return map;
    }

    public List<BaseSpell> getAllocatedSpells() {
        List<BaseSpell> list = new ArrayList<>();

        this.map.values()
            .forEach(x -> {
                if (x.isValid() && x.getAbility() != null) {
                    list.add((BaseSpell) x.getAbility());
                }
            });

        return list;
    }

    public boolean isAllocated(IAbility ability) {
        return isAllocated(ability.GUID());
    }

    public boolean isAllocated(String id) {
        return map.containsKey(id) && map.get(id)
            .getAbility() != null;
    }

    private static AbilityData EMPTY_ABILITY = new AbilityData();

    public void clean() {
        new HashMap<String, AbilityData>(map).entrySet()
            .forEach(x -> {
                if (!x.getValue()
                    .isValid()) {
                    map.remove(x.getKey());
                }
            });

    }
}
