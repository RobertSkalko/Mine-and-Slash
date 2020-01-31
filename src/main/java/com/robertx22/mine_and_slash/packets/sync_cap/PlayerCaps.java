package com.robertx22.mine_and_slash.packets.sync_cap;

import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonPlayerCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.PlayerEntity;

public enum PlayerCaps {

    ENTITY_DATA {
        @Override
        public ICommonPlayerCap getCap(PlayerEntity player) {
            return Load.Unit(player);
        }
    },
    MAP_DATA {
        @Override
        public ICommonPlayerCap getCap(PlayerEntity player) {
            return Load.playerMapData(player);
        }
    },
    PROFESSIONS {
        @Override
        public ICommonPlayerCap getCap(PlayerEntity player) {
            return Load.professions(player);
        }
    },
    TALENTS {
        @Override
        public ICommonPlayerCap getCap(PlayerEntity player) {
            return Load.talents(player);
        }
    },
    SPELLS {
        @Override
        public ICommonPlayerCap getCap(PlayerEntity player) {
            return Load.spells(player);
        }
    },
    QUESTS {
        @Override
        public ICommonPlayerCap getCap(PlayerEntity player) {
            return Load.quests(player);
        }
    },
    STAT_POINTS {
        @Override
        public ICommonPlayerCap getCap(PlayerEntity player) {
            return Load.statPoints(player);
        }
    };

    PlayerCaps() {

    }

    public abstract ICommonPlayerCap getCap(PlayerEntity player);

}
