package me.arcx.mayor;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class InventoryListener implements Listener {

    private final ElectionManager electionManager;

    public InventoryListener(ElectionManager electionManager) {
        this.electionManager = electionManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equals("§6Mayor Election")) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta()) return;

            String name = event.getCurrentItem().getItemMeta().getDisplayName().replace("§aVote for ", "");
            electionManager.setVote(player.getUniqueId(), name);
            player.sendMessage("§aYou voted for §e" + name);
            player.closeInventory();
        }
    }
}
