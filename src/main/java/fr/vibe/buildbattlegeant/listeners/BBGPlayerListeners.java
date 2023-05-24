package fr.vibe.buildbattlegeant.listeners;

import fr.vibe.buildbattlegeant.BBGState;
import fr.vibe.buildbattlegeant.Main;
import fr.vibe.buildbattlegeant.tasks.AutoStart;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BBGPlayerListeners implements Listener {

    private Main main;
    public BBGPlayerListeners(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player p = event.getPlayer();
        Location spawn = new Location(Bukkit.getWorld("world"), 1, 85, 2, 0f, 0f);

        if (main.isState(BBGState.WAITING)) {
            p.teleport(spawn);
            p.setFoodLevel(20);
            p.setHealth(20);
            p.getInventory().clear();
        } else {
            p.setGameMode(GameMode.SPECTATOR);
            p.sendMessage("§k| §cLe BuildBattle a déjà commencé ):§k |");
            return;
        }

        if (!main.getPlayers().contains(p)){
            main.getPlayers().add(p);
        }
        p.setGameMode(GameMode.ADVENTURE);
        event.setJoinMessage("§7[§9Build Battle Geant§7] §6" + p.getName() + " §aa rejoint la partie §8<§2" + main.getPlayers().size() + "§7/§2" + Bukkit.getMaxPlayers() + "§8>");

        if (main.isState(BBGState.WAITING) && main.getPlayers().size() >= 2){
            AutoStart start = new AutoStart(main);
            start.runTaskTimer(main, 0, 20);
            main.setState(BBGState.STARTING);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player p = event.getPlayer();

        if (main.isState(BBGState.STARTING) && main.getPlayers().size() < 2){
            main.setState(BBGState.WAITING);
            for (Player pls : main.getPlayers()){
                pls.setLevel(0);
            }
        }
    }
}
