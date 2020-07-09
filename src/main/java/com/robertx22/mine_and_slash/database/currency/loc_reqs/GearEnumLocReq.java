package com.robertx22.mine_and_slash.database.currency.loc_reqs;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.GearItemEnum;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GearEnumLocReq extends BaseLocRequirement {

    public static final GearEnumLocReq AFFIXES = new GearEnumLocReq(x -> x.canGetAffixes());
    public static final GearEnumLocReq CAN_PRIMARY_STATS = new GearEnumLocReq(x -> x.canGetPrimaryStats());
    public static final GearEnumLocReq REROLL_NUMBERS = new GearEnumLocReq(x -> x.canRerollNumbers());

    private GearEnumLocReq(Predicate<GearItemEnum> pred) {
        this.gearsThatCanDoThis = pred;
    }

    Predicate<GearItemEnum> gearsThatCanDoThis;

    @Override
    public ITextComponent getText() {

        ITextComponent comp = Words.AllowedOn.locName()
            .appendText(": ");

        List<GearItemEnum> enums = Arrays.stream(GearItemEnum.values())
            .filter(x -> gearsThatCanDoThis.test(x))
            .collect(Collectors.toList());

        int count = 1;
        for (GearItemEnum x : enums) {

            comp.appendSibling(x.word()
                .locName());

            if (count < enums.size()) {
                comp.appendText(", ");
            }

            count++;
        }

        return comp;
    }

    @Override

    public boolean isAllowed(LocReqContext context) {

        if (context.data instanceof GearItemData) {
            GearItemData gear = (GearItemData) context.data;

            GearItemEnum genum = gear.getGearEnum();

            if (gearsThatCanDoThis.test(genum)) {
                return true;
            }
        }

        return false;
    }

}
