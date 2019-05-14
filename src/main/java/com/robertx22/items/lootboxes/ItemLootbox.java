package com.robertx22.items.lootboxes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.CreativeTabList;
import com.robertx22.db_lists.Rarities;
import com.robertx22.generation.GearGen;
import com.robertx22.generation.RunedGearGen;
import com.robertx22.generation.SpellItemGen;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.generation.blueprints.RunedGearBlueprint;
import com.robertx22.generation.blueprints.SpellBlueprint;
import com.robertx22.items.BaseItem;
import com.robertx22.items.currency.CurrencyItem;
import com.robertx22.loot.gens.CompatibleItemLootGen;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ItemLootbox extends BaseItem implements IWeighted {
  public static HashMap<String, Item> Items = new HashMap<String, Item>();

  public enum LootTypes {
    Gear, Spell, Currency



  }
  public enum LootBoxSizes {
    Small(2F), Medium(1F), Big(0.3F);

    LootBoxSizes(float mult) {
      this.weightMult = mult;
    }

    float weightMult;
  }

  public enum GearType {
    Runed, Normal, Compatible
  }



  public static Item GetItem(int rarity, LootTypes type, LootBoxSizes size) {
    String key = GetStringForType(rarity, type, size);

    if (Items.containsKey(key)) {
      return Items.get(key);
    }

    return null;
  }

  private static String GetStringForType(int rarity, LootTypes type, LootBoxSizes size) {

    return size.toString() + "_" + type.toString() + "_lootbox_" + rarity;

  }

  private static HashMap<LootBoxSizes, Integer> ItemAmount = new HashMap<LootBoxSizes, Integer>() {
    {
      {
        put(LootBoxSizes.Small, 2);
        put(LootBoxSizes.Medium, 4);
        put(LootBoxSizes.Big, 6);

      }
    }
  };



  public int weight = 1;
  public LootBoxSizes size;
  public int rarity;
  public LootTypes lootType;

  public ItemLootbox(LootBoxSizes size, LootTypes type, int rarity) {
    this.setMaxStackSize(64);
    this.setMaxDamage(0);
    this.setCreativeTab(CreativeTabList.LootboxTab);
    this.setUnlocalizedName(GetStringForType(rarity, type, size));
    this.setRegistryName(GetStringForType(rarity, type, size));

    this.size = size;
    this.rarity = rarity;
    this.lootType = type;



    Items.put(GetStringForType(rarity, type, size), this);

  }

  private void setWeight() {

    int base = Rarities.Items.get(rarity).Weight();

    base *= this.size.weightMult;

    this.weight = base;
  }



  static class GenWeight implements IWeighted {
    public GenWeight(GearType gen, int weight) {
      this.gen = gen;
      this.weight = weight;
    }

    GearType gen = GearType.Normal;
    int weight = 1;

    @Override
    public int Weight() {
      return weight;
    }
  }


  private GearType getRandomType() {

    List<GenWeight> types = new ArrayList();

    if (ModConfig.Server.USE_COMPATIBILITY_ITEMS) {
      types.add(new GenWeight(GearType.Compatible,
          (int) (ModConfig.DropRates.COMPATIBLE_ITEMS_DROPRATE) * 100));
    }
    types.add(new GenWeight(GearType.Runed, (int) (ModConfig.DropRates.RUNED_GEAR_DROPRATE) * 100));
    types.add(new GenWeight(GearType.Normal, (int) (ModConfig.DropRates.GEAR_DROPRATE) * 100));

    GenWeight winner = (GenWeight) RandomUtils.WeightedRandom(ListUtils.CollectionToList(types));

    return winner.gen;


  }


  private void GiveItems(EntityPlayer player, int lvl) {

    List<ItemStack> stacks = new ArrayList<ItemStack>();

    if (lootType.equals(LootTypes.Gear)) {


      for (int i = 0; i < this.ItemAmount.get(this.size); i++) {

        GearType type = getRandomType();

        if (type.equals(GearType.Compatible)) {

          stacks.add(CompatibleItemLootGen.gen(lvl));

        } else if (type.equals(GearType.Runed)) {

          RunedGearBlueprint print = new RunedGearBlueprint(lvl);
          print.minRarity = this.rarity;
          print.LevelRange = false;

          stacks.add(RunedGearGen.CreateStack(print));

        } else {
          GearBlueprint print = new GearBlueprint(lvl);
          print.minRarity = this.rarity;
          print.LevelRange = false;

          stacks.add(GearGen.CreateStack(print));
        }
      }

    } else if (lootType.equals(LootTypes.Spell)) {

      SpellBlueprint print = new SpellBlueprint(lvl);
      print.minRarity = this.rarity;
      print.LevelRange = false;

      for (int i = 0; i < this.ItemAmount.get(this.size); i++) {
        stacks.add(SpellItemGen.Create(print));
      }

    } else if (lootType.equals(LootTypes.Currency)) {

      for (int i = 0; i < this.ItemAmount.get(this.size) + rarity; i++) {

        CurrencyItem item = (CurrencyItem) RandomUtils
            .WeightedRandom(ListUtils.CollectionToList(CurrencyItem.ITEMS));
        stacks.add(new ItemStack(item));
      }

    }

    for (ItemStack stack : stacks) {
      player.dropItem(stack, false);
    }
  }

  @SubscribeEvent
  public static void registerItems(RegistryEvent.Register<Item> event) {

    for (ItemRarity rarity : Rarities.Items) {
      for (LootTypes type : LootTypes.values()) {
        for (LootBoxSizes size : LootBoxSizes.values()) {
          new ItemLootbox(size, type, rarity.Rank());
        }
      }

    }

    Items.values().forEach((x) -> event.getRegistry().register(x));

  }

  @SubscribeEvent
  public static void onModelRegistry(ModelRegistryEvent event) {
    Items.values().forEach((x) -> RegisterUtils.registerRender(x));

  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn,
      EnumHand handIn) {

    EntityFireworkRocket firework = new EntityFireworkRocket(worldIn);
    firework.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
    worldIn.spawnEntity(firework);

    if (!worldIn.isRemote) {
      try {
        UnitData data = Load.Unit(playerIn);

        if (data != null) {

          int lvl = data.getLevel();

          GiveItems(playerIn, lvl);

          return new ActionResult<ItemStack>(EnumActionResult.PASS,
              EmptyOrDecrease(playerIn.getHeldItem(handIn)));

        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
  }

  public String Name() {
    return "LootBox";
  }

  @Override
  public int Weight() {
    return weight;
  }
}
