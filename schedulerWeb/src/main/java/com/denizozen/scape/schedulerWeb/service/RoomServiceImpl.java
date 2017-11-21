package com.denizozen.scape.schedulerWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.denizozen.scape.schedulerWeb.dao.RoomDao;
import com.denizozen.scape.schedulerWeb.model.Room;
@Service
@Transactional
public class RoomServiceImpl implements RoomService{

	@Autowired
	RoomDao roomDao;
	
	@Override
	public Room getRoom(int id) {
		return roomDao.getRoom(id);
	}

	@Override
	public List<Room> getRooms() {
		return roomDao.getRooms();
	}



}
