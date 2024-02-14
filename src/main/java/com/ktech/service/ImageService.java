package com.ktech.service;

import java.util.List;
import java.util.Optional;

import com.ktech.entity.Image;

public interface ImageService {

	Image saveImageDetails(Image image);

	List<Image> gImagesList();

	Optional<Image> getSingleImage(Long id);
}
