package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RightClickSpell {

    @SubscribeEvent
    public static void onClick(PlayerInteractEvent.RightClickItem event) {

        try {

            PlayerEntity player = event.getPlayer();

            if (player.world.isRemote) {
                return;
            }

            ItemStack stack = event.getItemStack();

            GearItemData gear = Gear.Load(stack);

            if (gear != null) {

                PlayerSpellCap.ISpellsCap spells = Load.spells(player);

                BaseSpell spell = spells.getSpellData()
                    .getRightClickSpell();

                if (!spell.isAllowedAsRightClickFor(gear.GetBaseGearType())) {
                    return;
                }

                if (spells.canCastRightClickSpell(player)) {
                    spells
                        .getSpellData()
                        .setToCast(spells.getSpellData()
                            .getRightClickSpell(), player);

                    spells.getSpellData()
                        .getRightClickSpell()
                        .spendResources(player);

                } else {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
