package com.robertx22.mine_and_slash.api;

import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent;

public class MineAndSlashEvents {

    public static class GiveExpEvent extends LivingEvent {

        public int experience = 0;
        public UnitData playerCapability;
        public LivingEntity entityKilled;
        public PlayerEntity player;

        public GiveExpEvent(LivingEntity killed, PlayerEntity player, UnitData playerdata,
                            int exp) {
            super(player);
            this.entityKilled = killed;
            this.player = player;
            this.playerCapability = playerdata;
            this.experience = exp;
            this.setResult(Result.ALLOW);

        }

    }
}