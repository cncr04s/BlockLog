package me.arno.blocklog.logs;

import java.sql.SQLException;
import java.sql.Statement;

import me.arno.blocklog.BlockLog;
import me.arno.blocklog.Log;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;


public class LoggedBlock {
	private BlockLog plugin;
	private Log logType;
	
	private Integer block_id;
	private Byte datavalue;
	
	private Player player;
	private BlockState block;
	private Location location;
	private World world;
	
	private long date;
	
	private Integer rollback = 0;
	
	public LoggedBlock(BlockLog plugin, Player player, MaterialData block, Location location, int type) {
		this.plugin = plugin;
		this.player = player;
		this.location = location;
		this.world = location.getWorld();
		this.block_id = block.getItemTypeId();
		this.datavalue = block.getData(); // Material Data
		this.date = System.currentTimeMillis()/1000;
		this.logType = Log.values()[type];
	}
	
	public LoggedBlock(EnvironmentBlock block) {
		this.plugin = block.plugin;
		this.block = block.getBlock();
		this.block_id = block.getBlock().getTypeId();
		this.datavalue = block.getData().getData();
		this.location = block.getLocation();
		this.world = block.getWorld();
		this.date = block.getDate();
		this.logType = block.getType();
	}
	
	public LoggedBlock(BrokenBlock block) {
		this.plugin = block.plugin;
		this.player = block.getPlayer();
		this.block = block.getBlock();
		this.block_id = block.getId();
		this.datavalue = block.getData().getData();
		this.location = block.getLocation();
		this.world = block.getWorld();
		this.date = block.getDate();
		this.logType = Log.BREAK;
	}
	
	public LoggedBlock(PlacedBlock block) {
		this.plugin = block.plugin;
		this.player = block.getPlayer();
		this.block = block.getBlock();
		this.block_id = block.getId();
		this.datavalue = block.getData().getData();
		this.location = block.getLocation();
		this.world = block.getWorld();
		this.date = block.getDate();
		this.logType = Log.PLACE;
	}
	
	public LoggedBlock(GrownBlock block) {
		this.plugin = block.plugin;
		this.player = block.getPlayer();
		this.block = block.getBlock();
		this.block_id = block.getId();
		this.datavalue = block.getData().getData();
		this.location = block.getLocation();
		this.world = block.getWorld();
		this.date = block.getDate();
		this.logType = Log.GROW;
	}

	public LoggedBlock(PortalBlock block) {
		this.plugin = block.plugin;
		this.player = block.getPlayer();
		this.block = block.getBlock();
		this.block_id = block.getId();
		this.datavalue = block.getData().getData();
		this.location = block.getLocation();
		this.world = block.getWorld();
		this.date = block.getDate();
		this.logType = Log.PORTAL;
	}

	public void save() {
		try {
			Statement stmt = plugin.conn.createStatement();
			stmt.executeUpdate("INSERT INTO blocklog_blocks (player, block_id, datavalue, world, date, x, y, z, type, rollback_id) VALUES ('" + getPlayerName() + "', " + getBlockId() + ", " + getDataValue() + ", '" + getWorld().getName() + "', " + getDate() + ", " + getX() + ", " + getY() + ", " + getZ() + ", " + getTypeId() + ", " + getRollback() + ")");
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
	}
	
	public int getBlockId() {
		return block_id;
	}
	
	public byte getDataValue() {
		return datavalue;
	}
	
	public BlockState getBlock() {
		return block;
	}
	
	public World getWorld() {
		return world;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public String getPlayerName() {
		return (player == null) ? "Environment" : player.getName();
	}
	
	public int getRollback() {
		return rollback;
	}
	
	public void setRollback(int id) {
		this.rollback = id;
	}
	
	public long getDate() {
		return date;
	}
	
	public Log getType() {
		return logType;
	}
	
	public int getTypeId() {
		return logType.getId();
	}
	
	public int getX()
	{
		return location.getBlockX();
	}
	
	public int getY()
	{
		return location.getBlockY();
	}
	
	public int getZ()
	{
		return location.getBlockZ();
	}
}