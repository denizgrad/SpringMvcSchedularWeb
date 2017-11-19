package com.denizozen.scape.schedulerWeb.service;

import java.util.List;

import javax.persistence.NoResultException;

import com.denizozen.scape.schedulerWeb.model.Room;
import com.denizozen.scape.schedulerWeb.model.Study;

public interface RoomService {

	/**
	 * 
	 * @param id
	 * @return
	 * @throws NoResultException
	 *             if member not exists with id
	 */
	public Study getRoom(int id);

	public List<Room> getRooms();
}
