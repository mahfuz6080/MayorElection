package me.arcx.mayor.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MayorGUI {

    private final List<String> mayors;

    public MayorGUI(List<String> mayors) {
        this.mayors = mayors;
    }

    public void open(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9, "§6§lMayor Election");

        for (int i = 0; i < mayors.size() && i < 5; i++) {
            String name = mayors.get(i);
            ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§e" + name);
            meta.setLore(List.of("§7Click to vote for", "§b" + name));
            item.setItemMeta(meta);

            gui.setItem(1 + i * 2, item); // Space them out: slot 1, 3, 5, etc.
        }

        player.openInventory(gui);
    }

    public void handleClick(Player player, InventoryClickEvent event) {
        event.setCancelled(true);
        ItemStack item = event.getCurrentItem();
        if (item == null || !item.hasItemMeta()) return;

        String selected = item.getItemMeta().getDisplayName().replace("§e", "");
        player.sendMessage("§aYou voted for §b" + selected + "§a!");
        player.closeInventory();
        // Save vote or trigger vote logic here
    }
}
