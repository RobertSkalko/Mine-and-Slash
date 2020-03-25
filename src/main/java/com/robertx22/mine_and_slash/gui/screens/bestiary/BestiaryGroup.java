package com.robertx22.mine_and_slash.gui.screens.bestiary;

import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.stream.Collectors;

public enum BestiaryGroup {

    UNIQUE_GEAR {
        @Override
        public List<ItemStack> getAll(int lvl) {
            return SlashRegistry.UniqueGears()
                .getList()
                .stream()
                .map(x -> createStack(lvl, x.GUID()))
                .collect(Collectors.toList());
        }

        @Override
        public String texName() {
            return "unique_gear";
        }

        @Override
        public ItemStack createStack(int lvl, String id) {
            return new UniqueGearBlueprint(lvl, SlashRegistry.UniqueGears()
                .get(id)).createStack();
        }
    };

    public final ResourceLocation getTextureLoc() {
        return new ResourceLocation(Ref.MODID, "textures/gui/bestiary/group_icons/" + texName() + ".png");
    }

    public abstract List<ItemStack> getAll(int lvl);

    public abstract String texName();

    public abstract ItemStack createStack(int lvl, String id);

}
