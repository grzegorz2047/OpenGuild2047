package pl.grzegorz2047.openguild2047.listeners;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import pl.grzegorz2047.openguild2047.antilogout.AntiLogoutManager;
import pl.grzegorz2047.openguild2047.configuration.GenConf;
import pl.grzegorz2047.openguild2047.cuboidmanagement.Cuboid;
import pl.grzegorz2047.openguild2047.cuboidmanagement.Cuboids;
import pl.grzegorz2047.openguild2047.database.SQLHandler;
import pl.grzegorz2047.openguild2047.dropstone.DropFromBlocks;
import pl.grzegorz2047.openguild2047.guilds.Guilds;
import pl.grzegorz2047.openguild2047.managers.TagManager;
import pl.grzegorz2047.openguild2047.teleporters.Teleporter;
import pl.grzegorz2047.openguild2047.teleporters.TpaRequester;

/**
 * Created by grzeg on 23.08.2017.
 */
public class ListenerLoader {

    private final Guilds guilds;
    private final TagManager tagManager;
    private final SQLHandler sqlHandler;
    private final Teleporter teleporter;
    private final TpaRequester tpaRequester;
    private final Cuboids cuboids;
    private final AntiLogoutManager logout;
    private final DropFromBlocks drop;
    private final Plugin p;

    public ListenerLoader(Plugin p, Guilds guilds, TagManager tagManager, SQLHandler sqlHandler, Teleporter teleporter, TpaRequester tpaRequester, Cuboids cuboids, AntiLogoutManager antiLogoutManager, DropFromBlocks dropFromBlocks) {
        this.guilds = guilds;
        this.tagManager = tagManager;
        this.sqlHandler = sqlHandler;
        this.teleporter = teleporter;
        this.tpaRequester = tpaRequester;
        this.cuboids = cuboids;
        this.logout = antiLogoutManager;
        this.drop = dropFromBlocks;
        this.p = p;
    }

    public void loadListeners(PluginManager pm) {
        pm.registerEvents(new PlayerJoinListener(guilds, tagManager, sqlHandler), p);
        pm.registerEvents(new PlayerChatListener(guilds), p);
        pm.registerEvents(new PlayerDeathListener(sqlHandler, logout), p);
        pm.registerEvents(new PlayerKickListener(teleporter, cuboids, tpaRequester, guilds), p);
        pm.registerEvents(new PlayerQuitListener(guilds, cuboids, logout, teleporter, tpaRequester), p);

        if (GenConf.cubEnabled) {
            pm.registerEvents(new CuboidAndSpawnManipulationListeners(cuboids, drop, guilds), p);
        }

        pm.registerEvents(new EntityDamageByEntityListener(logout, guilds), p);

        if (GenConf.playerMoveEvent) {
            pm.registerEvents(new PlayerMoveListener(cuboids), p);
        }
    }
}