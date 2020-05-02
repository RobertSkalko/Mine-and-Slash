package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.data_generation.models.IAutoModel;
import com.robertx22.mine_and_slash.data_generation.models.ItemModelManager;
import com.robertx22.mine_and_slash.data_generation.unique_dungeons.UniqueDungeon;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class UniqueDungeonKeyItem extends Item implements IAutoLocName, IAutoModel {

    public UniqueDungeonKeyItem() {
        super(new Properties().group(CreativeTabs.MyModTab));
    }

    @Override
    public void generateModel(ItemModelManager manager) {
        manager.generated(this);
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Gear_Items;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName()
            .toString();
    }

    public static UniqueDungeon getDungeon(ItemStack stack) {

        if (stack.hasTag() && stack.getTag()
            .contains("dungeon")) {

            return SlashRegistry.UniqueDungeons()
                .get(stack.getTag()
                    .getString("dungeon"));

        } else {
            CompoundNBT nbt = new CompoundNBT(); // this makes even creative spawned keys usable as random keys
            nbt.putString("dungeon", SlashRegistry.UniqueDungeons()
                .random()
                .GUID());
            stack.setTag(nbt);

            return getDungeon(stack); // recursion warning
        }

    }

    @Override
    public String locNameForLangFile() {
        return "Dungeon Key";
    }

    @Override
    public String GUID() {
        return "dungeon_key";
    }
}