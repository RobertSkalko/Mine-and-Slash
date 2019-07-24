package com.robertx22.mine_and_slash.potion_effects.all;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.SpellPotionBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;

public class TeleportProtection extends SpellPotionBase {

    public static final TeleportProtection INSTANCE = new TeleportProtection();

    private TeleportProtection() {
        // boolean isBadEffectIn, int liquidColorIn
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

    }

    @Override
    public String locNameForLangFile() {
        return "Teleport Guard";
    }

    @Override
    public String GUID() {
        return "teleport_protection";
    }

    @Override
    public void doEffect(Entity applier, Entity caster, LivingEntity target,
                         int amplifier) {
    }

    @Override
    public void performEffect(LivingEntity en, int amplifier) {

        en.setInvulnerable(true);
        super.performEffect(en, amplifier);

    }

    @Override
    public void performEffectEverySetTime(LivingEntity entity, int amplifier) {

        try {

            if (entity.world.isRemote == false && entity instanceof ServerPlayerEntity) {

                int tries = 0;

                while (entity.isEntityInsideOpaqueBlock() || entity.posY < 2) {

                    tries++;

                    if (entity.posY >= entity.world.getHeight()) {
                        break;
                    }

                    if (tries > 5) {
                        break;
                    }

                    goUpward((ServerPlayerEntity) entity);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void goUpward(ServerPlayerEntity en) {

        int y = en.getPosition().getY() + 2;
        // idk which one of these set pos things work
        en.setLocationAndAngles(en.posX, y, en.posZ, en.rotationYaw, en.rotationPitch);
        en.setPosition(en.posX, y, en.posZ);
        en.connection.setPlayerLocation(en.posX, y, en.posZ, en.rotationYaw, en.rotationPitch);
        en.setPositionAndUpdate(en.posX, y, en.posZ);

    }

    @Override
    public int performEachXTicks() {
        return 5;
    }
}
