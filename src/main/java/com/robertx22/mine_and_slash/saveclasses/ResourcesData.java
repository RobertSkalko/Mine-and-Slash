package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.database.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.HealEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.ModifyResourceEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellHealEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.HealthUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

@Storable
public class ResourcesData {

    public ResourcesData() {

    }

    public static class Context {

        public BaseSpell spell;

        public UnitData sourceData;
        public LivingEntity source;

        public UnitData targetData;
        public LivingEntity target;

        public Type type;
        public float amount;
        public Use use;

        public boolean statsCalculated = false;

        public Context(UnitData data, LivingEntity entity, Type type, float amount,
                       Use use, BaseSpell spell) {
            this.targetData = data;
            this.target = entity;
            this.sourceData = data;
            this.source = entity;
            this.type = type;
            this.amount = amount;
            this.use = use;
            this.spell = spell;
            calculateStats();
        }

        public Context(UnitData data, LivingEntity entity, Type type, float amount,
                       Use use) {
            this.targetData = data;
            this.target = entity;
            this.sourceData = data;
            this.source = entity;
            this.type = type;
            this.amount = amount;
            this.use = use;
            calculateStats();
        }

        private void calculateStats() {
            if (!statsCalculated) {
                new ModifyResourceEffect(this).Activate();
            }
        }

    }

    public enum Type {
        HEALTH,
        MANA,
        ENERGY,
        MAGIC_SHIELD,
        BLOOD
    }

    public enum Use {
        SPEND,
        RESTORE
    }

    @Store
    private float energy = 0;
    @Store
    private float mana = 0;
    @Store
    private float magicShield = 0;
    @Store
    private float blood = 0;

    public float getEnergy() {
        return energy;
    }

    public float getMana() {
        return mana;
    }

    public float getBlood() {
        return blood;
    }

    public float getMagicShield() {
        return magicShield;
    }

    public float getHealth(UnitData data, LivingEntity entity) {
        return data.getUnit().health().CurrentValue(entity, data.getUnit());
    }

    public float getModifiedValue(Context ctx) {
        if (ctx.use == Use.RESTORE) {
            return get(ctx) + ctx.amount;
        } else {
            return get(ctx) - ctx.amount;
        }

    }

    private float get(Context ctx) {
        if (ctx.type == Type.ENERGY) {
            return energy;
        } else if (ctx.type == Type.MANA) {
            return mana;
        } else if (ctx.type == Type.MAGIC_SHIELD) {
            return magicShield;
        } else if (ctx.type == Type.BLOOD) {
            return blood;
        } else if (ctx.type == Type.HEALTH) {
            return ctx.targetData.getUnit()
                    .health()
                    .CurrentValue(ctx.target, ctx.targetData.getUnit());
        }
        return 0;

    }

    private void modifyBy(Context ctx) {

        if (ctx.type == Type.ENERGY) {
            energy = MathHelper.clamp(getModifiedValue(ctx), 0, ctx.targetData.getUnit()
                    .energyData().Value);
        } else if (ctx.type == Type.MANA) {
            mana = MathHelper.clamp(getModifiedValue(ctx), 0, ctx.targetData.getUnit()
                    .manaData().Value);
        } else if (ctx.type == Type.MAGIC_SHIELD) {
            magicShield = MathHelper.clamp(getModifiedValue(ctx), 0, ctx.targetData.getUnit()
                    .magicShieldData().Value);
        } else if (ctx.type == Type.BLOOD) {
            blood = MathHelper.clamp(getModifiedValue(ctx), 0, ctx.targetData.getUnit()
                    .getMaximumBlood());
        } else if (ctx.type == Type.HEALTH) {
            if (ctx.use == Use.RESTORE) {
                heal(ctx);
            } else {
                ctx.target.setHealth(HealthUtils.DamageToMinecraftHealth(getModifiedValue(ctx), ctx.target, ctx.targetData));
            }
        }
    }

    private void heal(Context ctx) {
        if (ctx.target.isAlive()) {

            if (ctx.spell != null) {
                SpellHealEffect effect = new SpellHealEffect(ctx);
                effect.Activate();

            } else {
                HealEffect effect = new HealEffect(ctx);
                effect.Activate();
            }
        }
    }

    public boolean hasEnough(Context ctx) {
        if (ctx.amount <= 0) {
            return true;
        }

        return get(ctx) >= ctx.amount;
    }

    public void modify(Context ctx) {
        modifyBy(ctx);
    }

}
