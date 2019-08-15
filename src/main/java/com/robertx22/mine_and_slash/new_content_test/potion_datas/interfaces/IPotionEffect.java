package com.robertx22.mine_and_slash.new_content_test.potion_datas.interfaces;

import com.robertx22.mine_and_slash.new_content_test.potion_datas.PotionContext;

public interface IPotionEffect {

    public enum Type {
        OnAttacked,
        OnAttack,
        Tick,
        StatGive
    }

    void doEffect(PotionContext ctx);

    Type getType();

}
