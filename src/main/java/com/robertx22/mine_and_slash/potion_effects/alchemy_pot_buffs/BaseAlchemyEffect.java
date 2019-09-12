package com.robertx22.mine_and_slash.potion_effects.alchemy_pot_buffs;

import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BaseBuffPotion;
import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;

public class BaseAlchemyEffect extends BaseEffect {

    public BaseAlchemyEffect(BaseBuffPotion pot) {
        super(pot.GUID(), "Alchemy Buff", pot.level.number, pot.mods());
    }

    @Override
    public Professions proffesion() {
        return Professions.ALCHEMY;
    }
}
