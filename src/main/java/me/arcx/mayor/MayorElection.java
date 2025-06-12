// File: MayorElection.java
package me.arcx.mayor;

import me.arcx.mayor.npc.MayorNPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MayorElection extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("[MayorElection] Plugin enabled");

        // Register command for testing
        getCommand("spawnmayor").setExecutor((sender, command, label, args) -> {
            if (!(sender instanceof Player)) return false;
            Player player = (Player) sender;

            MayorNPC npc = new MayorNPC("Mayor_" + player.getName(), player.getLocation().getX() + 1, player.getLocation().getY(), player.getLocation().getZ(), 0f, 0f);
            npc.spawn(player);
            player.sendMessage("Spawned test mayor NPC!");
            return true;
        });
    }

    @Override
    public void onDisable() {
        getLogger().info("[MayorElection] Plugin disabled");
    }
}
