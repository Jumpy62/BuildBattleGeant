package fr.vibe.buildbattlegeant.tasks;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import fr.vibe.buildbattlegeant.BBGState;
import fr.vibe.buildbattlegeant.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoStart extends BukkitRunnable {

    private int timer = 10;
    private Main main;

    public AutoStart(Main main){
        this.main = main;
    }

    @Override
    public void run() {



        for (Player pls : main.getPlayers()){
            pls.setLevel(timer);
        }

        if (main.getPlayers().size() < 2){
            cancel();
        }

        if (timer == 5 || timer == 3 || timer == 2 || timer == 1){
            Bukkit.broadcastMessage("§a§lDébut de la partie dans : §l§6" + timer + "§a§l s");
        }

        if (timer == 0){
            Bukkit.broadcastMessage("§e§lLancement du Buildbattle");
            main.setState(BBGState.PLAYING);

            MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");

            MVWorldManager worldManager = core.getMVWorldManager();

            for (int i = 0; i < main.getPlayers().size(); i++){

                Player p = main.getPlayers().get(i);

                worldManager.addWorld(p.getName(), World.Environment.NORMAL, null, WorldType.FLAT, false, null);

                MultiverseWorld teamWorld = worldManager.getMVWorld(p.getName());
                teamWorld.setGameMode(GameMode.CREATIVE);
                teamWorld.setDifficulty(Difficulty.PEACEFUL);
                teamWorld.setSpawnLocation(new Location(teamWorld.getCBWorld(), 0, -60, 0, 0f, 0f));

                WorldBorder wb = teamWorld.getCBWorld().getWorldBorder();
                wb.setWarningDistance(0);
                wb.setCenter(0, 0);
                wb.setSize(130);

                p.teleport(teamWorld.getSpawnLocation());

                worldManager.loadWorld(p.getName());

                p.sendTitle("§k§l| §eC'est parti ! §f§k§l|", "§9§nBon courage");

                p.getInventory().setItem(4, (new ItemStack(Material.DIAMOND_AXE, 1)));
                p.updateInventory();
            }

            GameCycle cycle = new GameCycle(main);
            cycle.runTaskTimer(main, 0, 20);
            cancel();
        }
        timer--;
    }
}