package me.arcx.mayor;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class MayorElection extends JavaPlugin implements Listener {

    private ElectionManager manager;
    private MayorGUI gui;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        manager = new ElectionManager();
        gui = new MayorGUI(manager);
        Bukkit.getPluginManager().registerEvents(this, this);
        getCommand("election").setExecutor((sender, cmd, label, args) -> {
            if (!(sender instanceof org.bukkit.entity.Player p)) return false;
            gui.open(p);
            return true;
        });

        List<Mayor> allMayors = loadMayors();
        manager.startElection(allMayors);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof org.bukkit.entity.Player p) {
            gui.onClick(p, e);
        }
    }

    private List<Mayor> loadMayors() {
        FileConfiguration cfg = getConfig();
        List<Mayor> list = new ArrayList<>();
        for (String key : cfg.getConfigurationSection("mayors").getKeys(false)) {
            String skin = cfg.getString("mayors." + key + ".skin");
            List<String> perks = cfg.getStringList("mayors." + key + ".perks");
            list.add(new Mayor(key, skin, perks));
        }
        return list;
    }
}
