package com.ktech.serviecImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktech.entity.Image;
import com.ktech.repository.ImageRepository;
import com.ktech.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository rImageRepository;

	@Override
	public Image saveImageDetails(Image image) {
		Image save = rImageRepository.save(image);
		return save;
	}

	@Override
	public List<Image> gImagesList() {
		List<Image> all = rImageRepository.findAll();
		return all;
	}

	@Override
	public Optional<Image> getSingleImage(Long id) {
		Optional<Image> optionalImage = rImageRepository.findById(id);
		return optionalImage;
	}
}
