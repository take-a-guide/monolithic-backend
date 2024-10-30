package br.com.takeaguide.guidelistingservice.domain.services;

import br.com.takeaguide.guidelistingservice.dtos.ResponseObject;
import br.com.takeaguide.guidelistingservice.dtos.*;
import org.springframework.http.ResponseEntity;

public interface GuideListingService {
    ResponseEntity<ResponseObject> createGuideListing(CreateGuideListingRequest request);
    ResponseEntity<ResponseObject> updateGuideListing(String id, ChangeGuideListingRequest request);
    ResponseEntity<ResponseObject> deleteGuideListing(String id);
    ResponseEntity<ResponseObject> getGuideListingById(String id);
    ResponseEntity<ResponseObject> getAllGuideListings();
    ResponseEntity<ResponseObject> getFilteredGuideListings(String searchTerm, String category, String location);
}
