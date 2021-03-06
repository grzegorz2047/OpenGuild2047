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

package pl.grzegorz2047.openguild.randomtp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import pl.grzegorz2047.openguild.managers.MsgManager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class RandomTPHandler {


    private static Random random = new Random();
    private List<Material> unsafeMaterials = Arrays.asList(Material.LAVA, Material.WATER);
    private int RANDOM_TP_RANGE;
    private boolean RANDOM_TP_ENABLED;
    private boolean USE_BUTTON_FOR_RANDOM_TP;

    public void enable(Plugin plugin) {
        FileConfiguration config = plugin.getConfig();
        RANDOM_TP_RANGE = config.getInt("random-tp.range", 3000);
        RANDOM_TP_ENABLED = config.getBoolean("random-tp.enabled", false);
        USE_BUTTON_FOR_RANDOM_TP = config.getBoolean("random-tp.button", true);

        if (RANDOM_TP_ENABLED) {
            Bukkit.getPluginManager().registerEvents(new RandomTPListeners(this), plugin);
        }
    }


    public boolean isEnabled() {
        return RANDOM_TP_ENABLED;
    }


    public boolean isButtonEnabled() {
        return USE_BUTTON_FOR_RANDOM_TP;
    }


    /*  public void setEnabled(boolean enabled) {
          GenConf.RANDOM_TP_ENABLED = enabled;
      }

      public void setButtonEnabled(boolean enabled) {
          GenConf.USE_BUTTON_FOR_RANDOM_TP = enabled;
      }
  */
    public void teleport(Player player) {
        Location location;
        try {
            location = findSaveSpot(player);
            player.teleport(location);
            player.sendMessage(MsgManager.get("rantp"));
        } catch (Exception e) {
            player.sendMessage(MsgManager.get("nosafertp"));
        }
    }


    private Location findSaveSpot(Player player) throws Exception {
        for (int i = 0; i < 10; i++) {
            World world = player.getWorld();
            int x = random.nextInt(RANDOM_TP_RANGE);
            int z = random.nextInt(RANDOM_TP_RANGE);
            if (random.nextBoolean())
                x = x - (2 * x);
            if (random.nextBoolean())
                z = z - (2 * z);
            Location randomisedLocation = new Location(world, x, 64, z);
            Location highestRandomisedLocation = world.getHighestBlockAt(randomisedLocation).getLocation();
            Location highestMinusOneRandomisedLocation = world.getHighestBlockAt(randomisedLocation).getLocation();
            highestMinusOneRandomisedLocation.setY(highestMinusOneRandomisedLocation.getY() - 1);

            if (!unsafeMaterials.contains(highestRandomisedLocation.getBlock().getType()) && !unsafeMaterials.contains(highestMinusOneRandomisedLocation.getBlock().getType())) {
                return highestRandomisedLocation;
            }
        }
        throw new Exception("No safe place found");
    }


}
