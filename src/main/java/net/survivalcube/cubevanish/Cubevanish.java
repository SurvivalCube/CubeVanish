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
import java.util.Collections;
import java.util.Objects;

public class Cubevanish extends JavaPlugin {
    public Scoreboard s;
    public Team t;
    ArrayList<Player> vanished = new ArrayList<Player>();

    @Override
    public void onEnable() {
    getLogger().info("Loading CubeVanish");
    loadConfig();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player onlinep : Bukkit.getOnlinePlayers()) {
                if (vanished.contains(onlinep) && Objects.equals(getConfig().get("display-actionbar"), true)) onlinep.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "You are vanished!"));
            }
        }, 20L, 20L);
    s = Bukkit.getScoreboardManager().getMainScoreboard();
    t = s.registerNewTeam("vanished");
    t.setPrefix(ChatColor.DARK_GRAY+"["+ChatColor.RED+"V"+ChatColor.DARK_GRAY+"] "+ChatColor.RESET);
    this.getCommand("vanish").setExecutor(new VanishCommand(this));
    this.getCommand("fancyvanish").setExecutor(new LightningVanish(this));
    this.getCommand("vanishedplayers").setExecutor(new VanishedPlayersCommand(this));
    this.getCommand("joinvanished").setExecutor(new JoinVanishedCommand(this));
    getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
    getServer().getPluginManager().registerEvents(new LeaveEvent(this), this);
    getLogger().info("Loaded CubeVanish!");
    }

    public void onDisable() {
    getLogger().info("Disabling CubeVanish");
    for (int i = 0; i < vanished.size(); i++){
        Player curr = vanished.get(i);
        t.removeEntry(curr.getName());
    }
    getLogger().info("CubeVanish disabled.");
    }
    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
