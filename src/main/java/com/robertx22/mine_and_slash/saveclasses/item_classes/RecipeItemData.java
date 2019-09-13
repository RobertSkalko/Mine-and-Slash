package com.robertx22.mine_and_slash.saveclasses.item_classes;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltip;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipContext;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

@Storable
public class RecipeItemData implements ITooltip, IGUID {

    public RecipeItemData() {

    }

    public RecipeItemData(BaseRecipe recipe) {
        this.guid = recipe.GUID();
    }

    @Store
    public String guid = "";

    public BaseRecipe getRecipe() {
        return SlashRegistry.Recipes().get(GUID());
    }

    @Override
    public String GUID() {
        return guid;
    }

    @Override
    public void BuildTooltip(TooltipContext ctx) {

        List<ITextComponent> t = ctx.event.getToolTip();

        t.add(new StringTextComponent(""));
        t.add(new StringTextComponent(TextFormatting.GREEN + "[Exp] Gained: " + this.getRecipe()
                .getExpGained()));
        t.add(new StringTextComponent(TextFormatting.YELLOW + "[Level] Required " + this.getRecipe()
                .getLevelReq()));
        t.add(new StringTextComponent(""));

    }
}
