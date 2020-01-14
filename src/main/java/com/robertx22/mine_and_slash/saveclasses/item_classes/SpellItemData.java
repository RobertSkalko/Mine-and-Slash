package com.robertx22.mine_and_slash.saveclasses.item_classes;

import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.SpellRarity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipContext;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.ItemType;
import com.robertx22.mine_and_slash.uncommon.datasaving.Spell;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.DataItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.StatUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Tooltip;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@Storable
public class SpellItemData implements ICommonDataItem {

    public SpellItemData() {

    }

    public static final int MIN_MANA_COST_PERCENT = 50;
    public static final int MAX_MANA_COST_PERCENT = 100;

    @Store
    public int tier = 0;
    @Store
    public int level = 1;
    @Store
    public String spellGUID;
    @Store
    public int rarity = 0;
    @Store
    public int manaCostPercent = 100;
    @Store
    public int scalingEffectPercent = 100;
    @Store
    public int baseEffectPercent = 100;

    @Override
    public void saveToStack(ItemStack stack) {
        Spell.Save(stack, this);
    }

    @Override
    public String getUniqueGUID() {
        return this.spellGUID;
    }

    public int getManaCost(EntityCap.UnitData data) {
        return (int) StatUtils.calculateNormalScalingStatGrowth(this.getSpell()
                .getManaCost() * this.manaCostPercent / 100, data.getLvlForResourceCosts());
    }

    public int getBaseValue() {
        return (int) StatUtils.calculateNormalScalingStatGrowth(2 + getSpell().getBaseValue() * baseEffectPercent / 100, level);
    }

    public float getScalingValue() {
        return (getSpell().ScalingValue().Multi * scalingEffectPercent / 100);
    }

    private int getMinScaling() {
        return (int) (getSpell().ScalingValue().Multi * getRarity().SpellScalingPercents().Min);
    }

    private int getMaxScaling() {
        return (int) (getSpell().ScalingValue().Multi * getRarity().SpellScalingPercents().Max);
    }

    private int getMinBase() {
        return (int) StatUtils.calculateNormalScalingStatGrowth((1 + (float) getSpell().getBaseValue() * getRarity()
                .SpellBasePercents().Min / 100), level);
    }

    private int getMaxBase() {
        return (int) StatUtils.calculateNormalScalingStatGrowth((1 + (float) getSpell().getBaseValue() * getRarity()
                .SpellBasePercents().Max / 100), level);
    }

    private int getMinMana(EntityCap.UnitData data) {
        return (int) StatUtils.calculateNormalScalingStatGrowth(this.getSpell()
                .getManaCost() * MIN_MANA_COST_PERCENT / 100, data.getLvlForResourceCosts());
    }

    private int getMaxMana(EntityCap.UnitData data) {
        return (int) StatUtils.calculateNormalScalingStatGrowth(this.getSpell()
                .getManaCost() * MAX_MANA_COST_PERCENT / 100, data.getLvlForResourceCosts());
    }

    @Override
    public DataItemType getDataType() {
        return DataItemType.SPELL;
    }

    @Override
    public int getRarityRank() {
        return this.rarity;
    }

    public SpellRarity getRarity() {
        return Rarities.Spells.get(this.rarity);
    }

    public ITextComponent getScalingDesc(boolean moreInfo) {

        ITextComponent text = new StringTextComponent("");

        try {
            text = Words.Scaling_Value.locName()
                    .appendText(": ")
                    .appendSibling(getSpell().ScalingValue().GetStat().locName())
                    .appendText(" ")
                    .appendSibling(Words.By.locName())
                    .appendText(" : " + (int) (getScalingValue() * 100) + "%");
        } catch (Exception e) {
            e.printStackTrace();

        }

        if (moreInfo) {
            text.appendText(" (" + getMinScaling() + "-" + getMaxScaling() + ")");
        }

        return text;

    }

    public ITextComponent getBaseDesc(boolean moreInfo) {

        ITextComponent text = Words.BaseValue.locName()
                .appendText(": " + this.getBaseValue());

        if (moreInfo) {

            text.appendText("" + " (" + getMinBase() + "-" + getMaxBase() + ")");
        }

        return text;
    }

    public ITextComponent getManaDesc(EntityCap.UnitData data, boolean moreInfo) {

        ITextComponent text = Words.Mana_Cost.locName()
                .appendText(": " + this.getManaCost(data));

        if (moreInfo) {
            text.appendText(" (" + getMinMana(data) + "-" + getMaxMana(data) + ")");
        }

        return text;
    }

    public static float SPELL_DMG_RANDOMNESS = 1.5F;

    public int getDamage(Unit unit) {

        //        BaseSpell spell = getSpell();

        int basedmg = getBaseValue();
        int scalingdmg = (int) getScalingValue();

        int total = basedmg + scalingdmg;

        return total;

    }

    public BaseSpell getSpell() {
        return SlashRegistry.Spells().get(this.spellGUID);
    }

    @Override
    public ItemStack getSalvageResult(float salvageBonus) {

        int min = tryIncreaseAmount(salvageBonus, 1);
        int max = tryIncreaseAmount(salvageBonus, 2);

        ItemStack stack = ItemStack.EMPTY;

        if (RandomUtils.roll(this.getRarity().specialItemChance())) {

            Item item = SlashRegistry.CurrencyItems()
                    .getWrapped()
                    .ofCurrencyUsableOnItemType(ItemType.GEAR)
                    .ofTierOrLess(this.rarity * 3)
                    .random();

            stack = new ItemStack(item);
        } else {

            int amount = RandomUtils.RandomRange(min, max);

            ItemOre ore = (ItemOre) ItemOre.ItemOres.get(rarity);
            stack = new ItemStack(ore);
            stack.setCount(amount);

        }

        return stack;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void BuildTooltip(TooltipContext ctx) {

        List<ITextComponent> tooltip = ctx.event.getToolTip();

        if (getSpell() != null) {

            BaseSpell spell = getSpell();

            GearRarity rarity = Rarities.Items.get(this.rarity);

            tooltip.add(TooltipUtils.lvlReq(level, ctx.data));

            Tooltip.add("", tooltip);

            boolean moreInfo = Screen.hasShiftDown();

            Tooltip.add(Styles.GREENCOMP()
                    .appendSibling(Words.Stats.locName().appendText(": ")), tooltip);

            Tooltip.add(new StringTextComponent(TextFormatting.RED + " * ").appendSibling(getManaDesc(ctx.data, moreInfo)), tooltip);

            Tooltip.add(new StringTextComponent(TextFormatting.RED + " * ").appendSibling(getBaseDesc(moreInfo)), tooltip);

            if (spell.hasScalingValue()) {
                Tooltip.add(new StringTextComponent(TextFormatting.RED + " * ").appendSibling(getScalingDesc(moreInfo)), tooltip);
            }

            Tooltip.add("", tooltip);

            Tooltip.add(Styles.GREENCOMP()
                    .appendSibling(Words.Use_Time.locName()
                            .appendText(": " + getSpell().getUseDurationInSeconds() + " sec.")), tooltip);

            Tooltip.add("", tooltip);

            Tooltip.add(Styles.AQUACOMP()
                    .appendSibling(Words.Type.locName()
                            .appendText(": ")
                            .appendText(spell.typeString())), tooltip);

            Tooltip.add("", tooltip);

            Tooltip.add(Styles.LIGHT_PURPLECOMP()
                    .appendSibling(getSpell().GetDescription(this)), tooltip);

            Tooltip.add("", tooltip);

            tooltip.add(TooltipUtils.rarity(rarity));

        }

    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int Tier() {
        return this.tier;
    }

    @Override
    public String getSpecificType() {
        return this.spellGUID;
    }

}
