package br.com.takeaguide.takeaguide.adapters.controllers;

import br.com.takeaguide.takeaguide.domain.services.GuideService;
import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import br.com.takeaguide.takeaguide.dtos.guide.GuideRequest;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guides")
@Tag(name = "APIs-TAKE-A-GUIDE: GUIDE", description = "CONTAINS ALL GUIDE-RELATED ENDPOINTS")
public class GuideController {

    private final GuideService guideService;

    public GuideController(GuideService guideService) {
        this.guideService = guideService;
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createGuide(@RequestBody GuideRequest guideRequest) {
        return guideService.createGuide(guideRequest);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ResponseObject> getGuideByCpf(@PathVariable String cpf) {
        return guideService.getGuideByCpf(cpf);
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getAllGuides() {
        return guideService.getAllGuides();
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ResponseObject> updateGuide(@PathVariable String cpf, @RequestBody GuideRequest guideRequest) {
        return guideService.updateGuide(cpf, guideRequest);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<ResponseObject> deleteGuide(@PathVariable String cpf) {
        return guideService.deleteGuide(cpf);
    }
}
