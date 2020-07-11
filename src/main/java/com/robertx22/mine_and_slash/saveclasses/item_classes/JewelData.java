package com.robertx22.mine_and_slash.saveclasses.item_classes;

import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.requirements.bases.GearRequestedFor;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.AffixData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltip;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipContext;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Jewel;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.DataItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.ItemStack;

@Storable
public class JewelData implements ITooltip, ICommonDataItem {

    @Store
    public AffixData affix;

    public JewelData() {

    }

    public void insertIntoGear(GearItemData gear) {

        AffixData socket = gear.getAllAffixes()
            .stream()
            .filter(x -> x.affixType == affix.affixType && x.isSocketAndEmpty())
            .findFirst()
            .get();

        socket.percent = affix.percent;
        socket.baseAffix = affix.baseAffix;
    }

    public boolean canInsertInto(GearItemData gear) {

        if (gear.containsAffix(affix.baseAffix)) {
            return false;
        }

        BaseAffix af = affix.getAffix();
        if (!af.meetsRequirements(new GearRequestedFor(gear))) {
            return false;
        }

        return gear.getAllAffixes()
            .stream()
            .anyMatch(x -> x.isSocketAndEmpty() && x.affixType == affix.affixType);

    }

    @Override
    public void BuildTooltip(TooltipContext ctx) {

        TooltipInfo info = new TooltipInfo(ctx.data);
        GearItemData gear = Gear.Load(ctx.stack);

        ctx.event.getToolTip()
            .addAll(this.affix.GetTooltipString(info, null));

    }

    public void randomize() {

        this.affix = new AffixData(RandomUtils.roll(50) ? BaseAffix.Type.prefix : BaseAffix.Type.suffix);

        this.affix.baseAffix = SlashRegistry.Affixes()
            .getFilterWrapped(x -> x.type == affix.affixType)
            .random()
            .GUID();

        this.affix.percent = RandomUtils.RandomRange(20, 100);

    }

    @Override
    public DataItemType getDataType() {
        return DataItemType.JEWEL;
    }

    @Override
    public void saveToStack(ItemStack stack) {
        Jewel.Save(stack, this);
    }

    @Override
    public ItemStack getSalvageResult(float salvageBonus) {
        return new ItemStack(ModItems.MAGIC_ESSENCE.get());
    }

    @Override
    public int getRarityRank() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Gears.get(0);
    }

    @Override
    public int getTier() {
        return 0;
    }
}
