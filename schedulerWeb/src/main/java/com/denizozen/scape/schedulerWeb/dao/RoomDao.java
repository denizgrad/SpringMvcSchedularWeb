package com.denizozen.scape.schedulerWeb.dao;

import java.util.List;

import com.denizozen.scape.schedulerWeb.model.Room;

public interface RoomDao {
	void addRoom(Room room);
	Room getRoom(int id);
	void deleteRoom(int id);
	List<Room> getRooms();
}
