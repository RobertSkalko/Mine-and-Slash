package com.robertx22.mine_and_slash.gui.screens.bestiary.splitters;

import com.robertx22.mine_and_slash.gui.screens.bestiary.BestiaryEntry;
import com.robertx22.mine_and_slash.gui.screens.bestiary.groups.BestiaryGroup;

import java.util.List;

public abstract class BaseSplitter<T> {

    public BaseSplitter(BestiaryGroup group) {
        this.group = group;
    }

    public BestiaryGroup group;

    public abstract List<BestiaryEntry> split(int lvl);

}
