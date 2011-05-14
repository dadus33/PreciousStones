package net.sacredlabyrinth.Phaed.PreciousStones.managers;

import net.sacredlabyrinth.Phaed.PreciousStones.PreciousStones;

import com.nijikokun.bukkit.Permissions.Permissions;
import com.nijiko.permissions.PermissionHandler;

import org.bukkit.plugin.Plugin;
import org.bukkit.entity.Player;

/**
 *
 * @author phaed
 */
public final class PermissionsManager
{
    /**
     *
     */
    public static PermissionHandler Permissions = null;
    private PreciousStones plugin;

    /**
     *
     * @param plugin
     */
    public PermissionsManager(PreciousStones plugin)
    {
	this.plugin = plugin;

	startPermissions();
    }

    /**
     *
     * @param player
     * @param permission
     * @return
     */
    public boolean hasPermission(Player player, String permission)
    {
	if (player == null)
	{
	    return false;
	}

	if (hasPermissionPlugin())
	{
            if (Permissions.has(player, "preciousstones.blacklist"))
            {
                return false;
            }

	    return Permissions.has(player, permission);
	}
	else
	{
	    if (player.isOp())
	    {
		return true;
	    }
	    else
	    {
		if (permission.contains("benefit"))
		{
		    return true;
		}
		else if (permission.contains("whitelist"))
		{
		    return true;
		}

		return false;
	    }
	}
    }

    private boolean hasPermissionPlugin()
    {
	return Permissions != null;
    }

    /**
     *
     */
    public void startPermissions()
    {
	if (PermissionsManager.Permissions == null)
	{
	    Plugin test = plugin.getServer().getPluginManager().getPlugin("Permissions");

	    if (test != null)
	    {
		if (!plugin.getServer().getPluginManager().isPluginEnabled(test))
		{
		    plugin.getServer().getPluginManager().enablePlugin(test);
		}

		PermissionsManager.Permissions = ((Permissions) test).getHandler();
	    }
	}
    }
}
