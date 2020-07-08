package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.BaseRaritiesContainer;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.bases.AffixChancePart;
import com.robertx22.mine_and_slash.loot.blueprints.bases.GearItemSlotPart;
import com.robertx22.mine_and_slash.loot.blueprints.bases.UnidentifiedPart;
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

    public GearBlueprint(LootInfo info) {
        super(info);
        actionsAfterGeneration.add(DamagedGear.INSTANCE);
    }

    public GearBlueprint(int level, int tier) {
        super(level, tier);
        actionsAfterGeneration.add(DamagedGear.INSTANCE);
    }

    public GearItemSlotPart gearItemSlot = new GearItemSlotPart(this);

    public float chaosStatChance = 1;
    public UnidentifiedPart unidentifiedPart = new UnidentifiedPart(this);
    public AffixChancePart prefixChancePart = new AffixChancePart(this);
    public AffixChancePart suffixChancePart = new AffixChancePart(this);

    @Override
    public BaseRaritiesContainer<? extends Rarity> getRarityContainer() {
        return Rarities.Gears;
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
