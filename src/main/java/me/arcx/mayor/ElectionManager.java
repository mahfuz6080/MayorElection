package me.arcx.mayor.election;

import me.arcx.mayor.npc.MayorNPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class ElectionManager {

    private final JavaPlugin plugin;
    private final List<String> allMayors;

    public ElectionManager(JavaPlugin plugin) {
        this.plugin = plugin;
        FileConfiguration config = plugin.getConfig();
        this.allMayors = config.getStringList("mayors");
    }

    public List<String> getRandomMayors(int count) {
        List<String> copy = new ArrayList<>(allMayors);
        Collections.shuffle(copy);
        return copy.subList(0, Math.min(count, copy.size()));
    }

    public void spawnMayors(Player viewer) {
        List<String> selectedMayors = getRandomMayors(5);

        List<Location> locations = Arrays.asList(
                new Location(Bukkit.getWorld("world"), -36.5, 71, -44.5),
                new Location(Bukkit.getWorld("world"), -34.5, 71, -44.5),
                new Location(Bukkit.getWorld("world"), -32.5, 71, -44.5),
                new Location(Bukkit.getWorld("world"), -30.5, 71, -44.5),
                new Location(Bukkit.getWorld("world"), -28.5, 71, -44.5)
        );

        for (int i = 0; i < selectedMayors.size(); i++) {
            String name = selectedMayors.get(i);
            Location loc = locations.get(i);
            MayorNPC npc = new MayorNPC(name, loc.getX(), loc.getY(), loc.getZ(), 0f, 0f);
            npc.spawn(viewer);
        }

        viewer.sendMessage("Spawned 5 random mayors.");
    }
}
