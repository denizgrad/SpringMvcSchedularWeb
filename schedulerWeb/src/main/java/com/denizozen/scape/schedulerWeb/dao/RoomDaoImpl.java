package com.denizozen.scape.schedulerWeb.dao;

import java.util.List;

import com.denizozen.scape.schedulerWeb.model.Room;
/**
 * @author deniz.ozen
 */
public class RoomDaoImpl extends BaseDao implements RoomDao{

	@Override
	public void addRoom(Room room) {
		addModel(room);
	}

	@Override
	public Room getRoom(int id) {
		return getCurrentSession().get(Room.class, id);
	}

	@Override
	public void deleteRoom(int id) {
		deleteModel(Room.class, id);
	}

	@Override
	public List<Room> getRooms() {
		return getModels(Room.class);
	}

}
