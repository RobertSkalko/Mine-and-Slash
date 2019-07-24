package com.robertx22.mine_and_slash.new_content_test;

import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.new_content_test.requests.BlueprintDataItemRequest;
import com.robertx22.mine_and_slash.new_content_test.requests.BlueprintSimpleItemRequest;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.DataItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.ArrayList;
import java.util.List;

@Storable
public class BlueprintItemData implements ICommonDataItem<ItemRarity> {

    @Store
    public List<BlueprintDataItemRequest> dataRequests = new ArrayList<>();

    @Store
    public List<BlueprintSimpleItemRequest> simpleItemRequests = new ArrayList<>();

    @Store
    public BlueprintGearReward gearReward = new BlueprintGearReward();

    @Store
    public int level;

    @Store
    public int tier;

    @Store
    public int rarity;

    public int getDifficulty() {

        int diff = 0;

        diff += dataRequests.stream()
                .mapToInt(BlueprintDataItemRequest::getDifficultyValue)
                .sum();

        diff += simpleItemRequests.stream()
                .mapToInt(BlueprintSimpleItemRequest::getDifficultyValue)
                .sum();

        return diff;

    }

    @Override
    public DataItemType getDataType() {
        return DataItemType.BLUEPRINT;
    }

    @Override
    public String getUniqueGUID() {
        return "";
    }

    @Override
    public void BuildTooltip(ItemStack stack, ItemTooltipEvent event, Unit unit,
                             EntityCap.UnitData data) {
        List<ITextComponent> tooltip = event.getToolTip();

        TooltipInfo info = new TooltipInfo();

        tooltip.add(Words.Requirements.locName().appendText(": "));

        dataRequests.forEach(x -> x.GetTooltipString(info));
        simpleItemRequests.forEach(x -> x.GetTooltipString(info));

    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getRarityRank() {
        return rarity;
    }

    @Override
    public ItemRarity getRarity() {
        return Rarities.Items.get(rarity);
    }

    @Override
    public ItemStack getSalvageResult(float salvageBonus) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isSalvagable(SalvageContext context) {
        return false;
    }

    @Override
    public int Tier() {
        return tier;
    }

    @Override
    public String getSpecificType() {
        return "";
    }
}
