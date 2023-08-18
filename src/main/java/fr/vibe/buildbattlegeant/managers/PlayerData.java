package fr.vibe.buildbattlegeant.managers;

import fr.vibe.buildbattlegeant.BTeam;

public class PlayerData {

    private BTeam team;

    public BTeam getTeam(){
        return team;
    }

    public void setTeam(BTeam team){
        this.team = team;
    }
}
