package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.NoEnergyPacket;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.StatUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public abstract class BaseSpell implements IWeighted, IGUID, ISlashRegistryEntry<BaseSpell> {

    public enum SpellType {
        Single_Target_Projectile,
        Aoe_Projectile,
        Self_Heal,
        Aoe_Bomb_Projectile,
        Restore_Energy,
        Aoe_Damage_Nova,
        LASTING_AOE,
        Self_Buff,
        Aoe_Debuff // TODO TURN THESE INTO SINGLES AND ASK FOR LIST
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Items.get(getRarityRank());
    }

    public Stat dmgStat() {
        return new ElementalSpellDamage(getElement());
    }

    @Override
    public int Tier() {
        return 0;
    }

    public final ResourceLocation getIcon() {
        return new ResourceLocation(Ref.MODID, "textures/gui/spells/" + getSchool().id + "/" + GUID() + ".png");
    }

    public abstract SpellSchools getSchool();

    public final int getCooldownInTicks() {
        return getCooldownInSeconds() * 20;
    }

    public abstract int getCooldownInSeconds();

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.SPELL;
    }

    public String typeString() {
        return this.getSpellType().toString().replaceAll("_", " ");
    }

    public boolean hasScalingValue() {
        return true;
    }

    public abstract SpellType getSpellType();

    public abstract String GUID();

    public abstract int getManaCost();

    public final int getCalculatedManaCost(UnitData data) {
        return (int) StatUtils.calculateNormalScalingStatGrowth(getManaCost(), data.getLevel());
    }

    public abstract int useTimeTicks();

    public float getUseDurationInSeconds() {
        return (float) useTimeTicks() / 20;
    }

    public int DamageVariance = 50;

    public abstract SpellCalcData getCalculation();

    public abstract Elements getElement();

    public BaseSpell() {

    }

    public abstract ITextComponent GetDescription();

    public int Weight() {
        return 1000;
    }

    public abstract boolean cast(PlayerEntity caster, int ticksInUse);

    public boolean CanCast(PlayerEntity caster) {

        if (!caster.world.isRemote) {

            UnitData data = Load.Unit(caster);

            if (data != null) {

                ResourcesData.Context ctx = new ResourcesData.Context(
                        data, caster, ResourcesData.Type.MANA, getCalculatedManaCost(data), ResourcesData.Use.SPEND);

                if (data.getResources().hasEnough(ctx)) {
                    data.getResources().modify(ctx);
                    return true;

                } else {
                    if (caster instanceof ServerPlayerEntity) {
                        MMORPG.sendToClient(new NoEnergyPacket(), (ServerPlayerEntity) caster);
                    }

                }
            }
        }
        return false;

    }

}
