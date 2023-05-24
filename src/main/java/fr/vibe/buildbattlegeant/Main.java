package fr.vibe.buildbattlegeant;

import com.onarandombox.MultiverseCore.MultiverseCore;
import fr.vibe.buildbattlegeant.listeners.BBGPlayerListeners;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Main extends JavaPlugin {

    private BBGState state;
    private List<Player> players = new ArrayList<>();
    private List<Location> spawns = new ArrayList<>();

    private List<World> Maps = new ArrayList<>();

    private List<String> tablo = new ArrayList<>();


    /**
     *     // ========================================== Tests ==========================================
     *     ScoreboardManager manager = Bukkit.getScoreboardManager();
     *     Scoreboard board = manager.getNewScoreboard();
     *     // =================================== creation des Teams ===================================
     *     Team Red = board.registerNewTeam("Rouge");
     *     Team Blue = board.registerNewTeam("Bleu");
     *     Team Yellow = board.registerNewTeam("Jaune");
     *     // =================================== creation des Teams ===================================
     */


    @Override
    public void onEnable() {

        tablo.add("1");
        tablo.add("2");
        tablo.add("3");
        tablo.add("4");


        setState(BBGState.WAITING);

        spawns.add(new Location(Bukkit.getWorld("world"), 0, 120, 0, 0f, 0f));
        spawns.add(new Location(Bukkit.getWorld("world"), 1, 120, 1, 0f, 0f));

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

    public List<Location> getSpawns(){
        return spawns;
    }

    public List<World> getMaps() {
        return Maps;
    }

    public List<String> getTablo() {
        return tablo;
    }
}
