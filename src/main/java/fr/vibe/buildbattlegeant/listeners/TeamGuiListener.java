package fr.vibe.buildbattlegeant.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class TeamGuiListener implements Listener {

    Inventory inv;

    public Inventory TeamGui(){
        inv = Bukkit.createInventory(null, 9, "Teams");

        inizializeItems();

        return inv;
    }

    public void inizializeItems(){
        inv.addItem(createGuiItem(Material.RED_WOOL, "§cTeam Rouge"));
    }

    protected ItemStack createGuiItem(final Material material, final String name){

        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        item.setItemMeta(meta);

        return item;

    }

    public void openInventory(final HumanEntity ent){
        ent.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e){
        if (!e.getInventory().equals(inv)) return;

        e.setCancelled(true);

        final ItemStack clickItem = e.getCurrentItem();

        if (clickItem == null || clickItem.getType().isAir()) return;

        final Player p = (Player) e.getWhoClicked();

        p.sendMessage("Vous êtes dans une team");
    }

    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e){
        if (e.getInventory().equals(inv)){
            e.setCancelled(true);
        }
    }

}
