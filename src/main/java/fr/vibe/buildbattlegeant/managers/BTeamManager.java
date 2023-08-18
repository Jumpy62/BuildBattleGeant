package fr.vibe.buildbattlegeant.managers;

import fr.vibe.buildbattlegeant.BTeam;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class BTeamManager {
    private Map<String, BTeam> teams;

    public BTeamManager(){
        this.teams = new HashMap<>();
    }

    public BTeam getTeam(String name){
        return teams.get(name);
    }

    public void addPlayerToTeam(Player player, BTeam team){
        team.addMember(player);
    }

    public void removePlayerFromTeam(Player player, BTeam team){
        team.removeMember(player);
    }

}
