package com.robertx22.mine_and_slash.new_content_test.requests;

import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.DataItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

@Storable
public class BlueprintDataItemRequest extends BaseBlueprintRequest {

    @Store
    public int level = UNUSED_INT;

    @Store
    public int tier = UNUSED_INT;

    @Store
    public String specificType = "";

    @Store
    public int rarity = UNUSED_INT;

    @Store
    public String uniqueID;

    @Store
    public DataItemType dataItemType;

    // public abstract BaseBlueprintItemRequest random(BlueprintItemData data);

    @Override
    public void random(ItemRarity rar) {

    }

    @Override
    public int getDifficultyValue() {

        int diff = 1000;

        diff *= BaseBlueprintRequest.getRarityDifficultyMulti(rarity);
        diff *= BaseBlueprintRequest.getTierDifficultyMulti(tier);

        diff *= uniqueID.isEmpty() ? 1 : 2;

        return diff;
    }

    @Override
    public boolean matches(ItemStack stack) {

        ICommonDataItem data = ICommonDataItem.load(stack);

        if (data.getDataType() == dataItemType) {
            if (this.uniqueID.isEmpty() == false) {
                if (this.uniqueID.equals(data.getUniqueGUID()) == false) {
                    return false;
                }
            }

            return data != null && data.Tier() >= this.tier && data.getLevel() >= this.level && data
                    .getSpecificType()
                    .equals(this.specificType);
        }

        return false;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        if (level != UNUSED_INT) {
            list.add(Words.Level.locName().appendText(" : " + level));
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
