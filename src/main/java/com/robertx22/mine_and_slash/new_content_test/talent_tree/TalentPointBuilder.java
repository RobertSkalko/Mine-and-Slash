package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TalentPointBuilder {

    public static ID create(String id) {
        return new ID(id);
    }

    public static class ID {

        TalentPoint talent;

        public ID(String guid) {
            talent = new TalentPoint(guid);
        }

        public POS setPos(int x, int y) {
            return new POS(talent, x, y);
        }

    }

    public static class POS {

        TalentPoint talent;

        public POS(TalentPoint talent, int x, int y) {
            this.talent = talent;
            this.talent.x = x;
            this.talent.y = y;
        }

    }

    public static class RENDER {

        TalentPoint talent;

        public RENDER(TalentPoint talent, Item item) {
            this.talent = talent;
            this.talent.renderStack = new ItemStack(item);
        }

    }

}
