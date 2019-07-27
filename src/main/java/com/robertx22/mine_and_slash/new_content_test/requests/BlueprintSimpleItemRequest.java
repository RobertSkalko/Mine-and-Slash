package com.robertx22.mine_and_slash.new_content_test.requests;

import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

@Storable
public class BlueprintSimpleItemRequest extends BaseBlueprintRequest {

    @Store
    public int amount = 1;

    @Store
    public String itemRegistryName;

    public BlueprintSimpleItemRequest() {

    }

    public BlueprintSimpleItemRequest(int amount, Item item) {
        this.amount = amount;
        this.itemRegistryName = item.getRegistryName().toString();
    }

    @Override
    public void random(ItemRarity rar) {

        int raramount = (int) rar.powerMultiplier();
        this.amount = RandomUtils.RandomRange(raramount, raramount * 2);

        // TODO turn currency items into a registry?
        this.itemRegistryName = SlashRegistry.CurrencyItems()
                .random()
                .getRegistryName()
                .toString();

    }

    @Override
    public int getDifficultyValue() {

        int diff = 100;

        // diff *= BaseBlueprintRequest.getRarityDifficultyMulti(rarity);
        //  diff *= BaseBlueprintRequest.getTierDifficultyMulti(tier);

        return diff;
    }

    // public abstract BaseBlueprintItemRequest random(BlueprintItemData data);
    @Override
    public boolean matches(ItemStack stack) {

        if (stack.getCount() >= amount) {
            if (!this.itemRegistryName.isEmpty()) {
                if (!this.itemRegistryName.equals(stack.getItem()
                        .getRegistryName()
                        .toString())) {

                    return false;

                }
            }

            return true;
        }

        return false;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        if (itemRegistryName != null && itemRegistryName.isEmpty() == false) {
            try {
                list.add(new StringTextComponent(amount + "x ").appendSibling(ForgeRegistries.ITEMS
                        .getValue(new ResourceLocation(itemRegistryName))
                        .getName()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return list;
    }
}

