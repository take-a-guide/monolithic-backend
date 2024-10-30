package br.com.takeaguide.guidelistingservice.application.services;

import br.com.takeaguide.guidelistingservice.domain.entities.GuideListing;
import br.com.takeaguide.guidelistingservice.domain.repositories.GuideListingRepository;
import br.com.takeaguide.guidelistingservice.domain.services.GuideListingService;
import br.com.takeaguide.guidelistingservice.dtos.ResponseObject;
import br.com.takeaguide.guidelistingservice.dtos.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuideListingServiceImpl implements GuideListingService {

    private final GuideListingRepository guideListingRepository;

    public GuideListingServiceImpl(GuideListingRepository guideListingRepository) {
        this.guideListingRepository = guideListingRepository;
    }

    @Override
    public ResponseEntity<ResponseObject> createGuideListing(CreateGuideListingRequest request) {
        GuideListing guideListing = new GuideListing(
                null, 
                request.getCpf(), 
                request.getTitle(), 
                request.getDescription(), 
                request.getLocation(),
                request.getTours(), 
                request.getCategories(), 
                LocalDateTime.now(), 
                LocalDateTime.now()
        );
        guideListingRepository.save(guideListing);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseObject.success("Guide listing created successfully"));
    }

    @Override
    public ResponseEntity<ResponseObject> updateGuideListing(String id, ChangeGuideListingRequest request) {
        GuideListing guideListing = guideListingRepository.findById(id);
        if (guideListing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseObject.error("Guide listing not found"));
        }
        guideListing.updateFromRequest(request);
        guideListingRepository.update(guideListing);
        return ResponseEntity.ok(ResponseObject.success("Guide listing updated successfully"));
    }

    @Override
    public ResponseEntity<ResponseObject> deleteGuideListing(String id) {
        guideListingRepository.delete(id);
        return ResponseEntity.ok(ResponseObject.success("Guide listing deleted successfully"));
    }

    @Override
    public ResponseEntity<ResponseObject> getGuideListingById(String id) {
        GuideListing guideListing = guideListingRepository.findById(id);
        if (guideListing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseObject.error("Guide listing not found"));
        }
        return ResponseEntity.ok(ResponseObject.successWithData("Guide listing retrieved successfully", guideListing));
    }

    @Override
    public ResponseEntity<ResponseObject> getAllGuideListings() {
        List<GuideListing> listings = guideListingRepository.findAll();
        List<GuideListingDto> dtos = listings.stream().map(listing ->
                new GuideListingDto(
                        listing.getId(),
                        listing.getCpf(),
                        listing.getTitle(),
                        listing.getDescription(),
                        listing.getLocation(),
                        listing.getTours(),
                        listing.getCategories(),
                        listing.getCreatedAt(),
                        listing.getUpdatedAt()
                )
        ).collect(Collectors.toList());
        return ResponseEntity.ok(ResponseObject.successWithData("Guide listings retrieved successfully", dtos));
    }

    @Override
    public ResponseEntity<ResponseObject> getFilteredGuideListings(String searchTerm, String category, String location) {
        List<GuideListing> listings = guideListingRepository.findFiltered(searchTerm, category, location);
        List<GuideListingDto> dtos = listings.stream().map(listing ->
                new GuideListingDto(
                        listing.getId(),
                        listing.getCpf(),
                        listing.getTitle(),
                        listing.getDescription(),
                        listing.getLocation(),
                        listing.getTours(),
                        listing.getCategories(),
                        listing.getCreatedAt(),
                        listing.getUpdatedAt()
                )
        ).collect(Collectors.toList());
        return ResponseEntity.ok(ResponseObject.successWithData("Filtered guide listings retrieved successfully", dtos));
    }
}
