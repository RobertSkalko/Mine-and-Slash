package com.robertx22.mine_and_slash.database.items.currency.loc_reqs;

import com.robertx22.mine_and_slash.saveclasses.gearitem.GearItemEnum;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.Arrays;
import java.util.function.Predicate;

public class GearEnumLocReq extends BaseLocRequirement {

    public static final GearEnumLocReq AFFIXES = new GearEnumLocReq(x -> x.canGetAffixes());
    public static final GearEnumLocReq SETS = new GearEnumLocReq(x -> x.canGetSet());
    public static final GearEnumLocReq SECONDARY_STATS = new GearEnumLocReq(x -> x.canGetSecondaryStats());
    public static final GearEnumLocReq PRIMARY_STATS = new GearEnumLocReq(x -> x.canGetPrimaryStats());
    public static final GearEnumLocReq CHAOS_STATS = new GearEnumLocReq(x -> x.canGetChaosStats());
    public static final GearEnumLocReq INFUSIONS = new GearEnumLocReq(x -> x.canGetInfusions());
    public static final GearEnumLocReq REROLL_NUMBERS = new GearEnumLocReq(x -> x.canRerollNumbers());
    public static final GearEnumLocReq RUNED = new GearEnumLocReq(x -> x == GearItemEnum.RUNED);

    private GearEnumLocReq(Predicate<GearItemEnum> pred) {
        this.gearsThatCanDoThis = pred;
    }

    Predicate<GearItemEnum> gearsThatCanDoThis;

    @Override
    public ITextComponent getText() {

        ITextComponent comp = Words.AllowedOn.locName().appendText(": ");

        Arrays.stream(GearItemEnum.values())
                .filter(gearsThatCanDoThis)
                .map(x -> x.word())
                .forEach(x -> comp.appendSibling(x.locName().appendText(" ")));

        return comp;
    }

    @Override

    public boolean isAllowed(Object object, ItemStack currency) {

        if (object instanceof GearItemData) {
            GearItemData gear = (GearItemData) object;

            GearItemEnum genum = gear.getGearEnum();

            if (gearsThatCanDoThis.test(genum)) {
                return true;
            }
        }

        return false;
    }

}
