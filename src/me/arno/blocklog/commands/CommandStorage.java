package me.arno.blocklog.commands;

import java.util.ArrayList;

import me.arno.blocklog.BlockLog;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandStorage extends BlockLogCommand {
	public CommandStorage(BlockLog plugin) {
		super(plugin, "blocklog.storage");
	}

	public boolean execute(Player player, Command cmd, ArrayList<String> listArgs) {
		String[] args = (String[]) listArgs.toArray();
		if(args.length > 0) {
			player.sendMessage(ChatColor.WHITE + "/bl storage");
			return true;
		}
		
		if(!hasPermission(player)) {
			player.sendMessage("You don't have permission");
			return true;
		}
		
		if(player == null) {
			log.info(String.format("The internal storage contains %s block(s)!", plugin.blocks.size()));
			log.info(String.format("The internal storage contains %s interaction(s)!", plugin.interactions.size()));
		} else {
			player.sendMessage(String.format(ChatColor.DARK_RED +"[BlockLog] " + ChatColor.GOLD + "The internal storage contains %s block(s)!", plugin.blocks.size()));
			player.sendMessage(String.format(ChatColor.DARK_RED +"[BlockLog] " + ChatColor.GOLD + "The internal storage contains %s interaction(s)!", plugin.interactions.size()));
		}
		return true;
	}
}
