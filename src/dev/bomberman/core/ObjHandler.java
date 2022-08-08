package dev.bomberman.core;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import dev.bomberman.object.CollisionObject;
import dev.bomberman.object.GameObject;
import dev.bomberman.object.TickObject;


public class ObjHandler {
	
	public List<GameObject> gameObj;
	public List<TickObject> tickObj;
	public List<CollisionObject> collObj;
	
	public ObjHandler(){
		gameObj = new LinkedList<>();
		tickObj = new LinkedList<>();
		collObj = new LinkedList<>();
	}
	
	
	//SETTERS AND GETTERS [...]
	public void addGameObj(GameObject obj){
		gameObj.add(obj);
	}
	public void removeGameObj(GameObject obj){
		gameObj.remove(obj);
	}
	public void addTickObj(TickObject obj){
		tickObj.add(obj);
		addGameObj(obj);
	}
	public void removeTickObj(TickObject obj){
		tickObj.remove(obj);
		removeGameObj(obj);
	}
	public void addCollObj(CollisionObject obj){
		collObj.add(obj);
		addGameObj(obj);
	}
	public void addCollObjAll(Collection<CollisionObject> objs){
		collObj.addAll(objs);
		gameObj.addAll(objs);
	}
	public void removeCollObj(CollisionObject obj){
		collObj.remove(obj);
		removeGameObj(obj);
	}
}
