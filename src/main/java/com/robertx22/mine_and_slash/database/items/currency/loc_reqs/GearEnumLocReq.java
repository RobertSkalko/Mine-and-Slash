package com.robertx22.mine_and_slash.database.items.currency.loc_reqs;

import com.robertx22.mine_and_slash.saveclasses.gearitem.GearItemEnum;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.Arrays;
import java.util.function.Predicate;

class GearEnumLocReq extends BaseLocRequirement {

    GearEnumLocReq AFFIXES = new GearEnumLocReq(x -> x.canGetAffixes());
    GearEnumLocReq SETS = new GearEnumLocReq(x -> x.canGetSet());
    GearEnumLocReq SECONDARY_STATS = new GearEnumLocReq(x -> x.canGetSecondaryStats());
    GearEnumLocReq PRIMARY_STATS = new GearEnumLocReq(x -> x.canGetPrimaryStats());
    GearEnumLocReq CHAOS_STATS = new GearEnumLocReq(x -> x.canGetChaosStats());
    GearEnumLocReq INFUSIONS = new GearEnumLocReq(x -> x.canGetInfusions());
    GearEnumLocReq REROLL_NUMBERS = new GearEnumLocReq(x -> x.canRerollNumbers());

    public GearEnumLocReq(Predicate<GearItemEnum> pred) {
        this.gearsThatCanDoThis = pred;
    }

    Predicate<GearItemEnum> gearsThatCanDoThis;

    @Override
    public ITextComponent getText() {

        ITextComponent comp = new StringTextComponent("");

        Arrays.stream(GearItemEnum.values())
                .filter(gearsThatCanDoThis)
                .map(x -> x.word())
                .forEach(x -> comp.appendSibling(x.locName().appendText(" ")));

        return comp;
    }

    @Override
    public boolean isAllowed(Object object) {

        if (object instanceof GearItemData) {
            GearItemData gear = (GearItemData) object;
            if (gear.getGearEnum().canGetAffixes()) {
                return true;
            }
        }

        return false;
    }

}
