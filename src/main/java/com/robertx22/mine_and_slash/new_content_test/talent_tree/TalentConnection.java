package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import java.util.Objects;

public class TalentConnection {

    public TalentConnection(Allocation status, TalentPoint one, TalentPoint two) {
        this.allocationStatus = status;
        this.one = one;
        this.two = two;

    }

    private TalentPoint one, two;

    public enum Allocation {
        CAN_ALLOCATE,
        CANT_ALLOCATE,
        ALLOCATED
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TalentConnection)) {
            return false;
        }
        return Objects.equals(one, two);
    }

    @Override
    public int hashCode() {
        return Objects.hash(one.GUID(), two.GUID());
    }

    public Allocation allocationStatus = Allocation.CANT_ALLOCATE;

}
