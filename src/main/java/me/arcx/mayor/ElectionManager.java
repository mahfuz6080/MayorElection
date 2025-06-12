package me.arcx.mayor;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.*;

public class ElectionManager {
    private final List<Mayor> selectedMayors = new ArrayList<>();
    private final Map<String, Integer> votes = new HashMap<>();
    private final Map<Mayor, NPC> npcMap = new HashMap<>();

    private final List<Location> spawnLocations = Arrays.asList(
        new Location(Bukkit.getWorld("world"), -12, 70, -42),
        new Location(Bukkit.getWorld("world"), -14, 70, -45),
        new Location(Bukkit.getWorld("world"), -10, 70, -45),
        new Location(Bukkit.getWorld("world"), -8, 70, -42),
        new Location(Bukkit.getWorld("world"), -6, 70, -44)
    );

    public void startElection(List<Mayor> allMayors) {
        Collections.shuffle(allMayors);
        selectedMayors.clear();
        selectedMayors.addAll(allMayors.subList(0, 5));

        spawnNPCs();
    }

    private void spawnNPCs() {
        for (int i = 0; i < selectedMayors.size(); i++) {
            Mayor mayor = selectedMayors.get(i);
            Location loc = spawnLocations.get(i);
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(org.bukkit.entity.EntityType.PLAYER, mayor.name);
            npc.spawn(loc);
            npc.setName("§6Mayor " + mayor.name);
            SkinTrait skin = npc.getOrAddTrait(SkinTrait.class);
            skin.setSkinName(mayor.skin);
            npcMap.put(mayor, npc);
        }
    }

    public List<Mayor> getMayors() {
        return selectedMayors;
    }

    public void vote(String player, Mayor mayor) {
        votes.put(player, selectedMayors.indexOf(mayor));
    }

    public Mayor getWinner() {
        int[] counts = new int[selectedMayors.size()];
        for (int i : votes.values()) counts[i]++;
        int max = 0, index = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > max) {
                max = counts[i];
                index = i;
            }
        }
        return selectedMayors.get(index);
    }
}
