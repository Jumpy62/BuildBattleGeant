package fr.vibe.buildbattlegeant.tasks;

import fr.vibe.buildbattlegeant.BBGState;
import fr.vibe.buildbattlegeant.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameCycle extends BukkitRunnable {

    public Main main;
    private int timer = 120;

    public GameCycle(Main main){
        this.main = main;
    }

    @Override
    public void run() {

        switch (timer){

            case 43200:
                Bukkit.broadcastMessage("Il reste 12 h !");
                break;

            case 21600:
                Bukkit.broadcastMessage("Il reste 6 h !");
                break;

            case 3600:
                Bukkit.broadcastMessage("Il reste 1 h !");
                break;

            case 1800:
                Bukkit.broadcastMessage("Il reste 30 min !");
                break;

            case 900:
                Bukkit.broadcastMessage("Il reste 15 min !");
                break;

            case 300:
                Bukkit.broadcastMessage("Il reste 5 min !");
                break;

            case 60:
                Bukkit.broadcastMessage("Il reste 1 min !");
                break;

            case 30:
                Bukkit.broadcastMessage("Il reste 30 sec !");
                break;

            case 10:
                Bukkit.broadcastMessage("Il reste 10 sec !");
                break;

            case 5:
                Bukkit.broadcastMessage("Il reste 5 sec !");
                break;

            case 4:
                Bukkit.broadcastMessage("Il reste 4 sec !");
                break;

            case 3:
                Bukkit.broadcastMessage("Il reste 3 sec !");
                break;

            case 2:
                Bukkit.broadcastMessage("Il reste 2 sec !");
                break;

            case 1:
                Bukkit.broadcastMessage("Il reste 1 sec !");
                break;

            case 0:
                cancel();
                Bukkit.broadcastMessage("Fin du Build Battle Geant");
                main.setState(BBGState.FINISH);

                for (int i = 0; i < main.getPlayers().size(); i++){

                    Player p = main.getPlayers().get(i);
                    World world = main.getServer().getWorld("world");

                    p.teleport(new Location(world , 1, 85, 2, 0f, 0f));

                }
        }
        timer--;
    }
}
