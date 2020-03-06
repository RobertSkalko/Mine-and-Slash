package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Staff;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Wand;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.NoEnergyPacket;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ShootableItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSpell implements IWeighted, IGUID, ISlashRegistryEntry<BaseSpell>, ITooltipList {
    protected List<SpellPredicate> castRequirements = new ArrayList<>();

    private static Predicate<LivingEntity> SHOOTABLE_PRED = x -> {
        Item item = x.getHeldItemMainhand()
            .getItem();
        return item instanceof ShootableItem;
    };

    private static Predicate<LivingEntity> MELEE_PRED = x -> {
        try {
            GearItemData data = Gear.Load(x.getHeldItemMainhand());
            return data != null && data.GetBaseGearType()
                .isMeleeWeapon();
        } catch (Exception e) {
            return false;
        }
    };

    public static SpellPredicate REQUIRE_SHOOTABLE = new SpellPredicate(SHOOTABLE_PRED, new SText(TextFormatting.GREEN + "Requires Bow/Crossbow to use: "));
    public static SpellPredicate REQUIRE_MELEE = new SpellPredicate(MELEE_PRED, new SText(TextFormatting.GOLD + "Requires Melee weapon to use: "));

    public boolean shouldActivateCooldown(PlayerEntity player, PlayerSpellCap.ISpellsCap spells) {
        return true;
    }

    public void onCastingTick(PlayerEntity player, PlayerSpellCap.ISpellsCap spells, int tick) {
    }

    public enum AllowedAsRightClickOn {
        MAGE_WEAPON, MELEE_WEAPON, NONE
    }

    public enum SpellType {
        Single_Target_Projectile,
        Aoe_Projectile,
        Self_Heal,
        Restore_Energy,
        Aoe_Damage_Nova,
        LASTING_AOE,
        Dash,
        Self_Buff,
        Aoe_Debuff // TODO TURN THESE INTO SINGLES AND ASK FOR LIST
    }

    public AllowedAsRightClickOn allowedAsRightClickOn = AllowedAsRightClickOn.NONE;

    public boolean isAllowedAsRightClickFor(GearItemSlot slot) {
        switch (allowedAsRightClickOn) {
            case NONE: {
                return false;
            }
            case MELEE_WEAPON: {
                return slot.isMeleeWeapon();
            }
            case MAGE_WEAPON: {
                return slot == Wand.INSTANCE || slot == Staff.INSTANCE;
            }
        }
        return false;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Gears.get(getRarityRank());
    }

    public Stat dmgStat() {
        return new ElementalSpellDamage(getElement());
    }

    public boolean goesOnCooldownIfCastCanceled() {
        // override for spells that do oncastingtick
        return false;
    }

    public final boolean isLastTick(int tick) {
        return tick % useTimeTicks() == 0;
    }

    @Override
    public int Tier() {
        return 0;
    }

    public final ResourceLocation getIcon() {
        return new ResourceLocation(Ref.MODID, "textures/gui/spells/" + getSchool().id + "/" + GUID() + ".png");
    }

    public abstract SpellSchools getSchool();

    public int getCooldownInTicks() {
        return getCooldownInSeconds() * 20;
    }

    public abstract int getCooldownInSeconds();

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.SPELL;
    }

    public String typeString() {
        return this.getSpellType()
            .toString()
            .replaceAll("_", " ");
    }

    public boolean hasScalingValue() {
        return true;
    }

    public abstract SpellType getSpellType();

    public abstract String GUID();

    public abstract int getManaCost();

    public final int getCalculatedManaCost(UnitData data) {
        return (int) Mana.getInstance()
            .calculateScalingStatGrowth(getManaCost(), data.getLevel());
    }

    public abstract int useTimeTicks();

    public float getUseDurationInSeconds() {
        return (float) useTimeTicks() / 20;
    }

    public abstract SpellCalcData getCalculation();

    public abstract Elements getElement();

    public BaseSpell() {

    }

    public abstract List<ITextComponent> GetDescription(TooltipInfo info);

    public abstract Words getName();

    public int Weight() {
        return 1000;
    }

    public abstract boolean cast(LivingEntity caster, int ticksInUse);

    public void spendResources(LivingEntity caster) {
        UnitData data = Load.Unit(caster);
        data.getResources()
            .modify(getManaCostCtx(caster, data));
    }

    public ResourcesData.Context getManaCostCtx(LivingEntity caster, UnitData data) {
        return new ResourcesData.Context(
            data, caster, ResourcesData.Type.MANA, getCalculatedManaCost(data), ResourcesData.Use.SPEND);
    }

    public boolean canCast(LivingEntity caster) {

        if (caster instanceof PlayerEntity == false) {
            return true;
        }

        PlayerEntity player = (PlayerEntity) caster;

        if (!caster.world.isRemote) {

            UnitData data = Load.Unit(caster);

            if (data != null) {

                ResourcesData.Context ctx = getManaCostCtx(caster, data);

                if (data.getResources()
                    .hasEnough(ctx)) {

                    if (this.castRequirements.stream()
                        .anyMatch(x -> !x.predicate.test(player))) {
                        return false;
                    }

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

    @Override
    public final List<ITextComponent> GetTooltipString(TooltipInfo info) {

        UnitData data = info.unitdata;

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.BOLD + "" + getSchool().format).appendSibling(
            getName().locName()));

        TooltipUtils.addEmpty(list);

        list.addAll(GetDescription(info));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(TextFormatting.BLUE + "Mana Cost: " + getCalculatedManaCost(data)));
        list.add(new StringTextComponent(TextFormatting.YELLOW + "Cooldown: " + getCooldownInSeconds() + "s"));
        list.add(new StringTextComponent(TextFormatting.GREEN + "Cast time: " + getUseDurationInSeconds() + "s"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(getSchool().format + "School: ").appendSibling(getSchool().locName.locName()));

        TooltipUtils.addEmpty(list);

        this.castRequirements.forEach(x -> list.add(x.text));

        if (this.allowedAsRightClickOn == AllowedAsRightClickOn.MAGE_WEAPON) {
            TooltipUtils.addEmpty(list);
            list.add(new SText(TextFormatting.LIGHT_PURPLE + "Can be set as right click for a Mage Weapon"));
        }

        return list;

    }

}
