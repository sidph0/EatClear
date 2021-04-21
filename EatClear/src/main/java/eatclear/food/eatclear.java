import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.java.JavaPlugin;
//Adds Listener, checks if plugin started
public class eatclear extends JavaPlugin implements Listener,CommandExecutor {
    public static boolean isStarted = false;

    public eatclear() {
    }
//Creating variables a, b, c, and d
    public boolean onCommand(CommandSender a, Command b, String c, String[] d) {
        if (d[0].equals("start")) {
            isStarted = true;
//Creating messages for when plugin is started and stopped
            Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "Plugin Started!");
        } else {
            if (!d[0].equals("stop")) {
                return false;
            }

            isStarted = false;
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "Plugin Stopped!");
        }

        return true;

    }
//gives command functionability
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("eatclear").setExecutor(this);

    }
//When you eat food, your items get cleared
    @EventHandler
    public void onEat(PlayerItemConsumeEvent a) {
//Excluding potions and milk because those count as consumed items, when we only want food to clear your inventory
        if(isStarted && !a.getItem().getType().equals(Material.POTION) && !a.getItem().getType().equals(Material.MILK_BUCKET)) {
            Location b = a.getPlayer().getLocation();
            Player c = a.getPlayer();
            c.getInventory().clear();
        }


    }
}
//hi khanh
