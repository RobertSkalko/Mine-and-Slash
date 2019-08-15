package com.robertx22.mine_and_slash.new_content_test.potion_datas;

import com.robertx22.mine_and_slash.new_content_test.potion_datas.interfaces.IPotionEffect;

import java.util.ArrayList;
import java.util.List;

public class BaseDataPotion {

    private List<IPotionEffect> effects = new ArrayList<>();

    public void useEffects(PotionContext ctx) {
        for (IPotionEffect effect : effects) {
            if (effect.getType().equals(ctx.effectType)) {
                effect.doEffect(ctx);
            }
        }
    }

}
