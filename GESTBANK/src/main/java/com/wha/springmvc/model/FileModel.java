package com.wha.springmvc.model;

import org.springframework.web.multipart.MultipartFile;

public class FileModel {
	MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	// #region Utilitaire

	@Override
	public String toString() {
		return "FileModel [file=" + file.getName() + "]";
	}

	// #endregion

}
