package me.arcx.mayor.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        Inventory inventory = event.getInventory();
        if (inventory == null || !event.getView().getTitle().equals("§6§lMayor Election")) return;

        event.setCancelled(true); // Prevent item movement

        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || !clicked.hasItemMeta()) return;

        String displayName = clicked.getItemMeta().getDisplayName();
        if (displayName == null || !displayName.startsWith("§e")) return;

        String mayorName = displayName.replace("§e", "");
        player.sendMessage("§aYou voted for §b" + mayorName + "§a!");
        player.closeInventory();

        // Optional: Save vote to config
    }
}
