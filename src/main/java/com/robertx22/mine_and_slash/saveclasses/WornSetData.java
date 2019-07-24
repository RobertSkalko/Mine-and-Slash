package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.ArrayList;
import java.util.List;

@Storable
public class WornSetData {

    public WornSetData() {

    }

    public WornSetData(Set set) {
        this.setGUID = set.GUID();
    }

    @Store
    public String setGUID = "";
    @Store
    public int count = 0;
    @Store
    private List<Integer> lvls = new ArrayList<>();

    @Store // make sure 2 same ring uniques don't add set bonus
    private List<String> uniqueIds = new ArrayList<>();

    public Set getSet() {
        return SlashRegistry.Sets().get(setGUID);
    }

    public List<StatMod> getSetStats() {

        return getSet().getObtainedMods(this);

    }

    public void addSet(GearItemData gear) {

        if (gear.set == null || gear.set.baseSet == null) {
            return;
        }
        if (gear.set.baseSet.equals(setGUID)) {

            if (gear.isUnique()) {
                if (this.uniqueIds.contains(gear.uniqueGUID)) {
                    return; // don't add set count if same unique (like 2 same rings)
                }

                this.uniqueIds.add(gear.uniqueGUID);
            }

            lvls.add(gear.level);
            count++;

        }
    }

    public int getAverageLevel() {

        int sum = lvls.stream().mapToInt(Integer::intValue).sum();

        if (sum > 0) {
            return sum / lvls.size();
        } else {
            return 0;
        }
    }
}
