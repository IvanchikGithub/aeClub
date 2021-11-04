package com.aeClub.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aeClub.entity.Picture;
import com.aeClub.enums.PicturesTypes;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class PictureService {
	
	@Value("${rootPath}")
	String rootPath;
	
	public List<Picture> handlingFilesWithUsersExtraPhoto(MultipartFile[] filesWithUsersExtraPhoto) {
		List<Picture> pictures = new ArrayList<Picture>();
		for (int i = 0; i < filesWithUsersExtraPhoto.length; i++) {
			Optional<String> recievedLinkOnPictureInAlbum = savePictureInStorage(
					filesWithUsersExtraPhoto[i], PicturesTypes.PHOTO_IN_ALBUM);
			if (!recievedLinkOnPictureInAlbum.isEmpty()) {
				Picture picture = new Picture(recievedLinkOnPictureInAlbum.get()+".jpg");
				pictures.add(picture);
			}
		}
		return pictures;
	}

	public Optional<String> savePictureInStorage(MultipartFile fileWithUsersPhoto,
			PicturesTypes pictureType) {
		if (fileWithUsersPhoto==null||fileWithUsersPhoto.isEmpty()) {
			return Optional.empty();
		}
		if (!validateExtension(fileWithUsersPhoto)) {
			return Optional.empty();
		}
		String generatedLinkOnPhotoProfile = UUID.randomUUID().toString();
		if (!savePictureFullSizeInStorage(fileWithUsersPhoto, generatedLinkOnPhotoProfile,
				pictureType)) {
			return Optional.empty();
		}
		if (!savePictureSmallSizeInStorage(generatedLinkOnPhotoProfile, pictureType)) {
			return Optional.empty();
		}
		return Optional.of(generatedLinkOnPhotoProfile);
	}

	private boolean validateExtension(MultipartFile file) {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		return "png".equals(extension) || "jpeg".equals(extension) || "jpg".equals(extension)
				|| "bmp".equals(extension) || "wbmp".equals(extension);
	}

	private boolean savePictureFullSizeInStorage(MultipartFile fileWithUsersPhoto,
			String generatedLinkOnPhotoProfile, PicturesTypes picturesType) {
		try {
			fileWithUsersPhoto.transferTo(new File(
					rootPath + picturesType.getDirectory() + generatedLinkOnPhotoProfile + ".jpg"));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean savePictureSmallSizeInStorage(String generatedLinkOnPhotoProfile,
			PicturesTypes picturesType) {
		try {
			Thumbnails.of(rootPath + picturesType.getDirectory() + generatedLinkOnPhotoProfile + ".jpg")
					.size(110, 110).outputFormat("jpg").outputQuality(0.90).toFile(rootPath
							+ picturesType.getDirectory() + "small\\" + generatedLinkOnPhotoProfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String getRootPath() {
		return rootPath;
	}
}
