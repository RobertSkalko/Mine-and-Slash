package com.robertx22.mine_and_slash.database.spells.entities.bases;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public interface ISpellEntity extends IEntityAdditionalSpawnData {

    default void initSpellEntity() {
    }

    EntitySpellData getSyncedSpellData();

    void setSyncedSpellData(EntitySpellData data);

    default int getDefaultLifeInTicks() {
        return 200;
    }

    default Elements getElement() {
        return getSyncedSpellData().ele;
    }

    default int getLifeInTicks() {
        return getSyncedSpellData().lifeInTicks;
    }

    default void dealSpellDamageTo(LivingEntity target, boolean knockback) {

        EntitySpellData data = getSyncedSpellData();

        LivingEntity caster = data.getCaster(target.world);

        BaseSpell spell = data.getSpell();

        EntityCap.UnitData casterData = Load.Unit(caster);

        int num = spell.getCalculation().getCalculatedValue(casterData);

        SpellDamageEffect dmg = new SpellDamageEffect(caster, target, num, casterData, Load.Unit(target),
                                                      data.getSpell()
        );

        if (knockback == false) {
            dmg.removeKnockback();
        }

        dmg.element = spell.getElement();
        dmg.Activate();

    }

}
