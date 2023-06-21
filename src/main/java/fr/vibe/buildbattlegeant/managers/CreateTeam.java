package fr.vibe.buildbattlegeant.managers;

import fr.vibe.buildbattlegeant.Teams;
import org.bukkit.entity.Player;

public class CreateTeam {

    private PlayerManager playerManager;
    public void createTeam(String teamName, Player player){
        Teams team = new Teams(teamName);
        playerManager.addPlayerToTeam(player, team);
    }
}
