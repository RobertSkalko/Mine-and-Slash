package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.BaseRaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.containers.GearRarities;
import com.robertx22.mine_and_slash.database.requirements.GearRequestedFor;
import com.robertx22.mine_and_slash.loot.blueprints.bases.GearItemSlotPart;
import com.robertx22.mine_and_slash.loot.blueprints.bases.SetPart;
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
    private SetPart set;
    public float chaosStatChance = 1;

    public SetPart getSet(GearItemData gear) {
        this.set = new SetPart(this, new GearRequestedFor(gear));
        return set;

    }

    @Override
    public BaseRaritiesContainer<? extends Rarity> getRarityContainer() {

        return new GearRarities();

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

}
