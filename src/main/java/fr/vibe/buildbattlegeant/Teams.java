package fr.vibe.buildbattlegeant;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Teams {
    private static Main main;

    private String name;
    private List<Player> members;

    public Teams(String name){
        this.name = name;
        this.members = new ArrayList<>();
    }

    public void addMember(Player player){
        members.add(player);
    }

    public void removeMember(Player player){
        members.remove(player);
    }
}
