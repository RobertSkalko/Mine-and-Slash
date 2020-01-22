package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.blizzard.BlizzardEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class BlizzardSpell extends BaseSpell {

    public BlizzardSpell() {

    }

    public Elements element = Elements.Water;

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.OCEAN_MYSTIC;
    }

    @Override
    public int getCooldownInSeconds() {
        return 15;
    }

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.LASTING_AOE;
    }

    @Override
    public String GUID() {
        return "blizzard";
    }

    @Override
    public int getManaCost() {
        return 80;
    }

    @Override
    public int useTimeTicks() {
        return 30;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.one(dmgStat(), 0.25F, 5);
    }

    @Override
    public Elements getElement() {
        return element;
    }

    @Override
    public ITextComponent GetDescription() {
        return Words.StormCloudSpellDesc.locName();
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {
        World world = caster.world;

        RayTraceResult ray = caster.pick(10D, 0.0F, false);

        Vec3d pos = ray.getHitVec();

        Entity en = SpellUtils.getSpellEntity(new BlizzardEntity(world), this, caster);

        en.setPosition(pos.x, pos.y, pos.z);

        caster.world.addEntity(en);

        return true;
    }

}
