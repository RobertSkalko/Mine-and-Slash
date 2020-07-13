package com.robertx22.mine_and_slash.saveclasses.item_classes;

import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipContext;
import com.robertx22.mine_and_slash.uncommon.datasaving.SkillGem;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.DataItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.ItemStack;

@Storable
public class SkillGemData implements ICommonDataItem<GearRarity> {

// todo add affixes and stuff

    @Store
    public String spell_id = "";

    @Override
    public DataItemType getDataType() {
        return DataItemType.SKILL_GEM;
    }

    @Override
    public void saveToStack(ItemStack stack) {
        SkillGem.Save(stack, this);
    }

    public BaseSpell getSpell() {
        return SlashRegistry.Spells()
            .get(spell_id);
    }

    @Override
    public void BuildTooltip(TooltipContext ctx) {

        BaseSpell spell = SlashRegistry.Spells()
            .get(spell_id);

        ctx.tooltip
            .add(spell.getLocName());

    }

    @Override
    public ItemStack getSalvageResult(float salvageBonus) {
        return ItemStack.EMPTY;
    }

    @Override
    public int getRarityRank() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Gears.get(0);
    }

    @Override
    public int getTier() {
        return 0;
    }

    public ItemStack toItemStack() {
        ItemStack stack = new ItemStack(SlashRegistry.Spells()
            .get(spell_id)
            .getItem());

        this.saveToStack(stack);

        return stack;
    }

    public void create() {

        this.spell_id = SlashRegistry.Spells()
            .random()
            .GUID();

    }
}
