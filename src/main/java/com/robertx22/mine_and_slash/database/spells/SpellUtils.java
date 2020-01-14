package com.robertx22.mine_and_slash.database.spells;

import com.robertx22.mine_and_slash.database.spells.entities.bases.ISpellEntity;
import com.robertx22.mine_and_slash.database.spells.entities.bases.ServerEntitySpellData;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpellEffect;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.DamageData;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import net.minecraft.entity.Entity;

public class SpellUtils {

    public static Entity getSpellEntity(Entity spellEntity,

                                        BaseSpellEffect effect,

                                        DamageData data,

                                        BaseSpell spell) {

        ServerEntitySpellData serverData = new ServerEntitySpellData(effect, data, spell);
        EntitySpellData syncData = new EntitySpellData(spell);

        ISpellEntity se = (ISpellEntity) spellEntity;

        se.setServerSpellData(serverData);
        se.setSyncedSpellData(syncData);

        return spellEntity;

    }

}
