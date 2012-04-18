package me.arno.blocklog;

public enum Log {
	BREAK(0),
	PLACE(1),
	FIRE(2),
	EXPLOSION(3),
	LEAVES(4),
	GROW(5),
	PORTAL(6),
	FORM(7),
	SPREAD(8),
	FADE(9),
	EXPLOSION_CREEPER(10),
	EXPLOSION_GHAST(11),
	EXPLOSION_TNT(12);
	
	int id;
	Log(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Log getType() {
		return this;
	}
}
