package ru.scanword.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scanword.domain.Image;
import ru.scanword.dto.ImageDTO;
import ru.scanword.exceptions.ResourceNotFoundException;
import ru.scanword.mapper.ImageMapper;
import ru.scanword.repository.ImageRepository;
import ru.scanword.repository.QuestionRepository;
import ru.scanword.service.ImageService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('read')")
    public List<ImageDTO> getAll() {
        return allToDTO(imageRepository.findAll());
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('read')")
    public ImageDTO getById(Long id) {
        Optional<Image> audio = imageRepository.findById(id);
        if (audio.isPresent()) {
            return toDTO(audio.get());
        } else {
            throw new ResourceNotFoundException("Image with id = " + id + " not found", "");
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public ImageDTO create(ImageDTO imageDTO) {
        imageRepository.save(toEntity(imageDTO));
        return imageDTO;
    }

    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public boolean saveAll(List<ImageDTO> imageDTOList) {
        List<Image> images = imageRepository.findAll();
        imageDTOList.forEach(audio-> {
            images.removeIf(a-> a.getId().equals(audio.getId()));
        });

        imageRepository.saveAll(allToEntity(imageDTOList));
        images.forEach(image -> {
            if(questionRepository.findQuestionByImage(image).isEmpty()) {
                imageRepository.deleteById(image.getId());
            }
        });
        return true;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public ImageDTO update(ImageDTO imageDTO) {
        imageRepository.save(toEntity(imageDTO));
        return imageDTO;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public boolean deleteById(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        if (!image.isPresent()) {
            return false;
        }
        imageRepository.delete(image.get());
        return true;
    }

    private Image toEntity(ImageDTO imageDTO) {
        return ImageMapper.IMAGE_MAPPER.toEntity(imageDTO);
    }

    private ImageDTO toDTO(Image image) {
        return ImageMapper.IMAGE_MAPPER.toDTO(image);
    }

    private List<Image> allToEntity(List<ImageDTO> imageDTOList) {
        return ImageMapper.IMAGE_MAPPER.allToEntity(imageDTOList);
    }

    private List<ImageDTO> allToDTO(List<Image> imageList) {
        return ImageMapper.IMAGE_MAPPER.allToDTO(imageList);
    }


}
