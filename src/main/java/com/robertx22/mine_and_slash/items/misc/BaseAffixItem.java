package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.empty_entries.EmptyAffix;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BaseAffixItem extends Item {

    public BaseAffixItem() {
        super(new Properties().maxStackSize(1));
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return super.getDisplayName(stack)
            .appendText(": ")
            .appendSibling(getAffix(stack).locName());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        BaseAffix affix = getAffix(stack);

        TooltipInfo info = new TooltipInfo();
        info.level = Load.Unit(MMORPG.proxy.getPlayerEntityFromContext(null))
            .getLevel();

        affix.StatMods()
            .forEach(x -> {
                tooltip.addAll(StatModData.Load(x, 100)
                    .GetTooltipString(info));
            });

        tooltip.add(new SText(""));

        tooltip.addAll(affix.requirements()
            .GetTooltipString(new TooltipInfo()));

    }

    public static ItemStack getItemFor(BaseAffix affix) {
        ItemStack stack;
        if (affix.type.equals(BaseAffix.Type.prefix)) {
            stack = new ItemStack(ModItems.PREFIX_ITEM.get());
        } else {
            stack = new ItemStack(ModItems.SUFFIX_ITEM.get());
        }

        stack.setTag(new CompoundNBT());
        stack.getTag()
            .putString("affix", affix.GUID());
        return stack;

    }

    public BaseAffix getAffix(ItemStack stack) {
        try {
            return SlashRegistry.Affixes()
                .get(stack.getTag()
                    .getString("affix"));
        } catch (Exception e) {
        }

        return EmptyAffix.getInstance();
    }
}
