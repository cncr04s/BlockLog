package me.arno.blocklog.log;

import me.arno.blocklog.BlockLog;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class PlacedBlock {
	public BlockLog plugin;
	
	private Player player;
	private Block block;
	
	private long date;
	
	public PlacedBlock(BlockLog plugin, Player player, Block block) {
		this.plugin = plugin;
		this.player = player;
		this.block = block;
		this.date = System.currentTimeMillis()/1000;
	}
	
	public void push() {
		plugin.blocks.add(new LoggedBlock(this));
	}
	
	public int getId() {
		return block.getTypeId();
	}
	
	public Block getBlock() {
		return block;
	}
	
	public World getWorld() {
		return block.getWorld();
	}
	
	public Location getLocation() {
		return block.getLocation();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public String getPlayerName() {
		return player.getName();
	}
	
	public long getDate() {
		return date;
	}

	public int getData() {
		return block.getData();
	}
}