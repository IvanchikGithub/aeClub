package com.aeClub.service;

import com.aeClub.enums.SettingsWallType;
import com.aeClub.enums.WallType;

public interface EditService {
 public WallType changeActiveWall (int newWallType);
 public SettingsWallType changeActiveSettingsWall (int newSettingsWall);
}
