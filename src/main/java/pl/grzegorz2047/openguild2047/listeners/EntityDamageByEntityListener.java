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

package pl.grzegorz2047.openguild2047.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;
import pl.grzegorz2047.openguild2047.GenConf;
import pl.grzegorz2047.openguild2047.OpenGuild;
import pl.grzegorz2047.openguild2047.SimpleGuild;
import pl.grzegorz2047.openguild2047.managers.MsgManager;

public class EntityDamageByEntityListener implements Listener {

    public OpenGuild plugin;
    
    public EntityDamageByEntityListener(OpenGuild plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void handleEvent(EntityDamageByEntityEvent event) {
        if(event.isCancelled() || GenConf.teampvp){
            return;
        }
        
        if(event.getDamager() instanceof Player) {
            if(event.getEntity() instanceof Player) {
                Player attacked = (Player) event.getEntity();
                Player attacker = (Player) event.getDamager();
                
                if(plugin.getGuildHelper().hasGuild(attacked) && plugin.getGuildHelper().hasGuild(attacker)) {
                    SimpleGuild attackerGuild = plugin.getGuildHelper().getPlayerGuild(attacker.getUniqueId());
                    if(attackerGuild.containsMember(attacked.getUniqueId())) {
                        event.setCancelled(true);
                        
                        if(GenConf.TEAMPVP_MSG) {
                            attacker.sendMessage(MsgManager.get("pvpguildmember", "&cNie mozesz uderzyc gracza sojuszniczej gildii"));
                        }
                    }
                }
            }
        }
        else if(event.getDamager() instanceof Arrow) {
            if(event.getEntity() instanceof Player) {
                Player attacked = (Player) event.getEntity();
                ProjectileSource attackerEntity = ((Arrow) event.getDamager()).getShooter();
                
                if(attackerEntity instanceof Player) {
                    Player attacker = (Player) attackerEntity;
                    
                    if(plugin.getGuildHelper().hasGuild(attacked) && plugin.getGuildHelper().hasGuild(attacker)) {
                        SimpleGuild attackerGuild = plugin.getGuildHelper().getPlayerGuild(attacker.getUniqueId());
                        if(attackerGuild.containsMember(attacked.getUniqueId())) {
                            event.setCancelled(true);

                            if(GenConf.TEAMPVP_MSG) {
                                attacker.sendMessage(MsgManager.get("pvpguildmember", "&cNie mozesz uderzyc gracza sojuszniczej gildii"));
                            }
                        }
                    }
                }
            }
        }
    }
}