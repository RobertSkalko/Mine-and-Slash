package com.robertx22.mine_and_slash.new_content_test.requests;

import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class BlueprintSimpleItemRequest extends BaseBlueprintRequest {

    @Store
    public int amount = 1;

    @Store
    public String itemRegistryName;

    @Store
    public int tier = UNUSED_INT;

    @Store
    public int rarity = UNUSED_INT;

    @Override
    public void random(ItemRarity rar) {

        int raramount = (int) (3 * rar.powerMultiplier());
        this.amount = RandomUtils.RandomRange(raramount, raramount * 3);

        int rartier = (int) (1 * rar.powerMultiplier());
        this.tier = RandomUtils.RandomRange(rartier, ITiered.MAX_TIER);

        rarity = RandomUtils.weightedRandom(Rarities.Items.getRarities()).Rank();

    }

    @Override
    public int getDifficultyValue() {

        int diff = 100;

        diff *= BaseBlueprintRequest.getRarityDifficultyMulti(rarity);
        diff *= BaseBlueprintRequest.getTierDifficultyMulti(tier);

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

            Item item = stack.getItem();

            boolean matches = true;

            if (matches) {
                if (item instanceof ITiered) {
                    ITiered part = (ITiered) item;
                    matches = part.Tier() >= tier;
                }
            }
            if (matches) {
                if (item instanceof IRarity) {
                    IRarity part = (IRarity) item;
                    matches = part.getRarityRank() >= rarity;
                }
            }

            return matches;
        }

        return false;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        if (itemRegistryName.isEmpty() == false) {
            try {
                list.add(ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemRegistryName))
                        .getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (tier != UNUSED_INT) {
            list.add(Words.Tier.locName().appendText(" : " + tier));
        }

        if (rarity != UNUSED_INT) {
            list.add(Words.Rarity.locName().appendText(" : " + rarity));
        }

        return list;
    }
}

