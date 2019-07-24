package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.LootUtils;
import com.robertx22.mine_and_slash.loot.blueprints.RunedGearBlueprint;
import com.robertx22.mine_and_slash.saveclasses.gearitem.GearItemEnum;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import net.minecraft.item.ItemStack;

public class RunedGearLootGen extends BaseLootGen {

    public RunedGearLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.RUNED_GEAR_DROPRATE.get().floatValue();
    }

    @Override
    public LootType lootType() {
        return LootType.RunedItem;
    }

    @Override
    public ItemStack generateOne() {

        RunedGearBlueprint gearPrint = new RunedGearBlueprint(info.level);

        ItemStack stack = CreateStack(gearPrint);

        GearItemData gear = Gear.Load(stack);

        return LootUtils.RandomDamagedGear(stack, gear.getRarity());

    }

    public static GearItemData CreateData(RunedGearBlueprint blueprint) {
        return GearLootGen.CreateData(blueprint, GearItemEnum.RUNED);
    }

    public static ItemStack CreateStack(RunedGearBlueprint schema) {

        GearItemData data = CreateData(schema);

        ItemStack stack = new ItemStack(data.getItem());

        Gear.Save(stack, data);

        return stack;

    }

    public static ItemStack CreateStack(GearItemData data) {

        ItemStack stack = new ItemStack(data.getItem());

        Gear.Save(stack, data);

        return stack;

    }
}
