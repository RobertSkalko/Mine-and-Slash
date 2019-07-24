package com.robertx22.mine_and_slash.items.infusions.upgrade;

import com.robertx22.mine_and_slash.items.currency.CurrencyItem;
import com.robertx22.mine_and_slash.items.currency.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.InfusionData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Tooltip;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseUpgradeInfusion extends CurrencyItem implements ICurrencyItemEffect {

    public BaseUpgradeInfusion(String name) {
        super(name);

    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        Tooltip.add("", tooltip);
        Tooltip.add(Styles.GOLDCOMP()
                .appendSibling(Words.Major_Success_Chance.locName()
                        .appendText(": " + this.bonusSuccessChance() + "%")), tooltip);

        Tooltip.add(Styles.GOLDCOMP()
                .appendSibling(Words.Major_Success_Bonus.locName()
                        .appendText(": " + this.critOnSuccessChance() + "%")), tooltip);

        Tooltip.add(Styles.GOLDCOMP()
                .appendSibling(Words.Major_Failure_Chance.locName()
                        .appendText(": " + this.majorFailureChance() + "%")), tooltip);

        Tooltip.add(Styles.BLUECOMP()
                .appendSibling(Words.Item_modifiable_in_station.locName()), tooltip);

    }

    public void TryUpgradeInfusion(InfusionData infusion) {

        if (RandomUtils.roll(infusion.getChance() + this.bonusSuccessChance())) {

            if (RandomUtils.roll(this.critOnSuccessChance())) {
                infusion.majorSuccess();

            } else {
                infusion.success();
            }

        } else {
            if (RandomUtils.roll(majorFailureChance())) {
                infusion.majorFail();
            } else {
                infusion.fail();

            }
        }

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        this.TryUpgradeInfusion(gear.infusion);

        Gear.Save(stack, gear);

        return stack;

    }

    @Override
    public boolean canItemBeModifiedPROTECTED(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);

        return gear != null && gear.infusion != null && gear.infusion.canUpgrade();

    }

    public abstract float critOnSuccessChance();

    public abstract float bonusSuccessChance();

    public abstract float majorFailureChance();

    @Override
    public String locDescForLangFile() {
        return "Used when attempting Infusion Upgrading";
    }

}
