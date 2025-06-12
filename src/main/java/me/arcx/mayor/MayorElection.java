package me.arcx.mayor;

import me.arcx.mayor.election.ElectionManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MayorElection extends JavaPlugin {

    private ElectionManager electionManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.electionManager = new ElectionManager(this);

        getCommand("spawnmayors").setExecutor((sender, command, label, args) -> {
            if (sender instanceof Player) {
                electionManager.spawnMayors((Player) sender);
                return true;
            }
            sender.sendMessage("This command is for players only.");
            return false;
        });

        getLogger().info("[MayorElection] Enabled successfully.");
    }

    @Override
    public void onDisable() {
        getLogger().info("[MayorElection] Disabled.");
    }
}
