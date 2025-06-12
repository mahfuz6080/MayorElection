// File: MayorNPC.java package me.arcx.mayor.npc;

import net.minecraft.server.v1_8_R3.*; import org.bukkit.Bukkit; import org.bukkit.craftbukkit.v1_8_R3.CraftServer; import org.bukkit.craftbukkit.v1_8_R3.CraftWorld; import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer; import org.bukkit.entity.Player;

import java.util.UUID;

public class MayorNPC { private final EntityPlayer npc;

public MayorNPC(String name, double x, double y, double z, float yaw, float pitch) {
    MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
    WorldServer world = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
    GameProfile profile = new GameProfile(UUID.randomUUID(), name);

    npc = new EntityPlayer(server, world, profile, new PlayerInteractManager(world));
    npc.setLocation(x, y, z, yaw, pitch);
}

public void spawn(Player player) {
    PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
    connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
    connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
}

}

// More classes will follow in separate sections as requested.

