package net.survivalcube.cubevanish;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LightningVanish implements CommandExecutor {
    Cubevanish plugin;

    public LightningVanish(Cubevanish plugin) {
        this.plugin = plugin;
    }
    private String noeff = ChatColor.RED + "You need to specify a valid effect! Available effects: lightning";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED+"Only players can vanish!");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(noeff);
            return true;
        }
        Player p = (Player) sender;
        if (args[0].equalsIgnoreCase("lightning")) {
            p.getWorld().strikeLightningEffect(p.getLocation());
            //new VanishCommand(plugin);
            Bukkit.getServer().dispatchCommand(p, "vanish"); // well, it works :P
        } else {
            p.sendMessage(noeff);
            return true;
        }

        return true;
    }
}
