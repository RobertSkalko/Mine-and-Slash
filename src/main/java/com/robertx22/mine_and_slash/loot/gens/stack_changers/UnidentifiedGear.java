package com.robertx22.mine_and_slash.loot.gens.stack_changers;

import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

public class UnidentifiedGear implements IStackAction {

    private UnidentifiedGear() {
    }

    public static UnidentifiedGear getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void changeStack(ItemStack stack) {

        GearItemData data = Gear.Load(stack);

        if (data != null) {

            if (RandomUtils.roll(data.getRarity()
                .unidentifiedChance())) {
                data.setIdentified(false);
                Gear.Save(stack, data);

            }
        }

    }

    private static class SingletonHolder {
        private static final UnidentifiedGear INSTANCE = new UnidentifiedGear();
    }
}
