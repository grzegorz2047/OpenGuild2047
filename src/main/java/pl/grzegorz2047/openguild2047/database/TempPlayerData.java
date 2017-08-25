package pl.grzegorz2047.openguild2047.database;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by grzeg on 25.08.2017.
 */
public class TempPlayerData {

    private final Plugin plugin;
    private ConcurrentHashMap<UUID, SQLRecord> data = new ConcurrentHashMap<>();

    public TempPlayerData(Plugin plugin) {
        this.plugin = plugin;
    }

    public void addSQLRecord(UUID uuid, String guild, String lastSeenName, int elo, int kills, int deaths) {
        data.put(uuid, new SQLRecord(guild, lastSeenName, elo, kills, deaths));
    }

    public SQLRecord getPlayerRecord(UUID uuid) {
        return data.get(uuid);
    }

    public void removePlayer(UUID uuid) {
        data.remove(uuid);
    }

}
