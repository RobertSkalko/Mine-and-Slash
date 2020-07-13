package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.data_generation.models.IAutoModel;
import com.robertx22.mine_and_slash.data_generation.models.ItemModelManager;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import net.minecraft.item.Item;

public class SkillGemItem extends Item implements IAutoModel {

    public SkillGemItem() {
        super(new Item.Properties().maxStackSize(1)
            .maxDamage(0)
            .group(CreativeTabs.MyModTab));
    }

    @Override
    public void generateModel(ItemModelManager manager) {
        manager.generated(this);
    }

}
