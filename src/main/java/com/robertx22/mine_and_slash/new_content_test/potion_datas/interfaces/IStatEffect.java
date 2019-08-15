package com.robertx22.mine_and_slash.new_content_test.potion_datas.interfaces;

public interface IStatEffect extends IPotionEffect {
    default Type getType() {
        return Type.StatGive;
    }

}
