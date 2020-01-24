package com.robertx22.mine_and_slash.database.spells.spell_classes.shaman;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.trident.ThunderspearEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ThunderspearSpell extends BaseSpell {

    public ThunderspearSpell() {

    }

    public Elements element = Elements.Thunder;

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.SHAMAN;
    }

    @Override
    public int getCooldownInSeconds() {
        return 0;
    }

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.Single_Target_Projectile;
    }

    @Override
    public String GUID() {
        return "thunder_spear";
    }

    @Override
    public int getManaCost() {
        return 20;
    }

    @Override
    public int useTimeTicks() {
        return 10;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.one(dmgStat(), 1F, 15);
    }

    @Override
    public Elements getElement() {
        return element;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(SpellTooltips.singleTargetProjectile());

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.Blizzard;
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {
        World world = caster.world;

        Vec3d pos = caster.getPositionVector();

        ThunderspearEntity en = SpellUtils.getSpellEntity(new ThunderspearEntity(world), this, caster);

        SpellUtils.setupProjectileForCasting(en, caster, 2);

        caster.world.addEntity(en);

        caster.world.playMovingSound(
                (PlayerEntity) null, en, SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);

        return true;
    }
}