package com.robertx22.mine_and_slash.database.rarities.base;

import net.minecraft.util.SoundEvent;

public class DropSoundData {

    public DropSoundData() {

    }

    public DropSoundData(SoundEvent sound) {
        this.sound = sound;
    }

    public DropSoundData volume(float volume) {
        this.volume = volume;
        return this;
    }

    public DropSoundData pitch(float pitch) {
        this.pitch = pitch;
        return this;
    }

    public boolean hasSound() {
        return sound != null;
    }

    public SoundEvent sound;
    public float volume = 1;
    public float pitch = 1;

}
