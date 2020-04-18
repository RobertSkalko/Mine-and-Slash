package com.robertx22.mine_and_slash.potion_effects.bases;

public interface IOneOfATypePotion {

    public enum Type {
        DIVINE_BUFF
    }

    public Type getOneOfATypeType();
}
