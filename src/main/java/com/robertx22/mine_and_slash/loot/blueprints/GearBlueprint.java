package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.BaseRaritiesContainer;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.bases.*;
import com.robertx22.mine_and_slash.loot.gens.stack_changers.DamagedGear;
import com.robertx22.mine_and_slash.loot.gens.util.GearCreationUtils;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.GearItemEnum;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import net.minecraft.item.ItemStack;

public class GearBlueprint extends ItemBlueprint {

    public GearBlueprint(int level) {
        super(level, 0);
        actionsAfterGeneration.add(DamagedGear.INSTANCE);
    }

    public GearBlueprint(LootInfo info) {
        super(info);
        actionsAfterGeneration.add(DamagedGear.INSTANCE);
    }

    public GearBlueprint(int level, int tier) {
        super(level, tier);
        actionsAfterGeneration.add(DamagedGear.INSTANCE);
    }

    public GearItemSlotPart gearItemSlot = new GearItemSlotPart(this);
    public UnidentifiedPart unidentifiedPart = new UnidentifiedPart(this);
    public AffixChancePart affixChancePart = new AffixChancePart(this);
    public UniqueGearPart uniquePart = new UniqueGearPart(this);
    public IsUniquePart isUniquePart = new IsUniquePart(this);

    @Override
    public BaseRaritiesContainer<? extends Rarity> getRarityContainer() {
        return Rarities.Gears;
    }

    public GearItemData createData() {
        return GearCreationUtils.CreateData(info, this, GearItemEnum.NORMAL);
    }

    @Override
    ItemStack generate() {
        return GearCreationUtils.CreateStack(createData());
    }

}
