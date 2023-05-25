package fr.vibe.buildbattlegeant.commands;

import fr.vibe.buildbattlegeant.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RulesCommand implements CommandExecutor {

    private Main main;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player){

            Player p = (Player) sender;

            p.sendMessage("=====[ Build Battle Geant Regles ]=====");
            p.sendMessage("- World Edit Autorisé");
            p.sendMessage("- ...");
            p.sendMessage("=======================================");

        }

        return true;
    }
}
