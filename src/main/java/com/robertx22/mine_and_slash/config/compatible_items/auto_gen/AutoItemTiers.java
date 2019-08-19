package com.robertx22.mine_and_slash.config.compatible_items.auto_gen;

import net.minecraft.item.*;

public enum AutoItemTiers {

    Trash(new AutoItemTier(0).lvl(0, 20).rar(0, 2)),
    Decent(new AutoItemTier(1).lvl(10, 30).rar(0, 3)),
    Good(new AutoItemTier(2).lvl(20, 40).rar(0, 4)),
    Great(new AutoItemTier(3).lvl(30, 60).rar(1, 4)),
    Amazing(new AutoItemTier(4).lvl(40, 100).rar(1, 5));

    AutoItemTiers(AutoItemTier stats) {
        this.tierStats = stats;
    }

    public AutoItemTier tierStats;

    public AutoItemTiers getTier(GenAutoCompItems.AutoCompItem ctx) {

        Item item = ctx.item;

        float perc = 0;

        if (item instanceof ArmorItem) {
            ArmorItem armor = (ArmorItem) item;
            IArmorMaterial mat = armor.getArmorMaterial();

            perc += mat.getDamageReductionAmount(ctx.getSlot()) / ArmorMaterial.DIAMOND.getDamageReductionAmount(ctx
                    .getSlot());
            perc += mat.getDurability(ctx.getSlot()) / ArmorMaterial.DIAMOND.getDamageReductionAmount(ctx
                    .getSlot());
            perc += mat.getToughness() / ArmorMaterial.DIAMOND.getToughness();

            perc += mat.getEnchantability() / ArmorMaterial.DIAMOND.getEnchantability();

        } else if (item instanceof TieredItem) {
            TieredItem tiered = (TieredItem) item;
            IItemTier tier = tiered.getTier();
        }
        return Trash;

    }

}
