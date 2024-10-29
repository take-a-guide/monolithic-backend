package br.com.takeaguide.takeaguide.application.services;

import br.com.takeaguide.takeaguide.domain.entities.Guide;
import br.com.takeaguide.takeaguide.domain.repositories.GuideRepository;
import br.com.takeaguide.takeaguide.domain.services.GuideService;
import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import br.com.takeaguide.takeaguide.dtos.guide.GuideRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GuideServiceImpl implements GuideService {

    private final GuideRepository guideRepository;

    public GuideServiceImpl(GuideRepository guideRepository) {
        this.guideRepository = guideRepository;
    }

    @Override
    public ResponseEntity<ResponseObject> createGuide(GuideRequest guideRequest) {
        Guide guide = convertToGuideEntity(guideRequest);
        guide.setCreatedAt(LocalDateTime.now());
        guideRepository.saveGuide(guide);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ResponseObject.builder().success("Guide created successfully").build()
        );
    }

    @Override
    public ResponseEntity<ResponseObject> getGuideByCpf(String cpf) {
        Guide guide = guideRepository.findGuideByCpf(cpf);
        if (guide == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ResponseObject.builder().error("Guide not found").build()
            );
        }
        return ResponseEntity.ok(ResponseObject.builder().data(guide).build());
    }

    @Override
    public ResponseEntity<ResponseObject> getAllGuides() {
        List<Guide> guides = guideRepository.findAllGuides();
        return ResponseEntity.ok(ResponseObject.builder().data(guides).build());
    }

    @Override
    public ResponseEntity<ResponseObject> updateGuide(String cpf, GuideRequest guideRequest) {
        Guide guide = convertToGuideEntity(guideRequest);
        guide.setCpf(cpf);
        guide.setUpdatedAt(LocalDateTime.now());
        guideRepository.updateGuide(guide);
        return ResponseEntity.ok(ResponseObject.builder().success("Guide updated successfully").build());
    }

    @Override
    public ResponseEntity<ResponseObject> deleteGuide(String cpf) {
        guideRepository.deleteGuide(cpf);
        return ResponseEntity.ok(ResponseObject.builder().success("Guide deleted successfully").build());
    }

    private Guide convertToGuideEntity(GuideRequest guideRequest) {
        return new Guide(
            guideRequest.getCpf(),
            guideRequest.getEducation(),
            guideRequest.getDescription(),
            guideRequest.getLocation(),
            guideRequest.isVerified(),
            guideRequest.getAvailableDates(),
            guideRequest.getTours(),
            guideRequest.getCategories(),
            null,
            null
        );
    }
}
