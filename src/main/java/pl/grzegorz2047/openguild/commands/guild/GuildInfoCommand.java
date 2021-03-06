/*
 * Copyright 2014
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.grzegorz2047.openguild.commands.guild;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.grzegorz2047.openguild.guilds.Guilds;
import pl.grzegorz2047.openguild.guilds.Guild;
import pl.grzegorz2047.openguild.commands.command.Command;
import pl.grzegorz2047.openguild.commands.command.CommandException;
import pl.grzegorz2047.openguild.managers.MsgManager;

/**
 * This command shows informations about specified or players' guild.
 * <p>
 * Usage: /guild info [optional: tag (if you're member of a guild)]
 */
public class GuildInfoCommand extends Command {
    private final Guilds guilds;

    public GuildInfoCommand(String[] aliases, Guilds guilds) {
        super(aliases);
        setPermission("openguild.command.info");
        this.guilds = guilds;
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {

        if (args.length == 2) {
            String guildToCheck = args[1].toUpperCase();

            if (!guilds.doesGuildExists(guildToCheck)) {
                sender.sendMessage(MsgManager.get("guilddoesntexists"));
                return;
            }

            Guild guild = guilds.getGuild(guildToCheck);
            sender.sendMessage(this.getTitle(MsgManager.getIgnorePref("ginfotit").replace("{GUILD}", guild.getName().toUpperCase())));
            sender.sendMessage(MsgManager.getIgnorePref("ginfodesc").replace("{DESCRIPTION}", guild.getDescription()));
            sender.sendMessage(MsgManager.getIgnorePref("ginfoleader").replace("{LEADER}", Bukkit.getOfflinePlayer(guild.getLeader()).getName()));
            sender.sendMessage(MsgManager.getIgnorePref("ginfomemlist").replace("{SIZE}", String.valueOf(guild.getMembers().size())).replace("{MEMBERS}", getMembers(guild.getMembers())));
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage(MsgManager.get("cmdonlyforplayer"));
                return;
            }

            Player player = (Player) sender;
            if (!guilds.hasGuild(player)) {
                player.sendMessage(MsgManager.get("usageinfo"));
                return;
            }

            Guild guild = guilds.getPlayerGuild(player.getUniqueId());

            sender.sendMessage(this.getTitle(MsgManager.getIgnorePref("ginfotit").replace("{GUILD}", guild.getName().toUpperCase())));
            sender.sendMessage(MsgManager.getIgnorePref("ginfodesc").replace("{DESCRIPTION}", guild.getDescription()));
            sender.sendMessage(MsgManager.getIgnorePref("ginfoleader").replace("{LEADER}", Bukkit.getOfflinePlayer(guild.getLeader()).getName()));
            sender.sendMessage(MsgManager.getIgnorePref("ginfomemlist").replace("{SIZE}", String.valueOf(guild.getMembers().size())).replace("{MEMBERS}", getMembers(guild.getMembers())));
        }
    }

    private String getMembers(List<UUID> uuids) {
        StringBuilder builder = new StringBuilder();
        for (UUID uuid : uuids) {
            builder.append(Bukkit.getOfflinePlayer(uuid).getName());
            builder.append(", ");
        }
        return builder.toString();
    }

    @Override
    public int minArgs() {
        return 1;
    }

}