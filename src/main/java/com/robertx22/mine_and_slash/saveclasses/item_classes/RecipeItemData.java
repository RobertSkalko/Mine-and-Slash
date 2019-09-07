package com.robertx22.mine_and_slash.saveclasses.item_classes;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltip;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

@Storable
public class RecipeItemData implements ITooltip, IGUID {

    public RecipeItemData() {

    }

    public RecipeItemData(BaseRecipe recipe) {
        this.guid = recipe.GUID();
    }

    @Store
    public String guid = "";

    @Override
    public String GUID() {
        return guid;
    }

    @Override
    public void BuildTooltip(ItemStack stack, ItemTooltipEvent event, Unit unit,
                             EntityCap.UnitData data) {

    }
}
