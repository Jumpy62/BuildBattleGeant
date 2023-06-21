package fr.vibe.buildbattlegeant.managers;

import fr.vibe.buildbattlegeant.Teams;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    private Map<UUID, Teams> playerTeams;

    public PlayerManager(){
        this.playerTeams = new HashMap<>();
    }

    public void addPlayerToTeam(Player player, Teams team){
        playerTeams.put(player.getUniqueId(), team);
        team.addMember(player);
    }

    public void removePlayerFromTeam(Player player){
        if (isPlayerInTeam(player)){
            Teams team = getPlayerTeam(player);
            playerTeams.remove(player.getUniqueId());
            team.removeMember(player);
        }
    }

    public boolean isPlayerInTeam(Player player){
        return playerTeams.containsKey(player.getUniqueId());
    }

    public Teams getPlayerTeam(Player player){
        return playerTeams.get(player.getUniqueId());
    }
}
