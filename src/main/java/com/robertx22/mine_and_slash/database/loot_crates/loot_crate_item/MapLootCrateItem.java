package com.robertx22.mine_and_slash.database.loot_crates.loot_crate_item;

import com.robertx22.mine_and_slash.database.loot_crates.LootCrate;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Tooltip;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nullable;
import java.util.List;

public class MapLootCrateItem extends Item {

    @ObjectHolder(Ref.MODID + ":loot_crate")
    public static final Item ITEM = null;

    public MapLootCrateItem() {
        super(getProp());

        RegisterItemUtils.RegisterItemName(this, "loot_crate");

    }

    @Override
    public String getHighlightTip(ItemStack stack, String displayName) {
        return getCrate(stack).name().translate();
    }

    public static Properties getProp() {
        Properties prop = new Properties().maxStackSize(1);

        prop.setTEISR(LootCrateRenderer.INSTANCE);

        return prop;

    }

    static String LVL = "LVL", SCORE = "SCORE", ID = "REGISTRY_ID", TIER = "TIER";

    public static ItemStack getStack(LootCrate crate, int lvl, int mapTier, int score) {

        ItemStack stack = new ItemStack(ITEM);

        if (!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
        }

        stack.getTag().putInt(LVL, lvl);
        stack.getTag().putInt(TIER, mapTier);
        stack.getTag().putString(ID, crate.GUID());
        stack.getTag().putInt(SCORE, score);

        return stack;
    }

    public LootCrate getCrate(ItemStack stack) {
        return SlashRegistry.LootCrates().get(stack.getTag().getString(ID));
    }

    static String SCORE_SYMBOL = "\u2764";

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        try {
            LootCrate crate = getCrate(stack);
            int lvl = stack.getTag().getInt(LVL);
            int score = stack.getTag().getInt(SCORE);
            int tier = stack.getTag().getInt(TIER);

            tooltip.clear();

            tooltip.add(new StringTextComponent(TextFormatting.BOLD + "" + TextFormatting.YELLOW)
                    .appendSibling(crate.name().locName()));

            tooltip.add(TooltipUtils.level(lvl));

            Tooltip.addEmpty(tooltip);

            tooltip.add(TooltipUtils.tier(tier));

            Tooltip.addEmpty(tooltip);

            String stars = "";

            for (int i = 0; i < score; i++) {
                stars += " " + SCORE_SYMBOL;
            }

            tooltip.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Score: " + stars));

            Tooltip.addEmpty(tooltip);

            tooltip.add(new StringTextComponent(TextFormatting.BLUE + "Right click to open!"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player,
                                                    Hand hand) {

        if (!world.isRemote) {
            try {

                try {

                    ItemStack stack = player.getHeldItem(hand);

                    LootCrate crate = SlashRegistry.LootCrates()
                            .get(stack.getTag().getString(ID));
                    int lvl = stack.getTag().getInt(LVL);
                    int score = stack.getTag().getInt(SCORE);
                    int tier = stack.getTag().getInt(TIER);

                    LootInfo info = new LootInfo(player);
                    info.level = lvl;
                    info.tier = tier;

                    stack.shrink(1);

                    List<ItemStack> items = crate.generateItems(info, score, crate.wonLottery());

                    for (ItemStack istack : items) {
                        player.entityDropItem(istack, 1F);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
    }

}