package me.arcx.mayor;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public class MayorElection extends JavaPlugin {

    private ElectionManager electionManager;

    @Override
    public void onEnable() {
        this.electionManager = new ElectionManager(this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(electionManager), this);
        getCommand("mayorvote").setExecutor(new MayorGUI(electionManager));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
