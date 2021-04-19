package net.survivalcube.cubevanish;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Cubevanish extends JavaPlugin {
    ArrayList<Player> vanished = new ArrayList<Player>();

@Override
    public void onEnable() {
    getLogger().info("Loading CubeVanish...");
    this.getCommand("vanish").setExecutor(new VanishCommand(this));
    getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
    getLogger().info("Loaded CubeVanish!");
    }

    public void onDisable() {
    getLogger().info("CubeVanish disabled.");
    }
}
