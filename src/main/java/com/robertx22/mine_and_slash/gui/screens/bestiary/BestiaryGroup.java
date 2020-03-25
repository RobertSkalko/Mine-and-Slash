package com.robertx22.mine_and_slash.gui.screens.bestiary;

import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum BestiaryGroup {

    UNIQUE_GEAR {
        @Override
        public List<BestiaryEntry> getAll(int lvl) {

            List<BestiaryEntry> list = new ArrayList<>();

            for (int tier = 0; tier < ITiered.MAX_TIER; tier++) {
                int finalTier = tier;
                List<ItemStack> stackList = SlashRegistry.UniqueGears()
                    .getFiltered(x -> x.Tier() == finalTier)
                    .stream()
                    .map(x -> {
                        return createStack(lvl, x.GUID());
                    })
                    .collect(Collectors.toList());

                if (!stackList.isEmpty()) {
                    list.add(new BestiaryEntry.Splitter("Tier: " + tier));
                    stackList.forEach(x -> list.add(new BestiaryEntry.Item(x)));
                }
            }

            return list;
        }

        @Override
        public String texName() {
            return "unique_gear";
        }

        @Override
        public ItemStack createStack(int lvl, String id) {
            UniqueGearBlueprint blueprint = new UniqueGearBlueprint(lvl, SlashRegistry.UniqueGears()
                .get(id));
            blueprint.unidentifiedPart.set(false);
            return blueprint.createStack();
        }
    };

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
