package com.robertx22.mine_and_slash.onevent.entity;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.loot.LootUtils;
import com.robertx22.mine_and_slash.loot.MasterLootGen;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.registers.common.CriteriaRegisters;
import com.robertx22.mine_and_slash.network.DmgNumPacket;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityTypeUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnMobDeathDrops {

    @SubscribeEvent
    public static void mobOnDeathDrop(LivingDeathEvent event) {

        try {

            LivingEntity entity = event.getEntityLiving();

            if (entity.world.isRemote) {
                return;
            }

            if (!(entity instanceof PlayerEntity)) {
                if (event.getSource().getTrueSource() instanceof PlayerEntity) {
                    if (Load.hasUnit(entity)) {

                        PlayerEntity player = (PlayerEntity) event.getSource()
                                .getTrueSource();

                        UnitData victim = Load.Unit(entity);
                        UnitData killer = Load.Unit(event.getSource().getTrueSource());

                        if (victim.shouldDropLoot(entity) == false) {
                            return;
                        }

                        CriteriaRegisters.DROP_LVL_PENALTY_TRIGGER.trigger((ServerPlayerEntity) player, killer, victim);

                        float loot_multi = EntityTypeUtils.getLootMulti(entity);
                        float exp_multi = EntityTypeUtils.getExpMulti(entity);

                        if (loot_multi > 0) {

                            MasterLootGen.genAndDrop(victim, killer, entity, player);

                        }

                        if (exp_multi > 0) {
                            int exp = GiveExp(entity, (LivingEntity) event.getSource()
                                    .getTrueSource(), killer, victim, exp_multi);

                            DmgNumPacket packet = new DmgNumPacket(entity, Elements.Nature, "+" + DamageEffect
                                    .FormatNumber(exp) + " Exp!");
                            packet.isExp = true;

                            ServerPlayerEntity mp = (ServerPlayerEntity) event.getSource()
                                    .getTrueSource();

                            MMORPG.sendToClient(packet, mp);
                        }
                    }
                }
            }

        } catch (

                Exception e) {
            e.printStackTrace();
        }

    }

    private static int GiveExp(LivingEntity victim, LivingEntity entity, UnitData player,
                               UnitData mob, float multi) {

        int exp = (int) (mob.getLevel() * Rarities.Mobs.get(mob.getRarity())
                .ExpOnKill() * multi);

        exp = (int) LootUtils.ApplyLevelDistancePunishment(mob, player, exp);

        exp = player.PostGiveExpEvent(victim, (PlayerEntity) entity, exp);

        return exp;

    }

}
