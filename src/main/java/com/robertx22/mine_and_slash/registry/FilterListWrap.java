package com.robertx22.mine_and_slash.registry;

import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.items.currency.CurrencyItem;
import com.robertx22.mine_and_slash.database.requirements.bases.GearRequestedFor;
import com.robertx22.mine_and_slash.db_lists.bases.IhasRequirements;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.comparators.RarityComparator;
import com.robertx22.mine_and_slash.uncommon.datasaving.ItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IGearSlotType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterListWrap<C extends ISlashRegistryEntry> {

    public FilterListWrap(List<C> list) {
        this.list = list;
    }

    public FilterListWrap(Collection<C> list) {
        this.list = new ArrayList<C>(list);
    }

    private boolean errorIfNothingLeft = true;

    public FilterListWrap<C> errorIfNothingLeft(boolean bool) {
        this.errorIfNothingLeft = bool;
        return this;
    }

    public FilterListWrap<C> ofAffixType(BaseAffix.Type type) {
        this.list = list.stream()
            .filter(x -> ((BaseAffix) x).type.equals(type))
            .collect(Collectors.toList());
        return this;
    }

    public List<C> list = new ArrayList<C>();

    public FilterListWrap<C> ofTierOrLess(int tier) {
        this.list = list.stream()
            .filter(x -> ((ITiered) x).Tier() <= tier)
            .collect(Collectors.toList());
        return this;
    }

    public FilterListWrap<C> of(Predicate<C> pred) {
        this.list = list.stream()
            .filter(pred)
            .collect(Collectors.toList());
        return this;
    }

    public FilterListWrap<C> ofCurrencyUsableOnItemType(ItemType type) {
        this.list = list.stream()
            .filter(x -> ((CurrencyItem) x).itemTypesUsableOn == type)
            .collect(Collectors.toList());
        return this;
    }

    public FilterListWrap<C> ofTierRange(int min, int max) {
        this.list = list.stream()
            .filter(x -> x.Tier() >= min && x.Tier() <= max)
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

    public FilterListWrap<C> ofHighestRarity() {

        final int highest;

        Optional<C> optional = list.stream()
            .max(new RarityComparator());

        if (optional.isPresent()) {
            highest = optional.get()
                .getRarityRank();

            this.list = list.stream()
                .filter(x -> ((IRarity) x).getRarityRank() == highest)
                .collect(Collectors.toList());
        } else {
            this.list.clear();
        }
        return this;
    }

    public FilterListWrap<C> ofSpecificGearType(String type) {

        if (type.isEmpty() || type.equals("random")) {
            // keep everything the same
        } else {
            this.list = list.stream()
                .filter(x -> ((IGearSlotType) x).getGearSlot()
                    .GUID()
                    .equals(type))
                .collect(Collectors.toList());
        }

        return this;
    }

    public FilterListWrap<C> randomAmountWithoutDuplicates(int amount) {

        HashSet<C> set = new HashSet<>();

        if (amount > list.size()) {

            MMORPG.logError("ERROR! Can't have more random items than there are items in registry!");

            amount = SlashRegistry.StatusEffects()
                .getSize() - 1;
        }
        int tries = 0;

        while (set.size() < amount && tries < 5) {
            set.add(random());
            tries++;
        }

        this.list = new ArrayList<>(set);
        return this;
    }

    public FilterListWrap<C> randomAmount(int amount) {

        List<C> list = new ArrayList<>();

        int tries = 0;
        while (list.size() < amount && tries < 20) {
            list.add(random());
            tries++;
        }
        this.list = list;
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

        if (this.list.isEmpty()) {
            if (errorIfNothingLeft) {
                try {
                    throw new Exception("Items filtered too much, no possibility left, returning null!");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                MMORPG.devToolsLog("Items filtered too much, no possibility left, returning null!");

            }
            return null;

        }

        return RandomUtils.weightedRandom(list);
    }

}
