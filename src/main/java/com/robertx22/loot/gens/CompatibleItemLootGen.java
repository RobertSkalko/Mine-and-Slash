package com.robertx22.loot.gens;

import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.mmorpg.config.non_mine_items.ConfigItem;
import com.robertx22.mmorpg.config.non_mine_items.ConfigItems;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CompatibleItemLootGen extends BaseLootGen {

  EntityData.UnitData mob;

  int level = 1;

  public CompatibleItemLootGen(EntityData.UnitData mob, EntityData.UnitData player,
      IWorldData world, EntityLivingBase victim) {
    super(mob, player, world, victim);

    this.mob = mob;

    level = mob.getLevel();
  }

  public CompatibleItemLootGen(World theworld, float multi, IWorldData world, int level) {
    super(multi, world);
    this.level = level;
  }

  @Override
  public float BaseChance() {
    return ModConfig.DropRates.COMPATIBLE_ITEMS_DROPRATE;
  }

  @Override
  public ItemStack generateOne() {

    if (ModConfig.Server.USE_COMPATIBILITY_ITEMS == true) {


      return gen(level);

    }

    return ItemStack.EMPTY;



  }


  public static ItemStack gen(int level) {

    ConfigItem config = (ConfigItem) RandomUtils
        .WeightedRandom(ListUtils.CollectionToList(ConfigItems.INSTANCE.getAll()));

    ResourceLocation res = new ResourceLocation(config.registryName);

    if (ForgeRegistries.ITEMS.containsKey(res)) {

      ItemStack stack = new ItemStack(ForgeRegistries.ITEMS.getValue(res));

      return config.create(stack, level);
    }
    return ItemStack.EMPTY;
  }

}
