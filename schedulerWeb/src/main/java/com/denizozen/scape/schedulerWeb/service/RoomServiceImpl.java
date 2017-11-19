package com.denizozen.scape.schedulerWeb.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.denizozen.scape.schedulerWeb.model.Room;
import com.denizozen.scape.schedulerWeb.model.Study;
@Service
@Transactional
public class RoomServiceImpl implements RoomService{

	@Override
	public Study getRoom(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getRooms() {
		// TODO Auto-generated method stub
		return null;
	}



}
