package br.com.takeaguide.takeaguide.domain.services;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import br.com.takeaguide.takeaguide.dtos.guide.GuideRequest;
import org.springframework.http.ResponseEntity;


public interface GuideService {
    ResponseEntity<ResponseObject> createGuide(GuideRequest guideRequest);
    ResponseEntity<ResponseObject> getGuideByCpf(String cpf);
    ResponseEntity<ResponseObject> getAllGuides();
    ResponseEntity<ResponseObject> updateGuide(String cpf, GuideRequest guideRequest);
    ResponseEntity<ResponseObject> deleteGuide(String cpf);
}
