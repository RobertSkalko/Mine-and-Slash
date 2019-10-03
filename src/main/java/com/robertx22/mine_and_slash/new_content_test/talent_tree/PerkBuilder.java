package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.db_lists.initializers.Perks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PerkBuilder {

    public static Guid create(String id) {
        return new Guid(id);
    }

    public static class Guid {

        private Perk talent;

        public Guid(String guid) {
            talent = new Perk(guid);
        }

        public Position setPos(int x, int y) {
            return new Position(talent, x, y);
        }

    }

    public static class Position {

        private Perk talent;

        public Position(Perk talent, int x, int y) {
            this.talent = talent;
            this.talent.x = x;
            this.talent.y = y;
        }

        public Render setRender(Item item) {
            return new Render(talent, item);
        }

        public Connections copy(Perk other) {
            return this.setRender(other.renderStack.getItem())
                    .setEffect(other.effect)
                    .connections();
        }

    }

    public static class Render {

        private Perk talent;

        public Render(Perk talent, Item item) {
            this.talent = talent;
            this.talent.renderStack = new ItemStack(item);
        }

        public Effect setEffect(PerkEffect effect) {
            return new Effect(talent, effect);
        }

    }

    public static class Effect {

        private Perk talent;

        public Effect(Perk talent, PerkEffect effect) {
            this.talent = talent;
            talent.effect = effect;
        }

        public Connections connections() {
            return new Connections(talent);
        }

    }

    public static class Connections {

        private Perk talent;

        public Connections(Perk talent) {
            this.talent = talent;
        }

        public Connections addConnection(Perk other) {
            this.talent.connectTo(other);
            return this;
        }

        public Perk build() {
            Perks.all.add(talent);
            return talent;
        }

    }

}
