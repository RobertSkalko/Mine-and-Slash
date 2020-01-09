package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.loot.gens.stack_changers.DamagedGear;
import com.robertx22.mine_and_slash.loot.gens.util.GearCreationUtils;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.item.ItemStack;

public class UniqueGearBlueprint extends GearBlueprint {

    public UniqueGearBlueprint(int level, int mapTier) {
        super(level);
        this.tier.number = mapTier;
    }

    public UniqueGearBlueprint(int level, int mapTier, boolean tierIsRandom) {
        super(level);
        this.tier.isRandom = tierIsRandom;
        this.tier.number = mapTier;

    }

    public UniqueGearBlueprint(int level, String guid) {
        super(level);
        this.guid = guid;
        this.uniqueIsRandom = false;

    }

    @Override
    void onCostruct() {
        this.rarity.setSpecificRarity(IRarity.Unique);
        actionsAfterGeneration.add(DamagedGear.INSTANCE);
    }

    @Override
    public GearItemData createData() {
        return GearCreationUtils.CreateData(this);
    }

    @Override
    ItemStack generate() {
        GearItemData data = createData();

        if (data != null && data.getItem() != null) {
            ItemStack stack = new ItemStack(data.getItem());

            Gear.Save(stack, data);

            return stack;
        }
        return ItemStack.EMPTY;
    }

    private String guid = "";
    public boolean uniqueIsRandom = true;

    public void setSpecificID(String id) {

        this.guid = id;
        this.uniqueIsRandom = false;

    }

    public IUnique getUnique() {
        if (this.uniqueIsRandom) {

            if (this.tier.isRandom == false) {
                return SlashRegistry.UniqueGears()
                        .getWrapped()
                        .ofExactTier(tier.number)
                        .random();

            } else {
                return randomUnique();
            }
        } else {
            return SlashRegistry.UniqueGears().get(this.guid);
        }

    }

    private IUnique randomUnique() {

        return SlashRegistry.UniqueGears()
                .getWrapped()
                .ofTierOrLess(tier.number)
                .ofSpecificGearType(gearItemSlot.get().GUID())
                .random();

    }

}
