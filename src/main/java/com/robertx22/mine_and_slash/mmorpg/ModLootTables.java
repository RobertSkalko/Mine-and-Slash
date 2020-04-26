package com.robertx22.mine_and_slash.mmorpg;

import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.functions.EnchantRandomly;
import net.minecraft.world.storage.loot.functions.SetCount;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {

    public static final ResourceLocation TIER_1_DUNGEON_CHEST = new ResourceLocation(Ref.MODID, "chests/tier_1_dungeon");
    public static final ResourceLocation TIER_2_DUNGEON_CHEST = new ResourceLocation(Ref.MODID, "chests/tier_2_dungeon");
    public static final ResourceLocation TIER_3_DUNGEON_CHEST = new ResourceLocation(Ref.MODID, "chests/tier_3_dungeon");
    public static final ResourceLocation TIER_4_DUNGEON_CHEST = new ResourceLocation(Ref.MODID, "chests/tier_4_dungeon");
    public static final ResourceLocation TIER_5_DUNGEON_CHEST = new ResourceLocation(Ref.MODID, "chests/tier_5_dungeon");

    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {

        consumer.accept(ModLootTables.TIER_1_DUNGEON_CHEST, LootTable.builder()
            .addLootPool(LootPool.builder()
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(Items.BOOK)
                    .weight(10)
                    .acceptFunction(EnchantRandomly.func_215900_c()))
                .addEntry(EmptyLootEntry.func_216167_a()
                    .weight(5)))
            .addLootPool(LootPool.builder()
                .rolls(RandomValueRange.of(2.0F, 4.0F))
                .addEntry(ItemLootEntry.builder(Items.IRON_NUGGET)
                    .weight(10)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4))))
                .addEntry(ItemLootEntry.builder(Items.REDSTONE)
                    .weight(5)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(2, 6))))
                .addEntry(ItemLootEntry.builder(Items.COAL)
                    .weight(10)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(2, 6))))
            )
        );

        consumer.accept(ModLootTables.TIER_2_DUNGEON_CHEST, LootTable.builder()
            .addLootPool(LootPool.builder()
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(Items.BOOK)
                    .weight(15)
                    .acceptFunction(EnchantRandomly.func_215900_c()))
                .addEntry(EmptyLootEntry.func_216167_a()
                    .weight(5)))
            .addLootPool(LootPool.builder()
                .rolls(RandomValueRange.of(1, 2))
                .addEntry(ItemLootEntry.builder(Items.BREAD)
                    .weight(10)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5.0F))))
                .addEntry(EmptyLootEntry.func_216167_a()
                    .weight(50)))
            .addLootPool(LootPool.builder()
                .rolls(RandomValueRange.of(2.0F, 4.0F))
                .addEntry(ItemLootEntry.builder(Items.IRON_INGOT)
                    .weight(10)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2))))
                .addEntry(ItemLootEntry.builder(Items.REDSTONE)
                    .weight(5)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                .addEntry(ItemLootEntry.builder(Items.COAL)
                    .weight(10)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 8.0F))))
            )

        );

        consumer.accept(ModLootTables.TIER_3_DUNGEON_CHEST, LootTable.builder()
            .addLootPool(LootPool.builder()
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(Items.BOOK)
                    .weight(20)
                    .acceptFunction(EnchantRandomly.func_215900_c()))
                .addEntry(EmptyLootEntry.func_216167_a()
                    .weight(5)))
            .addLootPool(LootPool.builder()
                .rolls(RandomValueRange.of(1, 2))
                .addEntry(ItemLootEntry.builder(Items.COOKED_COD)
                    .weight(10)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5.0F))))
                .addEntry(EmptyLootEntry.func_216167_a()
                    .weight(50)))
            .addLootPool(LootPool.builder()
                .rolls(RandomValueRange.of(2.0F, 4.0F))
                .addEntry(ItemLootEntry.builder(Items.GOLD_INGOT)
                    .weight(10)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2))))
                .addEntry(ItemLootEntry.builder(Items.IRON_INGOT)
                    .weight(5)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1, 3))))
            )

        );

        consumer.accept(ModLootTables.TIER_4_DUNGEON_CHEST, LootTable.builder()
            .addLootPool(LootPool.builder()
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(Items.BOOK)
                    .weight(25)
                    .acceptFunction(EnchantRandomly.func_215900_c()))
                .addEntry(EmptyLootEntry.func_216167_a()
                    .weight(5)))
            .addLootPool(LootPool.builder()
                .rolls(RandomValueRange.of(1, 2))
                .addEntry(ItemLootEntry.builder(Items.COOKED_BEEF)
                    .weight(10)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4))))
                .addEntry(ItemLootEntry.builder(Items.LEATHER)
                    .weight(5)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5))))
                .addEntry(EmptyLootEntry.func_216167_a()
                    .weight(50)))
            .addLootPool(LootPool.builder()
                .rolls(RandomValueRange.of(1, 2))
                .addEntry(ItemLootEntry.builder(Items.DIAMOND)
                    .weight(5)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2))))
                .addEntry(ItemLootEntry.builder(Items.GOLD_INGOT)
                    .weight(15)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1, 3))))
            )

        );

        consumer.accept(ModLootTables.TIER_5_DUNGEON_CHEST, LootTable.builder()
            .addLootPool(LootPool.builder()
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(Items.BOOK)
                    .weight(50)
                    .acceptFunction(EnchantRandomly.func_215900_c()))
                .addEntry(EmptyLootEntry.func_216167_a()
                    .weight(5)))
            .addLootPool(LootPool.builder()
                .rolls(RandomValueRange.of(1, 3))
                .addEntry(ItemLootEntry.builder(Items.COOKED_BEEF)
                    .weight(10)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4))))
                .addEntry(EmptyLootEntry.func_216167_a()
                    .weight(50)))
            .addLootPool(LootPool.builder()
                .rolls(RandomValueRange.of(1, 4))
                .addEntry(ItemLootEntry.builder(Items.DIAMOND)
                    .weight(7)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2))))
                .addEntry(ItemLootEntry.builder(Items.GOLD_INGOT)
                    .weight(15)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1, 5))))
                .addEntry(ItemLootEntry.builder(Items.EMERALD)
                    .weight(10)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1, 6))))
            )
            .addLootPool(LootPool.builder()
                .rolls(RandomValueRange.of(1, 2))
                .addEntry(ItemLootEntry.builder(Items.ENDER_PEARL)
                    .weight(10)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2))))
                .addEntry(ItemLootEntry.builder(Items.EXPERIENCE_BOTTLE)
                    .weight(15)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1, 3))))
                .addEntry(ItemLootEntry.builder(Items.GHAST_TEAR)
                    .weight(5)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(1, 2))))
            )

        );

    }

}
