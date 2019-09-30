package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TalentPointBuilder {

    public static Guid create(String id) {
        return new Guid(id);
    }

    public static class Guid {

        private TalentPoint talent;

        public Guid(String guid) {
            talent = new TalentPoint(guid);
        }

        public Position setPos(int x, int y) {
            return new Position(talent, x, y);
        }

    }

    public static class Position {

        private TalentPoint talent;

        public Position(TalentPoint talent, int x, int y) {
            this.talent = talent;
            this.talent.x = x;
            this.talent.y = y;
        }

        public Render setRender(Item item) {
            return new Render(talent, item);
        }

    }

    public static class Render {

        private TalentPoint talent;

        public Render(TalentPoint talent, Item item) {
            this.talent = talent;
            this.talent.renderStack = new ItemStack(item);
        }

        public Effect setEffect(TalentPointEffect effect) {
            return new Effect(talent, effect);
        }

    }

    public static class Effect {

        private TalentPoint talent;

        public Effect(TalentPoint talent, TalentPointEffect effect) {
            this.talent = talent;
            talent.effect = effect;
        }

        public Connections finish() {
            return new Connections(talent);
        }

    }

    public static class Connections {

        private TalentPoint talent;

        public Connections(TalentPoint talent) {
            this.talent = talent;
        }

        public Connections addConnection(TalentPoint other) {
            this.talent.connectTo(other);
            return this;
        }

        public TalentPoint build() {
            return talent;
        }

    }

}
