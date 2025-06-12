package me.arcx.mayor;

import java.util.List;

public class Mayor {
    public String name;
    public String skin;
    public List<String> perks;

    public Mayor(String name, String skin, List<String> perks) {
        this.name = name;
        this.skin = skin;
        this.perks = perks;
    }
}
