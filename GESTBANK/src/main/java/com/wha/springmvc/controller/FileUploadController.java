package com.wha.springmvc.controller;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wha.springmvc.model.FileModel;
import com.wha.springmvc.util.FileValidator;
@RestController
public class FileUploadController {
	private static String UPLOAD_LOCATION = "C:/Logiciels/Développement/Atona-download";

	@Autowired
	FileValidator fileValidator;

	@InitBinder("fileModel")
	protected void initBinderFileBucket(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

//	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
//	public String getHomePage(ModelMap model) {
//		return "welcome";
//	}

	@RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
	public String getSingleUploadPage(ModelMap model) {
		System.out.println("Essai Upload en GET Impossible");
		FileModel fileModel = new FileModel();
		model.addAttribute("fileModel", fileModel);
		return "UserManagement";
	}

	@RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
	public String singleFileUpload(@Valid FileModel fileModel, BindingResult result, ModelMap model)
			throws IOException {

		if (result.hasErrors()) {
			System.out.println("validation errors");
			return "UserManagement";
		} else {
			System.out.println("Fetching file");
			MultipartFile multipartFile = fileModel.getFile();

			// Now do something with file...
			FileCopyUtils.copy(fileModel.getFile().getBytes(),
					new File(UPLOAD_LOCATION + fileModel.getFile().getOriginalFilename()));

			String fileName = multipartFile.getOriginalFilename();
			model.addAttribute("fileName", fileName);
			return "success";
		}
	}


}
