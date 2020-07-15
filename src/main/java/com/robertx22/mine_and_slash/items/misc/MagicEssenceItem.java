package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.data_generation.models.IAutoModel;
import com.robertx22.mine_and_slash.data_generation.models.ItemModelManager;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import net.minecraft.item.Item;

public class MagicEssenceItem extends Item implements IAutoLocName, IWeighted, IAutoModel {

    public MagicEssenceItem() {
        super(new Properties().maxStackSize(64)
            .group(CreativeTabs.MyModTab));
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Misc;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName()
            .toString();
    }

    @Override
    public String locNameForLangFile() {
        return "Magic Essence";
    }

    @Override
    public String GUID() {
        return "";
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public void generateModel(ItemModelManager manager) {
        manager.generated(this);
    }
}
