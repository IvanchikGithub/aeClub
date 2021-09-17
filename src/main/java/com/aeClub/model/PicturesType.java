package com.aeClub.model;

public enum PicturesType {
	USERS_AVATAR("ProfileMainPhotos\\"), PHOTO_IN_ALBUM("LibraryPhotos\\"), PICTURES_ON_THE_WALL("WallPictures\\");
	
	private String directory;
	
	private PicturesType (String directory) {
		this.directory=directory;
	}

	public String getDirectory() {
		return directory;
	}
	
	
}
