package com.robertx22.mine_and_slash.gui.screens.bestiary.splitters;

import com.robertx22.mine_and_slash.gui.screens.bestiary.BestiaryEntry;
import com.robertx22.mine_and_slash.gui.screens.bestiary.groups.BestiaryGroup;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TierSplitter<T extends ITiered> extends BaseSplitter<T> {

    public TierSplitter(BestiaryGroup group) {
        super(group);
    }

    @Override
    public List<BestiaryEntry> split(int lvl) {

        List<BestiaryEntry> entries = new ArrayList<>();

        List<T> uniques = group.getAll(lvl);

        int max = ITiered.getMaxTier();
        for (int tier = 0; tier < max + 1; tier++) {
            int finalTier = tier;

            List<ItemStack> stackList = uniques.stream()
                .filter(x -> x.getTier() == finalTier)
                .map(x -> {
                    return group.createStack(lvl, x);
                })
                .collect(Collectors.toList());

            if (!stackList.isEmpty()) {
                entries.add(new BestiaryEntry.Splitter("Tier: " + tier));
                stackList.forEach(x -> entries.add(new BestiaryEntry.Item(x, group)));
            }
        }

        return entries;
    }
}
