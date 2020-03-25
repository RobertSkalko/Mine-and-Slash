package com.robertx22.mine_and_slash.gui.screens.bestiary.groups;

import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.gui.screens.bestiary.BestiaryEntry;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UniqueGearGroup extends BestiaryGroup<IUnique> {

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
        blueprint.suffixChancePart.set(false);
        blueprint.prefixChancePart.set(false);
        return blueprint.createStack();
    }
}
