package me.arcx.mayor;

import me.arcx.mayor.election.ElectionManager;
import me.arcx.mayor.gui.InventoryListener;
import org.bukkit.plugin.java.JavaPlugin;

public class MayorElection extends JavaPlugin {

    private static MayorElection instance;
    private ElectionManager electionManager;

    @Override
    public void onEnable() {
        instance = this;
        this.electionManager = new ElectionManager(this);

        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getCommand("mayorvote").setExecutor((sender, command, label, args) -> {
            if (sender instanceof org.bukkit.entity.Player player) {
                electionManager.openMayorGUI(player);
            }
            return true;
        });
    }

    public static MayorElection getInstance() {
        return instance;
    }

    public ElectionManager getElectionManager() {
        return electionManager;
    }
}
