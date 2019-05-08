package com.robertx22.loot.gens;

import com.robertx22.customitems.lootboxes.ItemLootbox;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LootBoxGen extends BaseLootGen {

  public LootBoxGen(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {
    super(mob, player, world, victim);

  }



  public LootBoxGen(float multi, IWorldData world) {
    super(multi, world);
  }



  @Override
  public float BaseChance() {
    return ModConfig.DropRates.LOOTBOX_DROPRATE;

  }



  @Override
  public ItemStack generateOne() {

    return new ItemStack(
        (Item) RandomUtils.WeightedRandom(ListUtils.CollectionToList(ItemLootbox.Items.values())));

  }

}
