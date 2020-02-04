package com.robertx22.mine_and_slash.potion_effects.bases;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class OnTickAction {
    public int eachXticks;

    public Function<PotionContext, PotionContext> action;
    Function<TooltipInfo, List<ITextComponent>> tooltip;

    public OnTickAction(int eachXticks, Function<PotionContext, PotionContext> action,
                        Function<TooltipInfo, List<ITextComponent>> tooltip) {
        this.eachXticks = eachXticks;
        this.action = action;
        this.tooltip = tooltip;
    }

    public final void onTick(PotionContext ctx) {
        action.apply(ctx);
    }

    public List<ITextComponent> getTooltip(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        if (tooltip != null) {
            list.add(new StringTextComponent(TextFormatting.YELLOW + "Effect occurs every " + eachXticks + " ticks."));
            list.addAll(tooltip.apply(info));
        }

        return list;
    }
}
