package dev.bomberman.object;

public enum ID {	//every GameObj is defined with an ID
		//controllable
	Player   (true ,false),
	Enemy	 (false,false),
	SolidTile(false,true ),
	BreakTile(false,false),
	Bomb	 (false,true ),
	Explosion(false,true ),
   Background(false,true);
	
	
	public boolean controlled;
	public boolean invulnerable;
	
	ID(boolean controlled, boolean invulnerable){
		this.controlled=controlled;
		this.invulnerable=invulnerable;
	}
}
