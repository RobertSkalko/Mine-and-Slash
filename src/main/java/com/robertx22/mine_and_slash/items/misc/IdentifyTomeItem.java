package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.mmorpg.registers.common.RecipeRegisters;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class IdentifyTomeItem extends Item implements IShapedRecipe {
    public IdentifyTomeItem() {
        super(new Properties().maxStackSize(64)
            .group(CreativeTabs.MyModTab));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {

        if (!world.isRemote) {
            try {

                ItemStack tome = player.getHeldItem(hand);

                if (tome.getItem() instanceof IdentifyTomeItem) {

                    for (ItemStack x : player.inventory.mainInventory) {
                        GearItemData gear = Gear.Load(x);

                        if (gear != null) {

                            if (!gear.isIdentified()) {

                                gear.setIdentified(true);

                                Gear.Save(x, gear);

                                tome.shrink(1);

                                return new ActionResult<ItemStack>(ActionResultType.CONSUME, tome);
                            }

                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.IDENTIFY_TOME.get(), 16)
            .key('b', Items.BOOK)
            .key('v', Items.REDSTONE)
            .key('o', ItemOre.ItemOres.get(IRarity.Rare))
            .patternLine("oo")
            .patternLine("bv")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(1));
    }

    public static class Recipe extends SpecialRecipe {

        public Recipe(ResourceLocation loc) {
            super(loc);
        }

        public ItemStack getStack(CraftingInventory inv, Predicate<ItemStack> co) {
            for (int i = 0; i < inv.getSizeInventory(); ++i) {
                ItemStack stack = inv.getStackInSlot(i);
                if (!stack.isEmpty()) {

                    if (co.test(stack)) {
                        return stack;
                    }

                }
            }
            return ItemStack.EMPTY;
        }

        @Override
        public boolean matches(CraftingInventory inv, World worldIn) {

            ItemStack gearstack = getStack(inv, x -> Gear.has(x));
            ItemStack tomestack = getStack(inv, x -> x.getItem()
                .equals(ModItems.IDENTIFY_TOME.get()));

            GearItemData gear = Gear.Load(gearstack);

            return gear != null && !gear.isIdentified() && !tomestack.isEmpty();
        }

        @Override
        public ItemStack getCraftingResult(CraftingInventory inv) {

            ItemStack gearstack = getStack(inv, x -> Gear.has(x));
            ItemStack tomestack = getStack(inv, x -> x.getItem()
                .equals(ModItems.IDENTIFY_TOME.get()));

            GearItemData gear = Gear.Load(gearstack);

            gear.setIdentified(true);

            Gear.Save(gearstack, gear);
            tomestack.shrink(1);

            return gearstack;
        }

        @Override
        public boolean canFit(int width, int height) {
            return width >= 1 && height >= 1;
        }

        static SpecialRecipeSerializer<Recipe> SER = IRecipeSerializer.register("test", new SpecialRecipeSerializer<>(Recipe::new));

        @Override
        public IRecipeSerializer<?> getSerializer() {
            return RecipeRegisters.IDENTIFY;
        }
    }

}
