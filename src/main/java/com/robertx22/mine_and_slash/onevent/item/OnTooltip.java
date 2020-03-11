package com.robertx22.mine_and_slash.onevent.item;

import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.new_content.trader.ISellPrice;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipContext;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RecipeItemData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.datasaving.Recipe;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnTooltip {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onItemTooltip(ItemTooltipEvent event) {

        buildDataTootltip(event);
        buildCurrencyEffectTooltip(event);

        ItemStack stack = event.getItemStack();

        if (ISellPrice.hasPrice(stack)) {
            event.getToolTip()
                .add(new SText(""));
            event.getToolTip()
                .add(new SText(""));
        }

    }

    private static void buildDataTootltip(ItemTooltipEvent event) {

        try {
            if (Screen.hasControlDown()) {

                GearItemData gear = Gear.Load(event.getItemStack());

                if (gear == null) {
                    return;
                }
            }

            if (event.getPlayer() == null || event.getPlayer().world == null || !event.getPlayer().world.isRemote) {
                return;
            }

            ItemStack stack;

            stack = event.getItemStack();

            UnitData unitdata = Load.Unit(event.getPlayer());

            if (unitdata == null) {
                return;
            }

            Unit unit = unitdata.getUnit();

            if (unit == null) {
                return;
            }

            TooltipContext ctx = new TooltipContext(stack, event, unitdata);

            if (!stack.hasTag()) {
                return;
            }

            ICommonDataItem data = ICommonDataItem.load(stack);

            if (data != null) {
                data.BuildTooltip(ctx);
            } else {
                if (stack.getItem()
                    .getRegistryName() != null) {
                    if (SlashRegistry.CompatibleItems()
                        .isRegistered(stack.getItem()
                            .getRegistryName()
                            .toString())) {

                        event.getToolTip()
                            .add(new StringTextComponent(Styles.RED + "Compatible Mine and Slash Item"));

                    }
                }
            }

            RecipeItemData recipe = Recipe.Load(stack);
            if (recipe != null) {
                recipe.BuildTooltip(ctx);
            }

            ITextComponent broken = TooltipUtils.itemBrokenText(stack, data);
            if (broken != null) {
                event.getToolTip()
                    .add(broken);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void buildCurrencyEffectTooltip(ItemTooltipEvent event) {

        if (event.getItemStack()
            .getItem() instanceof ICurrencyItemEffect) {
            ICurrencyItemEffect currency = (ICurrencyItemEffect) event.getItemStack()
                .getItem();
            currency.addToTooltip(event.getToolTip());
        }

    }

}
