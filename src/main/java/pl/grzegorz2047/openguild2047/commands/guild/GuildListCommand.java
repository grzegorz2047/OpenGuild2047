/*
 * The MIT License
 *
 * Copyright 2014 Adam.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package pl.grzegorz2047.openguild2047.commands.guild;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import pl.grzegorz2047.openguild2047.GuildHelper;
import com.github.grzegorz2047.openguild.Guild;
import com.github.grzegorz2047.openguild.command.Command;
import com.github.grzegorz2047.openguild.command.CommandException;
import pl.grzegorz2047.openguild2047.managers.MsgManager;

/**
 * This command shows list of guilds.
 * 
 * Usage: /guild list
 */
public class GuildListCommand extends Command {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        GuildHelper guildHelper = this.getPlugin().getGuildHelper();
        
        StringBuilder resultBuilder = new StringBuilder();
        for(Guild guild : guildHelper.getGuilds().values()) {
            String tag = guild.getTag();
            resultBuilder.append(tag).append(", ");
        }
        
        String result = resultBuilder.toString();
        
        sender.sendMessage(this.getTitle(MsgManager.getIgnorePref("titleguildlist")));
        resultBuilder.append(MsgManager.get("numguilds")).append(this.getPlugin().getGuildHelper().getGuilds().size());
        resultBuilder.append("\n");
        
        sender.sendMessage(result);
    }

    @Override
    public int minArgs() {
        return 1;
    }

}
