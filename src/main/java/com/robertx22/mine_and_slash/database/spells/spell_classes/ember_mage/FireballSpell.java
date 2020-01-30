package com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage;

import com.robertx22.mine_and_slash.database.spells.entities.proj.FireballEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseProjectileSpell;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class FireballSpell extends BaseProjectileSpell {

    public FireballSpell() {
        super();
    }

    @Override
    public AbstractArrowEntity newEntity(World world) {
        return new FireballEntity(world);
    }

    @Override
    public SoundEvent getShootSound() {
        return SoundEvents.ITEM_FIRECHARGE_USE;
    }

    @Override
    public Elements getElement() {
        return Elements.Fire;
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.EMBER_MAGE;
    }

    @Override
    public int getCooldownInSeconds() {
        return 0;
    }

    @Override
    public String GUID() {
        return "fireball";
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.one(dmgStat(), 1F, 10);
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(SpellTooltips.singleTargetProjectile());

        return list;

    }

    @Override
    public Words getName() {
        return Words.Fireball;
    }

}