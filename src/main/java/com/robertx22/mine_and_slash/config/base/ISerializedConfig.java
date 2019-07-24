package com.robertx22.mine_and_slash.config.base;

public interface ISerializedConfig {

    public abstract String fileName();

    public abstract String folder();

    public abstract void generateIfEmpty();

    public abstract void load();

    public default String getPath() {
        return folder() + "/" + fileName();
    }

}
