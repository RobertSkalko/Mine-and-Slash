package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.items.ItemDefault;
import com.robertx22.mine_and_slash.items.currency.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Tooltip;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nullable;
import java.util.List;

public class ItemAwakenRuneWord extends Item implements ICurrencyItemEffect {

    @ObjectHolder(Ref.MODID + ":awaken_runeword")
    public static final Item ITEM = null;

    public ItemAwakenRuneWord() {

        super(new ItemDefault());

        RegisterItemUtils.RegisterItemName(this, "awaken_runeword");

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        if (stack != null && SlashRegistry.RuneWords().isRegistered(getWord(stack))) {
            Tooltip.add("", tooltip);
            Tooltip.add(Styles.GOLDCOMP()
                    .appendSibling(Words.Runeword.locName().appendText(": ")), tooltip);
            String word = this.getWord(stack);

            RuneWord runeword = SlashRegistry.RuneWords().get(word);

            Tooltip.add(new StringTextComponent(TextFormatting.GOLD + "").appendSibling(runeword
                    .locName()), tooltip);

            Tooltip.add(new StringTextComponent(TextFormatting.GREEN + runeword.getRuneWordComboString()), tooltip);

            Tooltip.add(TextFormatting.AQUA + "Runes: " + runeword.size(), tooltip);

            Tooltip.add("", tooltip);

        }
        Tooltip.add(Styles.BLUECOMP()
                .appendSibling(Words.Item_modifiable_in_station.locName()), tooltip);
        Tooltip.add(Styles.BLUECOMP()
                .appendSibling(Words.unlocks_runeword_combo.locName()), tooltip);
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack currency) {

        if (currency.getItem() instanceof ItemAwakenRuneWord) {
            GearItemData gear = Gear.Load(stack);
            if (gear != null) {
                gear.runes.AwakenRuneWord(this.getWord(currency));
                Gear.Save(stack, gear);
            }
        }

        return stack;
    }

    public void setWord(ItemStack stack, RuneWord word) {
        CompoundNBT nbt = stack.getTag();
        if (nbt == null) {
            nbt = new CompoundNBT();
        }
        nbt.putString("runeword", word.GUID());
        stack.setTag(nbt);

    }

    public String getWord(ItemStack stack) {

        if (stack != null && stack.hasTag() && stack.getTag().contains("runeword")) {
            return stack.getTag().getString("runeword");
        }

        return "";

    }

    @Override
    public boolean canItemBeModifiedPROTECTED(ItemStack item, ItemStack currency) {

        if (currency.getItem() instanceof ItemAwakenRuneWord) {
            GearItemData gear = Gear.Load(item);

            if (gear != null) {

                String wordtext = this.getWord(currency);

                if (SlashRegistry.RuneWords().isRegistered(wordtext)) {
                    if (gear.isRuned() && gear.runes.canAwakenRuneWord(SlashRegistry.RuneWords()
                            .get(wordtext))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}