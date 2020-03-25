package com.robertx22.mine_and_slash.gui.screens.bestiary.groups;

import com.robertx22.mine_and_slash.gui.screens.bestiary.splitters.BaseSplitter;
import com.robertx22.mine_and_slash.gui.screens.bestiary.splitters.DefaultSplitter;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public abstract class BestiaryGroup<T> {

    private static List<BestiaryGroup> all = new ArrayList<>();

    public static List<BestiaryGroup> getAll() {

        if (all.isEmpty()) {

            all.add(new UniqueGearBestiary());
            all.add(new RuneBestiary());
            all.add(new RuneWordBestiary());
            all.add(new CurrencyBestiary());
            all.add(new PrefixBestiary());
            all.add(new SuffixBestiary());

        }

        return all;

    }

    public final ResourceLocation getTextureLoc() {
        return new ResourceLocation(Ref.MODID, "textures/gui/bestiary/group_icons/" + texName() + ".png");
    }

    public abstract List<T> getAll(int lvl);

    public abstract ITextComponent getName();

    public int getSize() {
        return getAll(1).size();
    }

    public abstract ITextComponent getName(ItemStack stack);

    public BaseSplitter<T> getDefaultSplitter() {
        return new DefaultSplitter(this);
    }

    public abstract String texName();

    public abstract ItemStack createStack(int lvl, T entry);

}
