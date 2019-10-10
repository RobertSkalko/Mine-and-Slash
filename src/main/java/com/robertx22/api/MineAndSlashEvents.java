package com.robertx22.api;

import com.robertx22.uncommon.capability.EntityData.UnitData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;

public class MineAndSlashEvents {

  public static class GiveExpEvent extends LivingEvent {

    public int experience = 0;
    public UnitData playerCapability;

    public GiveExpEvent(EntityPlayer entity, UnitData playerdata, int exp) {
      super(entity);
      playerCapability = playerdata;
      this.experience = exp;
      this.setResult(Result.ALLOW);

    }

  }
}
