package com.robertx22.mine_and_slash.gui.screens.bestiary.groups;

import com.robertx22.mine_and_slash.gui.screens.bestiary.BestiaryEntry;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public abstract class BestiaryGroup<T> {

    public final ResourceLocation getTextureLoc() {
        return new ResourceLocation(Ref.MODID, "textures/gui/bestiary/group_icons/" + texName() + ".png");
    }

    public abstract List<BestiaryEntry> getAll(int lvl);

    public int getSize() {
        return getAll(1).size();
    }

    public abstract String texName();

    public abstract ItemStack createStack(int lvl, String id);

}
