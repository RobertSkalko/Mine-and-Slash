package com.robertx22.mine_and_slash.saveclasses.item_classes;

import com.robertx22.mine_and_slash.config.forge.ClientContainer;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponSwingCost;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.stats.types.resources.Energy;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.items.gearitems.offhands.IEffectItem;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IGearPartTooltip;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatModsContainer;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.MergedStats;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.ArrayList;
import java.util.List;

public class GearTooltipUtils {

    public static void BuildTooltip(GearItemData gear, ItemStack stack, ItemTooltipEvent event, UnitData data) {

        List<ITextComponent> tip = event.getToolTip();

        TooltipInfo info = new TooltipInfo(data, gear.getRarity()
            .StatPercents(), gear.level);

        GearItemSlot slot = gear.GetBaseGearType();

        tip.clear();

        tip.add(gear.GetDisplayName(stack));

        if (!gear.isIdentified()) {

            tip.add(new SText(""));

            tip.add(Styles.GRAYCOMP()
                .appendSibling(Words.ItemIsUnidentified.locName()));
            tip.add(Styles.GRAYCOMP()
                .appendSibling(Words.UseAnIdentifyScroll.locName()));

            tip.add(new SText(""));

            tip.add(TooltipUtils.lvlReq(gear.level, data));

            return;
        }

        if (gear.primaryStats != null) {
            tip.addAll(gear.primaryStats.GetTooltipString(info, gear));
        }

        tip.add(new StringTextComponent(""));

        List<IGearPartTooltip> list = new ArrayList<IGearPartTooltip>();

        tip.add(TooltipUtils.lvlReq(gear.level, data));

        if (gear.requirements != null) {
            tip.addAll(gear.requirements.GetTooltipString(info, gear));
        }

        if (gear.runes != null) {
            tip.addAll(gear.runes.GetTooltipString(info, gear));
        }

        if (gear.uniqueStats != null) {
            tip.addAll(gear.uniqueStats.GetTooltipString(info, gear));
        }

        tip.add(new StringTextComponent(""));

        if (Screen.hasShiftDown()) {
            list.add(gear.secondaryStats);
            list.add(gear.prefix);
            list.add(gear.suffix);
        } else {

            List<IStatModsContainer.LevelAndStats> lvlstatsmerged = new ArrayList<>();

            if (gear.secondaryStats != null) {
                lvlstatsmerged.addAll(gear.secondaryStats.GetAllStats(gear.level));
            }
            if (gear.suffix != null) {
                lvlstatsmerged.addAll(gear.suffix.GetAllStats(gear.level));
            }
            if (gear.prefix != null) {
                lvlstatsmerged.addAll(gear.prefix.GetAllStats(gear.level));
            }

            MergedStats merged = new MergedStats(lvlstatsmerged, info);
            list.add(merged);

        }

        list.add(gear.chaosStats);
        list.add(gear.set);

        for (IGearPartTooltip part : list) {

            if (part != null) {
                tip.addAll(part.GetTooltipString(info, gear));
                tip.add(new StringTextComponent(""));
            }

        }

        if (Screen.hasShiftDown()) {
            boolean addRarityTooltip = true;

            if (gear.isUnique) {
                IUnique unique = gear.uniqueStats.getUnique();

                addRarityTooltip = false;

                tip.add(new StringTextComponent(""));

                tip.add(TooltipUtils.uniqueTier(unique.Tier()));

                tip.add(new StringTextComponent(""));
            }

            if (addRarityTooltip) {

                GearRarity rarity = gear.getRarity();
                tip.add(TooltipUtils.rarity(rarity));

            }
        }

        if (Screen.hasShiftDown()) {
            if (!gear.isSalvagable) {
                tip.add(Styles.REDCOMP()
                    .appendSibling(Words.Unsalvagable.locName()));
            }
        }

        if (Screen.hasShiftDown()) {
            if (slot
                .isWeapon()) {

                WeaponSwingCost costs = slot
                    .getSwingCosts();

                tip.add(new StringTextComponent(""));
                if (costs
                    .GetEnergyCost(data.getLvlForResourceCosts()) > 0) {
                    tip.add(Styles.GREENCOMP()
                        .appendSibling(Energy.getInstance()
                            .locName()
                            .appendText(": " + (int) costs
                                .GetEnergyCost(data.getLvlForResourceCosts()))));
                }
                if (costs
                    .GetManaCost(data.getLvlForResourceCosts()) > 0) {
                    tip.add(Styles.BLUECOMP()
                        .appendSibling(Mana.getInstance()
                            .locName()
                            .appendText(": " + (int) costs
                                .GetManaCost(data.getLvlForResourceCosts()))));
                }

                tip.addAll(slot.weaponDamageMulti()
                    .tooltipDesc());
                tip.addAll(slot
                    .getWeaponMechanic()
                    .tooltipDesc());
            }
        }

        if (Screen.hasShiftDown() == false) {

        } else {

            if (gear.usesInstability()) {
                event.getToolTip()
                    .add(TooltipUtils.instability(gear));
            }

        }

        if (Screen.hasShiftDown()) {
            if (stack.getItem() instanceof IEffectItem) {
                IEffectItem effect = (IEffectItem) stack.getItem();
                event.getToolTip()
                    .addAll(effect.getEffectTooltip(Screen.hasShiftDown()));
            }
        }

        if (gear.isUnique) {
            IUnique unique = gear.uniqueStats.getUnique();

            List<String> lores = TooltipUtils.cutIfTooLong(CLOC.translate(unique.locDesc()));
            tip.add(new StringTextComponent(""));

            int i = 0;
            for (String desc : lores) {
                ITextComponent comp = Styles.GREENCOMP();

                if (i == 0) {
                    comp.appendText("'");
                }
                comp.appendText(desc);

                if (i == lores.size() - 1) {
                    comp.appendText("'");
                }
                i++;
                tip.add(comp);

            }
        }
        tip.add(new StringTextComponent(""));

        if (Screen.hasShiftDown() == false) {
            event.getToolTip()
                .add(Styles.BLUECOMP()
                    .appendSibling(CLOC.tooltip("press_shift_more_info")));
        }

        BaseSpell spell = gear.getRightClickSpell();
        if (spell != null) {
            tip.add(new SText(""));

            tip.add(new SText(spell.getElement().format + "Right click: ").appendSibling(spell
                .getName()
                .locName()));

        }

        if (Screen.hasShiftDown()) {
//
        }

        List<ITextComponent> tool = TooltipUtils.removeDoubleBlankLines(tip,
            ClientContainer.INSTANCE.REMOVE_EMPTY_TOOLTIP_LINES_IF_MORE_THAN_X_LINES
                .get()
        );
        tip.clear();
        tip.addAll(tool);

    }
}
