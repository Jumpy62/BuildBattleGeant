package fr.vibe.buildbattlegeant;

import fr.vibe.buildbattlegeant.listeners.BBGPlayerListeners;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Main extends JavaPlugin {

    private BBGState state;
    private List<Player> players = new ArrayList<>();


    public static HashMap<String, List<Player>> teams = new HashMap<>();


    @Override
    public void onEnable() {

        teams.put("rouge", new ArrayList<>());
        teams.put("bleue", new ArrayList<>());
        teams.put("jaune", new ArrayList<>());
        teams.put("verte", new ArrayList<>());


        setState(BBGState.WAITING);

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new BBGPlayerListeners(this), this);

    }

    public void setState(BBGState state){
        this.state = state;
    }

    public boolean isState(BBGState state){
        return this.state == state;
    }

    public List<Player> getPlayers(){
        return players;
    }

}
