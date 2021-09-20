package com.aeClub.service.impl;

import org.springframework.stereotype.Service;

import com.aeClub.model.WallType;
import com.aeClub.service.EditService;

@Service
public class EditServiceImpl implements EditService {

	@Override
	public WallType changeActiveWall(int newWallType) {
		if (newWallType >= 0 && newWallType <= 4) {
			for (WallType wallType : WallType.values()) {
				if (wallType.ordinal() == newWallType) {
					return wallType;
				}
			}
		}
		return WallType.EVERYDAY_LIVE_WALL;
	}
}
