package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.SpellRarity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.loot.LootUtils;
import com.robertx22.mine_and_slash.loot.blueprints.bases.SpellPart;
import com.robertx22.mine_and_slash.loot.gens.stack_changers.DamagedGear;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Spell;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

public class SpellBlueprint extends ItemBlueprint {

    public SpellBlueprint(int level) {
        super(level);
        actionsAfterGeneration.add(DamagedGear.INSTANCE);
    }

    public SpellPart spellPart = new SpellPart(this);

    @Override
    public ItemStack generate() {
        BaseSpell spell = spellPart.get();
        ItemStack stack = new ItemStack(spell.getSpellItem());
        SpellItemData data = new SpellItemData();
        data.rarity = rarity.get().Rank();
        SpellRarity rarity = data.getRarity();

        data.rarity = rarity.Rank();

        data.spellGUID = spell.GUID();

        data.level = level.get();
        data.baseEffectPercent = RandomUtils.RandomRange(rarity.SpellBasePercents().Min, rarity
                .SpellBasePercents().Max);
        data.scalingEffectPercent = RandomUtils.RandomRange(rarity.SpellScalingPercents().Min, rarity
                .SpellScalingPercents().Max);
        data.manaCostPercent = RandomUtils.RandomRange(SpellItemData.MIN_MANA_COST_PERCENT, SpellItemData.MAX_MANA_COST_PERCENT);

        Spell.Save(stack, data);

        return LootUtils.RandomDamagedGear(stack, data.getRarity(), data.level);
    }

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {
        return Rarities.Spells;
    }

}
