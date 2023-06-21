package fr.vibe.buildbattlegeant.listeners;

import fr.vibe.buildbattlegeant.BBGState;
import fr.vibe.buildbattlegeant.Main;
import fr.vibe.buildbattlegeant.Teams;
import fr.vibe.buildbattlegeant.managers.PlayerManager;
import fr.vibe.buildbattlegeant.tasks.AutoStart;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Team;

import java.util.Collections;
import java.util.Objects;

public class BBGPlayerListeners implements Listener {

    private Main main;
    public BBGPlayerListeners(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player p = event.getPlayer();
        Location spawn = new Location(Bukkit.getWorld("world"), 1, 85, 2, 0f, 0f);

        ItemStack teams = new ItemStack(Material.COMPASS, 1);
        ItemMeta meta = teams.getItemMeta();

        meta.setDisplayName("§6Choix de team");
        meta.setLore(Collections.singletonList("§eClic-droit pour choisir ta team"));

        teams.setItemMeta(meta);

        if (main.isState(BBGState.WAITING)) {
            p.teleport(spawn);
            p.setFoodLevel(20);
            p.setHealth(20);
            p.getInventory().clear();
            p.getInventory().setItem(4, teams);
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

    Inventory inv = new TeamGuiListener().TeamGui();

    @EventHandler
    public void onInteract(PlayerInteractEvent event){

        Player p = event.getPlayer();
        Action action = event.getAction();
        ItemStack current = event.getItem();

        if (current == null) return;

        if (current.getType() == Material.COMPASS && Objects.equals(current.getItemMeta().getDisplayName(), "§6Choix de team")){

            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){

                p.openInventory(inv);
            }
        }
    }

    private PlayerManager playerManager;

    @EventHandler
    public void onClick(InventoryClickEvent e){

        Inventory invCurr = e.getInventory();
        ItemStack clickItem = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();

        if (clickItem == null) return;

        if (invCurr.equals(inv)){

            e.setCancelled(true);
            p.closeInventory();

            switch (clickItem.getType()){
                case RED_WOOL:
                    p.closeInventory();
                    p.sendMessage("Vous êtes dans la team §cRouge");
                    Teams.addTeam(Teams.red, p);
                    break;
                case BLUE_WOOL:
                    p.closeInventory();
                    p.sendMessage("Vous êtes dans la team §9Bleue");
                    Teams.addTeam(Teams.blue, p);
                    break;
                case YELLOW_WOOL:
                    p.closeInventory();
                    p.sendMessage("Vous êtes dans la team §eJaune");
                    Teams.addTeam(Teams.yellow, p);
                    break;
                case LIME_WOOL:
                    p.closeInventory();
                    p.sendMessage("Vous êtes dans la team §aVerte");
                    Teams.addTeam(Teams.green, p);
                    break;
            }
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player p = event.getPlayer();

        Bukkit.broadcastMessage("§c" + p.getName() + " §aa quitté la partie §8<§c" + main.getPlayers().size() + "§7/§2" + Bukkit.getMaxPlayers() + "§8>");

        if (main.isState(BBGState.STARTING) && main.getPlayers().size() < 2){
            main.setState(BBGState.WAITING);
            for (Player pls : main.getPlayers()){
                pls.setLevel(0);
            }
        }
    }
}
