package com.aeClub.service.impl;

import org.springframework.stereotype.Service;

import com.aeClub.enums.SettingsWallType;
import com.aeClub.enums.WallType;
import com.aeClub.service.EditService;

@Service
public class EditServiceImpl implements EditService {

	@Override
	public WallType changeActiveWall(int newWallType) {
		if (newWallType >= 0 && newWallType <= 3) {
			for (WallType wallType : WallType.values()) {
				if (wallType.ordinal() == newWallType) {
					return wallType;
				}
			}
		}
		return WallType.EVERYDAY_LIVE_WALL;
	}

	@Override
	public SettingsWallType changeActiveSettingsWall(int newSettingsWall) {
		if (newSettingsWall >= 0 && newSettingsWall <= 3) {
			for (SettingsWallType settingsWallType : SettingsWallType.values()) {
				if (settingsWallType.ordinal() == newSettingsWall) {
					return settingsWallType;
				}
			}
		}
		return SettingsWallType.MAIN_INFO;
	}
	
	
	
	
	
}
