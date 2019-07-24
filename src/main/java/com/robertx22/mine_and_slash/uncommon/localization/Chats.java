package com.robertx22.mine_and_slash.uncommon.localization;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;

public enum Chats implements IAutoLocName {
    NoSpaceForPortal("No Space for the portals. Please make room."),
    Dev_tools_enabled_contact_the_author("Devs tools enabled, if you see this please contact the author of Mine and Slash [robertx22], he forgot to disable them!"),
    Ran_Out_Of_Time("Ran Out Of Time"),
    You_have_attuned_to_this_Altar("You have attuned to this Altar"),
    Infusion_Failed_Horribly("Infusion Failed Horribly"),
    Not_enough_time("Not enough time"),
    Infusion_Failed("Infusion Failed"),
    Infusion_was_incredibly_Successful("Infusion was incredibly Successful"),
    A_Piece_of_gear_is_too_high_level_for_you("A Piece of gear is too high level for you"),
    Map_time_penalty_activated("Map time penalty activated"),
    Player_died_in_a_map_world("Player died in a map world"),
    You_have_ran_out_of_time("You have ran out of time"),
    Teleporting_back("Teleporting back"),
    No_targets_found("No targets found"),
    You_are_too_low_level("You are too low level"),
    Cooldown_not_met("Cooldown not met"),
    Weapon_durability_is_too_low("Weapon durability is too low"),
    You_have_leveled_up("You have leveled up"),
    No_bed_found("No bed found"),
    Teleport_canceled_due_to_movement("Teleport canceled due to movement"),
    You_are_not_inside_a_map_world("You are not inside a map world"),
    has_died_by_the_hands_of("has died by the hands of"),
    Not_attuned_to_any_altars("Not attuned to any altars"),
    Distance_too_high_to_teleport("Distance too high to teleport"),
    Teleport_started("Teleport started"),
    Not_enough_experience("Not enough experience"),
    Time_ran_out_due_to_Permadeath("Time ran out due to Permadeath"),
    Remaining_Map_Time_is("Remaining Map Time is"),
    Can_not_go_over_maximum_level("Can not go over maximum level"),
    This_is_not_an_Attunement_Altar("This is not an Attunement Altar");

    private String localization = "";

    Chats(String str) {
        this.localization = str;

    }

    @Override
    public IAutoLocName.AutoLocGroup locNameGroup() {
        return AutoLocGroup.Chat_Messages;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".chat." + formattedGUID();
    }

    @Override
    public String locNameForLangFile() {
        return localization;
    }

    @Override
    public String GUID() {
        return this.name().toLowerCase();
    }
}
