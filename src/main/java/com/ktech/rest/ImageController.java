package com.ktech.rest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ktech.entity.Image;
import com.ktech.serviecImpl.ImageServiceImpl;

@RestController
public class ImageController {
	@Autowired
	private ImageServiceImpl serviceImpl;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
		try {
			Image image = new Image();
			image.setName(file.getOriginalFilename());
			image.setData(file.getBytes());
			serviceImpl.saveImageDetails(image);
			return ResponseEntity.ok().body("Image uploaded successfully: " + file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
		Optional<Image> optionalImage = serviceImpl.getSingleImage(id);
		if (optionalImage.isPresent()) {
			Image image = optionalImage.get();
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image.getData());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/list")
	public ResponseEntity<List<Image>> getImageList() {
		List<Image> listOfImage = serviceImpl.gImagesList();
		return new ResponseEntity<>(listOfImage, HttpStatus.OK);
	}
}
