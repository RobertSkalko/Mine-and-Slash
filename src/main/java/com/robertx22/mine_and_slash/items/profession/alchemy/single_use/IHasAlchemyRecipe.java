package com.robertx22.mine_and_slash.items.profession.alchemy.single_use;

import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;

public interface IHasAlchemyRecipe extends IHasRecipe {
    @Override
    default Professions proffesion() {
        return Professions.ALCHEMY;
    }
}
