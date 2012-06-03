package me.arno.blocklog.managers;

import java.util.ArrayList;

import org.bukkit.block.BlockState;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.arno.blocklog.logs.BlockEntry;
import me.arno.blocklog.logs.LogType;
import me.arno.blocklog.logs.BlockEdit;

public class QueueManager extends BlockLogManager {
	private final ArrayList<BlockEntry> blockEdits = new ArrayList<BlockEntry>();
	
	/**
	 * Logs a block edit by the environment.
	 * This can be either a block that has been created or a block that has been destroyed
	 * 
	 * @param block {@link BlockState} of the block that got destroyed
	 * @param type {@link LogType} of the log
	 */
	public void queueBlockEdit(BlockState block, LogType type) {
		queueBlockEdit(null, block, EntityType.UNKNOWN, type);
	}
	
	/**
	 * Logs a block edit by a player.
	 * This can be either a block that has been created or a block that has been destroyed
	 * 
	 * @param player The player that triggered the event
	 * @param block {@link BlockState} of the block that got destroyed
	 * @param type {@link LogType} of the log
	 */
	public void queueBlockEdit(Player player, BlockState block, LogType type) {
		queueBlockEdit(player, block, EntityType.PLAYER, type);
	}
	
	/**
	 * Logs a block edit by an entity other than a player.
	 * This can be either a block that has been created or a block that has been destroyed
	 * 
	 * @param block {@link BlockState} of the block that got destroyed
	 * @param entity {@link EntityType} of the entity that triggered this event
	 * @param type {@link LogType} of the log
	 */
	public void queueBlockEdit(BlockState block, EntityType entity, LogType type) {
		queueBlockEdit(null, block, entity, type);
	}
	
	/**
	 * Logs a block edit by an entity that got triggered by a player.
	 * This can be either a block that has been created or a block that has been destroyed
	 * 
	 * @param player The {@link Player} that triggered the event
	 * @param block {@link BlockState} of the block that got destroyed
	 * @param entity {@link EntityType} of the entity that triggered this event
	 * @param type {@link LogType} of the log
	 */
	public void queueBlockEdit(Player player, BlockState block, EntityType entity, LogType type) {
		blockEdits.add(new BlockEntry(player, entity, type, block));
	}
	
	/**
	 * Gets a list of unsaved block edits
	 * 
	 * @return An {@link ArrayList} containing all the unsaved block edits
	 */
	public ArrayList<BlockEntry> getEditQueue() {
		return blockEdits;
	}
	
	/**
	 * Gets the amount of unsaved block edits
	 * 
	 * @return An integer value that represents the amount of unsaved block edits
	 */
	public int getEditQueueSize() {
		return blockEdits.size();
	}
	
	/**
	 * Gets a queued block edit
	 * 
	 * @param index The index of the queued block edit
	 * @return A {@link BlockEdit} object that represents the queued block edit
	 */
	public BlockEntry getQueuedBlockEdit(int index) throws IndexOutOfBoundsException {
		return getEditQueue().get(index);
	}
	
	/**
	 * Gets the oldest queued block edit
	 * 
	 * @return A {@link BlockEdit} object that represents the queued block edit
	 */
	public BlockEntry getOldestQueuedBlockEdit() {
		return getEditQueue().get(0);
	}
	
	/**
	 * Saves the oldest queued block edit
	 */
	public void saveQueuedEdit() {
		saveQueuedEdit(0);
	}
	
	/**
	 * Saves a queued block edit
	 * 
	 * @param index The index of the queued block interaction
	 */
	public void saveQueuedEdit(int index) {
		if(getEditQueueSize() > index) {
			BlockEntry blockEdit = getEditQueue().get(index);
			if(blockEdit != null) {
				blockEdit.save();
				getEditQueue().remove(index);
			}
		}
	}
}
