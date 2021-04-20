package net.survivalcube.cubevanish;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public class Cubevanish extends JavaPlugin {
    ArrayList<Player> vanished = new ArrayList<Player>();
    //ScoreboardManager manager = Bukkit.getScoreboardManager();
    //Scoreboard board;
    //Team team;

    @Override
    public void onEnable() {
    getLogger().info("Loading CubeVanish");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player onlinep : Bukkit.getOnlinePlayers()) {
                if (vanished.contains(onlinep)) onlinep.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "You are vanished!"));
            }
        }, 20L, 20L);
    //{ board = manager.getNewScoreboard(); }
   // team.setPrefix("§7[§cV§7]§f ");
    this.getCommand("vanish").setExecutor(new VanishCommand(this));
    this.getCommand("fancyvanish").setExecutor(new LightningVanish(this));
    getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
    getLogger().info("Loaded CubeVanish!");
    }

    public void onDisable() {
    getLogger().info("Disabling CubeVanish");
    getLogger().info("CubeVanish disabled.");
    }
}
