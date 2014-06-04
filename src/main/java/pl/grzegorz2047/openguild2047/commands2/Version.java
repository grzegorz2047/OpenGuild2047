/*
 * The MIT License
 *
 * Copyright 2014 Aleksander.
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

package pl.grzegorz2047.openguild2047.commands2;

import com.github.grzegorz2047.openguild.command.Command;
import com.github.grzegorz2047.openguild.command.CommandException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import pl.grzegorz2047.openguild2047.OpenGuild;

public class Version implements Command {
    
    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        sender.sendMessage(ChatColor.DARK_GRAY + " ----------------- " + ChatColor.GOLD + "OpenGuild2047" + ChatColor.DARK_GRAY + " ----------------- ");
        sender.sendMessage(ChatColor.DARK_GRAY + "Version: " + ChatColor.GOLD + OpenGuild.get().getDescription().getVersion());
        sender.sendMessage(ChatColor.DARK_GRAY + "Authors: " + ChatColor.GOLD + "grzegorz2047 & TheMolkaPL");
        sender.sendMessage(ChatColor.GOLD + "See all contributors at https://github.com/grzegorz2047/OpenGuild2047/graphs/contributors");
        sender.sendMessage(ChatColor.DARK_GRAY + "GitHub: " + ChatColor.GOLD + "https://github.com/grzegorz2047/OpenGuild2047");
        sender.sendMessage(ChatColor.DARK_GRAY + "BukkitDev: " + ChatColor.GOLD + "http://dev.bukkit.org/bukkit-plugins/openguild");
        sender.sendMessage(ChatColor.DARK_GRAY + "MCStats: " + ChatColor.GOLD + "http://mcstats.org/plugin/OpenGuild2047");
    }
    
}