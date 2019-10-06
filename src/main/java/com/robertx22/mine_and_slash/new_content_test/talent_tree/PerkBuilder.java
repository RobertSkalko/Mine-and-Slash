package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.db_lists.initializers.Perks;

public class PerkBuilder {

    public static Guid create(String id) {
        return new Guid(id);
    }

    public static class Guid {

        private Perk talent;

        public Guid(String guid) {
            talent = new Perk(guid);
        }

        public Position pos(int x, int y) {
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

        public Effect effect(PerkEffect effect) {
            return new Effect(talent, effect);
        }

        public Connections copy(Perk other) {
            return this.effect(other.effect).connections();
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

        public Connections add(Perk other) {
            this.talent.tryConnectTo(other);
            return this;
        }

        public Perk build() {
            Perks.all.add(talent);
            return talent;
        }

    }

}
