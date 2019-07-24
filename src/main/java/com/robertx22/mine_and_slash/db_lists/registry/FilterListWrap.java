package com.robertx22.mine_and_slash.db_lists.registry;

import com.robertx22.mine_and_slash.database.requirements.GearRequestedFor;
import com.robertx22.mine_and_slash.db_lists.bases.IhasRequirements;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IGearSlotType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FilterListWrap<C extends ISlashRegistryEntry> {

    public FilterListWrap(List<C> list) {
        this.list = list;
    }

    public FilterListWrap(Collection<C> list) {
        this.list = new ArrayList<C>(list);
    }

    public List<C> list = new ArrayList<C>();

    public FilterListWrap<C> ofTierOrLess(int tier) {
        this.list = list.stream()
                .filter(x -> ((ITiered) x).Tier() <= tier)
                .collect(Collectors.toList());
        return this;
    }

    public FilterListWrap<C> ofExactTier(int tier) {
        this.list = list.stream()
                .filter(x -> ((ITiered) x).Tier() == tier)
                .collect(Collectors.toList());

        return this;
    }

    public FilterListWrap<C> ofExactRarity(int rarity) {
        this.list = list.stream()
                .filter(x -> ((IRarity) x).getRarityRank() == rarity)
                .collect(Collectors.toList());
        return this;
    }

    public FilterListWrap<C> ofSpecificGearType(String type) {
        this.list = list.stream()
                .filter(x -> ((IGearSlotType) x).getGearSlot()
                        .equals(type) || type.isEmpty() || type.equals("random"))
                .collect(Collectors.toList());
        return this;
    }

    public FilterListWrap<C> allThatMeetRequirement(GearItemData gear) {
        return this.allThatMeetRequirement(new GearRequestedFor(gear));
    }

    public FilterListWrap<C> allThatMeetRequirement(GearRequestedFor request) {

        this.list = list.stream()
                .filter(x -> ((IhasRequirements) x).meetsRequirements(request))
                .collect(Collectors.toList());

        return this;
    }

    public C random() {
        return RandomUtils.weightedRandom(list);
    }

}
