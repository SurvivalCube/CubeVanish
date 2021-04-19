package net.survivalcube.cubevanish;

import org.bukkit.Bukkit;
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
    //{ board = manager.getNewScoreboard(); }
   // team.setPrefix("§7[§cV§7]§f ");
    this.getCommand("vanish").setExecutor(new VanishCommand(this));
    getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
    getLogger().info("Loaded CubeVanish!");
    }

    public void onDisable() {
    getLogger().info("CubeVanish disabled.");
    }
}
