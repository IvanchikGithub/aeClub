package com.aeClub.service;

import org.springframework.ui.Model;

import com.aeClub.entity.Account;
import com.aeClub.enums.SettingsWallType;
import com.aeClub.enums.WallType;

public interface EditService {
 public WallType changeActiveWall (int newWallType);
 public Model setCheckedInHobbiesAndLanguagesLists (Model model, Account account);
}
