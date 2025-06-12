package me.arcx.mayor;

import net.citizensnpcs.api.CitizensAPI; import net.citizensnpcs.api.npc.NPC; import net.citizensnpcs.api.trait.Trait; import org.bukkit.Bukkit; import org.bukkit.ChatColor; import org.bukkit.Location; import org.bukkit.plugin.java.JavaPlugin;

public class MayorElection extends JavaPlugin { @Override public void onEnable() { getLogger().info("Mayor Election plugin has been enabled.");

// Create NPC
    Bukkit.getScheduler().runTaskLater(this, () -> {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(
                org.bukkit.entity.EntityType.PLAYER, "MayorCandidate");

        Location spawnLocation = new Location(Bukkit.getWorld("world"), 5, 70, -3);
        npc.spawn(spawnLocation);
        npc.getEntity().setCustomName(ChatColor.YELLOW + "Candidate: " + ChatColor.GREEN + "Diana");
        npc.getEntity().setCustomNameVisible(true);
    }, 20L);
}

@Override
public void onDisable() {
    getLogger().info("Mayor Election plugin has been disabled.");
}

}

