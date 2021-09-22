package me.Tyloon1e.MyDoctor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Heal implements CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if (label.equalsIgnoreCase("mydoctor") || label.equalsIgnoreCase("doctor")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Nice try...");
				return true;
			}

			Player player = (Player) sender;

			if (!player.hasPermission("doctor.use")) {
				player.sendMessage(ChatColor.RED + "You don't have permission!");
				return true; // we kick them out if they don't have permission
			}

			if (arguments.length == 0) /* /doctor */ {
				TextComponent message = new TextComponent("Would you like to be healed?"); // important to use Bungee API for this
				message.setColor(ChatColor.GOLD);
				message.setBold(true);
				message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/doctor healme"));
				message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click here to be healed, yay!").color(ChatColor.GRAY).italic(true).create()));

				player.spigot().sendMessage(message);
				return true;
			}

			if (arguments[0].equalsIgnoreCase("healme")) {
				player.setHealth(20); // method 1
				player.setSaturation(20);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "heal " + player.getName()); // method 2, run through console

				player.sendMessage(ChatColor.GREEN + "You've been healed.");
				return true;
			}

			player.sendMessage(ChatColor.RED + "Usage: /doctor");
			return true;
		}
		return false;
	}

}
