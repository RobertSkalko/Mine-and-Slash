package com.robertx22.mine_and_slash.database.spells;

import com.robertx22.mine_and_slash.database.spells.entities.bases.ISpellEntity;
import com.robertx22.mine_and_slash.database.spells.entities.bases.ServerEntitySpellData;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpellEffect;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.DamageData;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class SpellUtils {

    public static Entity getSpellEntity(Entity spellEntity,

                                        BaseSpellEffect effect,

                                        SpellItemData spellItemData,

                                        LivingEntity caster

    ) {

        ISpellEntity se = (ISpellEntity) spellEntity;

        BaseSpell spell = spellItemData.getSpell();
        DamageData data = new DamageData(caster, spellItemData);
        int lifeInTicks = se.getDefaultLifeInTicks();

        ServerEntitySpellData serverData = new ServerEntitySpellData(effect, data, spell);
        EntitySpellData syncData = new EntitySpellData(spell, caster, lifeInTicks);

        se.setServerSpellData(serverData);
        se.setSyncedSpellData(syncData);

        return spellEntity;

    }

}
