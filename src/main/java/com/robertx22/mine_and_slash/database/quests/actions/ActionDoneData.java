package com.robertx22.mine_and_slash.database.quests.actions;

public abstract class ActionDoneData {

    public int groupMembers = 1;

    public ActionDoneData(int group) {
        this.groupMembers = group;
    }

    public float getGroupCompletitionMultiplier() {
        return 1F / (float) groupMembers;
    }

}
