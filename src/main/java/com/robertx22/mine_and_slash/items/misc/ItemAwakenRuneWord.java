package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.items.currency.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.AwakenRuneWordLocReq;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.items.ItemDefault;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.rune.RuneWordData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

            String word = this.getWord(stack);

            RuneWord runeword = SlashRegistry.RuneWords().get(word);

            ITextComponent name = new StringTextComponent(TextFormatting.LIGHT_PURPLE + "")
                    .appendSibling(runeword.locName());

            Tooltip.add(Styles.GOLDCOMP()
                    .appendSibling(Words.Runeword.locName()
                            .appendText(": ")
                            .appendSibling(name)), tooltip);

            Tooltip.add("", tooltip);

            tooltip.add(Styles.REDCOMP()
                    .appendSibling(Words.NeedsGearWithRunesInserted.locName())
                    .appendText(": "));

            Tooltip.add("", tooltip);

            Tooltip.add(runeword.getRuneWordComboString(), tooltip);

            Tooltip.add("", tooltip);

            Tooltip.add(new StringTextComponent(TextFormatting.AQUA + "").appendSibling(Words.RunesNeeded
                    .locName()).appendText(": " + runeword.size()), tooltip);

            Tooltip.add("", tooltip);

            try {
                RuneWordData data = new RuneWordData();
                data.name = word;

                data.Mods = runeword.mods()
                        .stream()
                        .map(x -> StatModData.Load(x, 100))
                        .collect(Collectors.toList());

                TooltipInfo info = new TooltipInfo();
                info.level = 1;
                info.unitdata = new EntityCap.DefaultImpl();
                info.minmax = new MinMax(0, 100);

                tooltip.addAll(data.GetTooltipString(info));
            } catch (Exception e) {
                e.printStackTrace();
            }

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
    public final boolean canItemBeModified(ItemStack stack, ItemStack Currency) {

        GearItemData data = Gear.Load(stack);

        if (data != null) {
            for (BaseLocRequirement req : requirements()) {
                if (req.isNotAllowed(data, Currency)) {
                    return false;

                }
            }
        }
        return true;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(AwakenRuneWordLocReq.INSTANCE);
    }

}