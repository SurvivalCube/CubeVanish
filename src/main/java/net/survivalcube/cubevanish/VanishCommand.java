package net.survivalcube.cubevanish;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
//import net.spigotmc.tagapi.api.TagAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.awt.*;

public class VanishCommand implements CommandExecutor {

    Cubevanish plugin;

    public VanishCommand(Cubevanish plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (sender.hasPermission("survivalcube.vanish")) {
                if (plugin.vanished.contains(p)) {
                    for (Player people : Bukkit.getOnlinePlayers()) {
                        people.showPlayer(plugin, p);
                        if (people.hasPermission("survivalcube.vanish")) {
                            people.sendMessage(ChatColor.GREEN+p.getName()+" unvanished.");
                        }
                    }
                    plugin.vanished.remove(p);
                    plugin.t.removeEntry(p.getName());
                    //plugin.team.removePlayer(p);
                    //TagAPI.getInstance().setTag(p, "", "", 100);
                } else if (!plugin.vanished.contains(p)) {
                    for (Player people : Bukkit.getOnlinePlayers()) {
                        if (!people.hasPermission("survivalcube.vanish")) {
                            people.hidePlayer(plugin, p);
                        } else {
                            people.sendMessage(ChatColor.GREEN+p.getName()+" vanished.");
                        }
                    }
                    plugin.vanished.add(p);
                    plugin.t.addEntry(p.getName());
                    // p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "You are vanished!"));
                    //plugin.team.addPlayer(p);
                    //TagAPI.getInstance().setTag(p, "§7[§aVANISHED§7] §f", "", 100);
                }
            } else {
                sender.sendMessage("Unknown command. Type \"/help\" for help.");
            }
        } else sender.sendMessage("Only players can vanish!");

        return true;
    }
}
