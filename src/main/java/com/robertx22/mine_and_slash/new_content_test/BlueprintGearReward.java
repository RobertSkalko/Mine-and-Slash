package com.robertx22.mine_and_slash.new_content_test;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.RunedGearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import com.robertx22.mine_and_slash.loot.gens.GearLootGen;
import com.robertx22.mine_and_slash.loot.gens.RunedGearLootGen;
import com.robertx22.mine_and_slash.loot.gens.UniqueGearLootGen;
import com.robertx22.mine_and_slash.saveclasses.gearitem.GearItemEnum;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Storable
public class BlueprintGearReward {

    @Store
    public int level;
    @Store
    public int tier;
    @Store
    public String specificType;
    @Store
    public int rarity;
    @Store
    public String uniqueID;
    @Store
    public GearItemEnum enumGearType;

    @Store
    public List<GearBlueprintSpecialEffects> specialEffects = new ArrayList<>();

    public BlueprintGearReward() {

    }

    public BlueprintGearReward(BlueprintItemData data) {

        int diff = data.getDifficulty();
        this.rarity = data.rarity;
        this.level = data.level;
        this.tier = data.tier;

        this.enumGearType = GearItemEnum.random();

        if (this.enumGearType == GearItemEnum.UNIQUE) {
            this.uniqueID = SlashRegistry.UniqueGears().random().GUID();
        }

        // TODO BASE THE REWARD ON THE DIFFICULTY..

    }

    public ItemStack create() {

        ItemStack stack = ItemStack.EMPTY;

        try {
            GearBlueprint blueprint = null;

            if (enumGearType == GearItemEnum.NORMAL) {
                blueprint = new GearBlueprint(level);
            } else if (enumGearType == GearItemEnum.RUNED) {
                blueprint = new RunedGearBlueprint(level);
            } else if (enumGearType == GearItemEnum.UNIQUE) {
                blueprint = new UniqueGearBlueprint(level, uniqueID);
            }

            blueprint.LevelRange = false;
            blueprint.setSpecificRarity(rarity);
            blueprint.SetSpecificType(specificType);

            if (enumGearType == GearItemEnum.NORMAL) {
                stack = GearLootGen.CreateStack(blueprint);
            } else if (enumGearType == GearItemEnum.RUNED) {
                stack = RunedGearLootGen.CreateStack((RunedGearBlueprint) blueprint);
            } else if (enumGearType == GearItemEnum.UNIQUE) {
                stack = UniqueGearLootGen.CreateStack((UniqueGearBlueprint) blueprint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        GearItemData gear = Gear.Load(stack);
        specialEffects.forEach(x -> x.modify(gear));
        Gear.Save(stack, gear);

        return stack;

    }
}