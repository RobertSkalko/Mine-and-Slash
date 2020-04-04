package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
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

                BaseSpell spell = gear.getRightClickSpell();

                if (spell != null) {

                    PlayerSpellCap.ISpellsCap spells = Load.spells(player);

                    if (spells.canCastRightClickSpell(spell, player)) {
                        spells
                            .getSpellData()
                            .setToCast(spell, player);

                        SpellCastContext ctx = new SpellCastContext(player, 0, spell);

                        spell.spendResources(ctx);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
