package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.containers.ItemRarities;
import com.robertx22.mine_and_slash.database.requirements.GearRequestedFor;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.db_lists.initializers.Sets;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

public class GearBlueprint extends ItemBlueprint {

    public GearBlueprint(int level) {
        super(level);
    }

    public String gearType = "";
    public boolean RandomGearType = true;
    public float chaosStatChance = 1;

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {

        return new ItemRarities();

    }

    public boolean getsChaosStats() {
        return RandomUtils.roll(chaosStatChance);
    }

    public void SetSpecificType(String type) {

        gearType = type;
        RandomGearType = false;

        try {
            SlashRegistry.GearTypes().get(type);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    public GearItemSlot GetGearType() {

        if (RandomGearType) {
            return RandomUtils.weightedRandom(SlashRegistry.GearTypes().getList());

        } else {

            return SlashRegistry.GearTypes().get(gearType);
        }

    }

    public boolean isCustomSetChance = false;
    public float customSetChance = 0;

    public void SetCustomSetChance(float chance) {
        isCustomSetChance = true;
        customSetChance = chance;
    }

    public boolean canGetSet(GearItemData data) {

        Set set = Sets.INTANCE.random(new GearRequestedFor(data));

        if (set == null) {
            return false;
        } else {
            if (this.isCustomSetChance) {

                if (RandomUtils.roll(this.customSetChance)) {
                    return true;
                }

            } else {
                if (RandomUtils.roll(data.getRarity().SetChance())) {
                    return true;
                }
            }

            return false;
        }
    }

}
