package com.lambakean.RationPlanner.representation.controller;

import com.lambakean.RationPlanner.representation.dto.PhotoDto;
import com.lambakean.RationPlanner.domain.mapper.PhotoMapper;
import com.lambakean.RationPlanner.data.model.Photo;
import com.lambakean.RationPlanner.domain.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/photo")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;
    private final PhotoMapper photoMapper;

    @PostMapping
    public ResponseEntity<PhotoDto> uploadPhoto(@RequestParam MultipartFile photo) {

        Photo savedPhoto = photoService.savePhoto(photo);

        return ResponseEntity.ok(photoMapper.toPhotoDto(savedPhoto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String id) {

        Resource resource = photoService.loadPhoto(id);

        return ResponseEntity.ok(resource);
    }
}