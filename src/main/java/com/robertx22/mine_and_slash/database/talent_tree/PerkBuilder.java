package com.robertx22.mine_and_slash.database.talent_tree;

import com.robertx22.mine_and_slash.database.spells.spell_tree.SpellPerk;

public class PerkBuilder {

    public static Guid create(String id) {
        return new Guid(id);
    }

    public static SpellGuid createSpell(String id) {
        return new SpellGuid(id);
    }

    public static class SpellGuid {

        private SpellPerk talent;

        public SpellGuid(String guid) {
            talent = new SpellPerk(guid);
        }

        public Position pos(int x, int y) {
            return new Position(talent, x, y);
        }

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

        private BasePerk talent;

        public Position(BasePerk talent, int x, int y) {
            this.talent = talent;
            this.talent.x = x;
            this.talent.y = y;
        }

        public Effect effect(BasePerkEffect effect) {
            return new Effect(talent, effect);
        }

        public Connections copy(Perk other) {
            return this.effect(other.effect).connections();
        }

    }

    public static class Effect {

        private BasePerk talent;

        public Effect(BasePerk talent, BasePerkEffect effect) {
            this.talent = talent;
            talent.effect = effect;
        }

        public Connections connections() {
            return new Connections(talent);
        }

    }

    public static class Connections {

        private BasePerk talent;

        public Connections(BasePerk talent) {
            this.talent = talent;
        }

        public Connections add(BasePerk other) {
            this.talent.tryConnectTo(other);
            return this;
        }

        public BasePerk build() {
            talent.registerToSlashRegistry();
            return talent;
        }

    }

}
