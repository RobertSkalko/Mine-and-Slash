package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.containers.ItemRarities;
import com.robertx22.mine_and_slash.database.requirements.GearRequestedFor;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.db_lists.initializers.Sets;
import com.robertx22.mine_and_slash.loot.blueprints.bases.GearItemSlotPart;
import com.robertx22.mine_and_slash.loot.gens.stack_changers.DamagedGear;
import com.robertx22.mine_and_slash.loot.gens.util.GearCreationUtils;
import com.robertx22.mine_and_slash.saveclasses.gearitem.GearItemEnum;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

public class GearBlueprint extends ItemBlueprint {

    public GearBlueprint(int level) {
        super(level);
        actionsAfterGeneration.add(DamagedGear.INSTANCE);
    }

    public GearItemSlotPart gearItemSlot = new GearItemSlotPart(this);

    public float chaosStatChance = 1;

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {

        return new ItemRarities();

    }

    public boolean getsChaosStats() {
        return RandomUtils.roll(chaosStatChance);
    }

    public GearItemData createData() {
        return GearCreationUtils.CreateData(this, GearItemEnum.NORMAL);
    }

    @Override
    ItemStack generate() {
        return GearCreationUtils.CreateStack(createData());
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
