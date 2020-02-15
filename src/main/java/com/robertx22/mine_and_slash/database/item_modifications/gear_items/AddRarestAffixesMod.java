package com.robertx22.mine_and_slash.database.item_modifications.gear_items;

import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.item_modifications.bases.BaseGearMod;
import com.robertx22.mine_and_slash.database.item_modifications.bases.ItemModType;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.PrefixData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.SuffixData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;

public class AddRarestAffixesMod extends BaseGearMod {

    @Override
    public ItemModType getItemModType() {
        return ItemModType.AFFIXES;
    }

    @Override
    public ITextComponent locName() {
        return Words.AddRarestAffixes.locName();
    }

    @Override
    protected boolean canModifyPRIVATE(ICommonDataItem data) {
        GearItemData gear = (GearItemData) data;
        return gear != null;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Mythic;
    }

    @Override
    public void modifyGear(GearItemData gear) {

        Prefix prefix = (Prefix) SlashRegistry.Affixes()
                .getWrapped()
                .ofAffixType(BaseAffix.Type.prefix)
                .allThatMeetRequirement(gear)
                .ofHighestRarity()
                .random();

        gear.prefix = new PrefixData();
        gear.prefix.create(gear, prefix);

        // line

        Suffix suffix = (Suffix) SlashRegistry.Affixes()
                .getWrapped()
                .ofAffixType(BaseAffix.Type.suffix)
                .allThatMeetRequirement(gear)
                .ofHighestRarity()
                .random();

        gear.suffix = new SuffixData();
        gear.suffix.create(gear, suffix);

    }

    @Override
    public String GUID() {
        return "add_rarest_affixes";
    }

}

