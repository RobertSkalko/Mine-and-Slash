package com.robertx22.mine_and_slash.gui.screens.bestiary.splitters;

import com.robertx22.mine_and_slash.gui.screens.bestiary.BestiaryEntry;
import com.robertx22.mine_and_slash.gui.screens.bestiary.groups.BestiaryGroup;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DefaultSplitter extends BaseSplitter {

    public DefaultSplitter(BestiaryGroup group) {
        super(group);
    }

    @Override
    public List<BestiaryEntry> split(int lvl) {

        List<BestiaryEntry> entries = new ArrayList<>();

        List<ItemStack> stacks = new ArrayList<>();

        group.getAll(lvl)
            .forEach(x -> stacks.add(group.createStack(lvl, x)));

        stacks.forEach(x -> entries.add(new BestiaryEntry.Item(x, group)));

        return entries;

    }
}
