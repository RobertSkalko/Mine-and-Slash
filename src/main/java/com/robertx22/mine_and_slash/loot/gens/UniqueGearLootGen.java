package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.LootUtils;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import com.robertx22.mine_and_slash.saveclasses.gearitem.GearItemEnum;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.item.ItemStack;

public class UniqueGearLootGen extends BaseLootGen {

    public UniqueGearLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.UNIQUE_DROPRATE.get().floatValue();
    }

    @Override
    public LootType lootType() {
        return LootType.UniqueItem;
    }

    @Override
    public boolean condition() {
        return WorldUtils.dropsUniques(info.world);
    }

    @Override
    public ItemStack generateOne() {

        UniqueGearBlueprint gearPrint = new UniqueGearBlueprint(info.level, info.tier, true);

        ItemStack stack = CreateStack(gearPrint);

        GearItemData gear = Gear.Load(stack);

        return LootUtils.RandomDamagedGear(stack, gear.getRarity());

    }

    public static GearItemData CreateData(UniqueGearBlueprint blueprint) {
        return GearLootGen.CreateData(blueprint, GearItemEnum.UNIQUE);
    }

    public static ItemStack CreateStack(UniqueGearBlueprint schema) {

        GearItemData data = CreateData(schema);

        if (data != null && data.getItem() != null) {
            ItemStack stack = new ItemStack(data.getItem());

            Gear.Save(stack, data);

            return stack;
        }
        return ItemStack.EMPTY;
    }

    public static ItemStack CreateStack(GearItemData data) {

        ItemStack stack = new ItemStack(data.getItem());

        Gear.Save(stack, data);

        return stack;

    }

}
