package com.robertx22.mine_and_slash.loot.gens.gears;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.LootUtils;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.gens.BaseLootGen;
import com.robertx22.mine_and_slash.loot.gens.util.GearCreationUtils;
import com.robertx22.mine_and_slash.saveclasses.gearitem.GearItemEnum;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import net.minecraft.item.ItemStack;

public class GearLootGen extends BaseLootGen {

    public GearLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float baseDropChance() {
        return ModConfig.INSTANCE.DropRates.GEAR_DROPRATE.get().floatValue();
    }

    @Override
    public LootType lootType() {
        return LootType.NormalItem;
    }

    @Override
    public ItemStack generateOne() {

        GearBlueprint gearPrint = new GearBlueprint(info.level);

        ItemStack stack = GearCreationUtils.CreateStack(gearPrint);

        GearItemData gear = Gear.Load(stack);

        return LootUtils.RandomDamagedGear(stack, gear.getRarity(), gear.level);

    }

    public static GearItemData CreateData(GearBlueprint blueprint) {
        return GearCreationUtils.CreateData(blueprint, GearItemEnum.NORMAL);
    }

}
