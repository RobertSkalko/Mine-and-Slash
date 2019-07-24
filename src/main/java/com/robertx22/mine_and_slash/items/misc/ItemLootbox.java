package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.items.BaseItem;
import com.robertx22.mine_and_slash.items.ItemDefault;
import com.robertx22.mine_and_slash.items.currency.CurrencyItem;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.RunedGearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.SpellBlueprint;
import com.robertx22.mine_and_slash.loot.gens.CompatibleItemLootGen;
import com.robertx22.mine_and_slash.loot.gens.GearLootGen;
import com.robertx22.mine_and_slash.loot.gens.RunedGearLootGen;
import com.robertx22.mine_and_slash.loot.gens.SpellLootGen;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.FireworkRocketEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemLootbox extends BaseItem implements IWeighted, IAutoLocName {
    public static HashMap<String, ItemLootbox> Items = new HashMap<String, ItemLootbox>();

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Lootboxes;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public String locNameForLangFile() {
        return Rarities.Items.get(rarity).textFormatColor() + Rarities.Items.get(rarity)
                .locNameForLangFile() + " " + this.size.name() + " " + this.lootType.name() + " " + "Treasure Box";
    }

    @Override
    public String GUID() {
        return this.getRegistryName().toString();
    }

    public enum LootTypes {
        Gear,
        Spell,
        Currency
    }

    public enum LootBoxSizes {
        Small(2F),
        Medium(1F),
        Big(0.3F);

        LootBoxSizes(float mult) {
            this.weightMult = mult;
        }

        float weightMult;
    }

    public enum GearType {
        Runed,
        Normal,
        Compatible
    }

    public static Item GetItem(int rarity, LootTypes type, LootBoxSizes size) {
        String key = GetStringForType(rarity, type, size);

        if (Items.containsKey(key)) {
            return Items.get(key);
        }

        return null;
    }

    public static String GetStringForType(int rarity, LootTypes type, LootBoxSizes size) {

        String reg = "lootbox/" + size.toString() + "_" + type.toString() + "_lootbox_" + rarity;

        return reg.toLowerCase();

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

        super(new ItemDefault().group(CreativeTabs.LootBoxes));

        this.size = size;
        this.rarity = rarity;
        this.lootType = type;

        this.setWeight();

    }

    private void setWeight() {

        int base = Rarities.Items.get(rarity).Weight() + 5000;
        //still important, but not that much

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

        if (ModConfig.INSTANCE.Server.USE_COMPATIBILITY_ITEMS.get()) {
            types.add(new GenWeight(GearType.Compatible, (int) (ModConfig.INSTANCE.DropRates.COMPATIBLE_ITEMS_DROPRATE
                    .get()
                    .floatValue()) * 100));
        }
        types.add(new GenWeight(GearType.Runed, (int) (ModConfig.INSTANCE.DropRates.RUNED_GEAR_DROPRATE
                .get()
                .floatValue()) * 100));

        types.add(new GenWeight(GearType.Normal, (int) (ModConfig.INSTANCE.DropRates.GEAR_DROPRATE
                .get()
                .floatValue()) * 100));

        GenWeight winner = (GenWeight) RandomUtils.weightedRandom(types);

        return winner.gen;

    }

    private void GiveItems(PlayerEntity player, int lvl) {

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

                    stacks.add(RunedGearLootGen.CreateStack(print));

                } else {
                    GearBlueprint print = new GearBlueprint(lvl);
                    print.minRarity = this.rarity;
                    print.LevelRange = false;

                    stacks.add(GearLootGen.CreateStack(print));
                }
            }

        } else if (lootType.equals(LootTypes.Spell)) {

            SpellBlueprint print = new SpellBlueprint(lvl);
            print.minRarity = this.rarity;
            print.LevelRange = false;

            for (int i = 0; i < this.ItemAmount.get(this.size); i++) {
                stacks.add(SpellLootGen.Create(print));
            }

        } else if (lootType.equals(LootTypes.Currency)) {

            for (int i = 0; i < this.ItemAmount.get(this.size) + rarity; i++) {

                CurrencyItem item = (CurrencyItem) RandomUtils.weightedRandom(CurrencyItem.ITEMS);
                stacks.add(new ItemStack(item));
            }

        }

        for (ItemStack stack : stacks) {
            player.dropItem(stack, false);
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn,
                                                    Hand handIn) {

        FireworkRocketEntity firework = new FireworkRocketEntity(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, ItemStack.EMPTY);
        firework.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
        WorldUtils.spawnEntity(worldIn, firework);

        if (!worldIn.isRemote) {
            try {
                UnitData data = Load.Unit(playerIn);

                if (data != null) {

                    int lvl = data.getLevel();

                    GiveItems(playerIn, lvl);

                    return new ActionResult<ItemStack>(ActionResultType.PASS, EmptyOrDecrease(playerIn
                            .getHeldItem(handIn)));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<ItemStack>(ActionResultType.PASS, playerIn.getHeldItem(handIn));
    }

    public String Name() {
        return "LootBox";
    }

    @Override
    public int Weight() {
        return weight;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        tooltip.add(Styles.GREENCOMP().appendSibling(CLOC.tooltip("lootbox")));

    }

}