package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.NoEnergyPacket;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
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
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Spells.get(getRarityRank());
    }

    @Override
    public int Tier() {
        return 0;
    }

    public ResourceLocation getIcon() {
        return new ResourceLocation(Ref.MODID, "textures/gui/spells/" + iconName() + ".png");
    }

    public abstract SpellSchools getSchool();

    public String iconName() { // TODO make abstract when done
        return "";
    }

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

    public boolean baseValueScalesWithLevel() {
        return true;
    }

    public abstract SpellType getSpellType();

    public abstract String GUID();

    public abstract int getManaCost();

    public abstract int useTimeTicks();

    public float getUseDurationInSeconds() {
        return (float) useTimeTicks() / 20;
    }

    // public abstract int Cooldown();

    public abstract int getBaseValue();

    public abstract EffectCalculation ScalingValue();

    public int DamageVariance = 50;

    public abstract Elements getElement();

    public boolean ScalesWithLevel = true;

    public BaseSpell() {

    }

    public abstract ITextComponent GetDescription(SpellItemData data);

    public int Weight() {
        return 1000;
    }

    public abstract boolean cast(PlayerEntity caster, int ticksInUse, SpellItemData data);

    public boolean CanCast(PlayerEntity caster, SpellItemData data) {

        if (!caster.world.isRemote) {

            UnitData unit = Load.Unit(caster);

            if (unit != null) {

                if (data.level > unit.getLevel()) {
                    caster.sendMessage(Chats.You_are_too_low_level.locName());
                    return false;
                }

                ResourcesData.Context ctx = new ResourcesData.Context(
                        unit, caster, ResourcesData.Type.MANA, data.getManaCost(unit), ResourcesData.Use.SPEND);

                if (unit.getResources().hasEnough(ctx)) {
                    unit.getResources().modify(ctx);
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
