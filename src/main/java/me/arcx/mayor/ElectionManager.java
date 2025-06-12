package me.arcx.mayor;

import me.arcx.mayor.gui.MayorGUI;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElectionManager {

    private final List<String> allMayors = List.of(
        "Diana", "Cole", "Paul", "Derpy", "Marina",
        "Jerry", "Aatrox", "Diaz", "Foxy", "Finnegan",
        "Technoblade", "Barry"
    );

    public void openMayorGUI(Player player) {
        List<String> selectedMayors = getRandomMayors(5);
        MayorGUI gui = new MayorGUI(selectedMayors);
        gui.open(player);
    }

    private List<String> getRandomMayors(int count) {
        List<String> copy = new ArrayList<>(allMayors);
        Collections.shuffle(copy);
        return copy.subList(0, Math.min(count, copy.size()));
    }
}
