package com.robertx22.mine_and_slash.potion_effects.all;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.OnTickAction;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TeleportProtection extends BasePotionEffect {

    public static final TeleportProtection INSTANCE = new TeleportProtection();

    private TeleportProtection() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.tickActions.add(new OnTickAction(5, ctx -> {
            Entity entity = ctx.entity;

            if (entity.world.isRemote == false && entity instanceof ServerPlayerEntity) {

                /*
                if (entity.ticksExisted > 10) {
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

                 */
            }
            return ctx;
        }, null));

    }

    @Override
    public int getDurationInSeconds() {
        return 10;
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
    public void performEffect(LivingEntity en, int amplifier) {

        en.setInvulnerable(true);
        super.performEffect(en, amplifier);

    }

    private void goUpward(ServerPlayerEntity en) {

        int y = en.getPosition()
            .getY() + 2;
        // idk which one of these set pos things work
        en.setLocationAndAngles(en.posX, y, en.posZ, en.rotationYaw, en.rotationPitch);
        en.setPosition(en.posX, y, en.posZ);
        en.connection.setPlayerLocation(en.posX, y, en.posZ, en.rotationYaw, en.rotationPitch);
        en.setPositionAndUpdate(en.posX, y, en.posZ);

    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        return new ArrayList<>();
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        return new PreCalcSpellConfigs();
    }

    @Nullable
    @Override
    public BaseSpell getSpell() {
        return null;
    }

    @Override
    public SpellSchools getSchool() {
        return null;
    }
}
