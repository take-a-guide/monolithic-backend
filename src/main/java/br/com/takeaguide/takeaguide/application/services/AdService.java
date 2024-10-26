package br.com.takeaguide.takeaguide.services;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import br.com.takeaguide.takeaguide.dtos.ad.*;
import br.com.takeaguide.takeaguide.repositories.mysql.AdRepository;
import br.com.takeaguide.takeaguide.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdService {

    private final AdRepository adRepository;

    @Autowired
    public AdService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    public ResponseEntity<ResponseObject> retrieveAd(RetrieveAdRequest request) {
        List<AdDto> ads = null;


        if (request.cpf() != null) {
            ads = adRepository.retrieveAdsByCpf(request.cpf());
        }


        if (ads != null && !ads.isEmpty()) {
            return ResponseUtils.formatResponse(
                HttpStatus.OK, 
                new RetrieveAdResponse(ads, "Ads found")
            );
        }

        return ResponseUtils.formatResponse(
            HttpStatus.NOT_FOUND, 
            ResponseObject.builder().error("No Ad found").build()
        );
    }

    public ResponseEntity<ResponseObject> createAd(CreateAdRequest request) {
        String cpf = adRepository.createAd(request.cpf(), request.ad());

        if (cpf == null) {
            return ResponseUtils.formatResponse(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                ResponseObject.builder().error("Ad can't be created").build()
            );
        }

        return ResponseUtils.formatResponse(
            HttpStatus.CREATED, 
            new CreateAdResponse(cpf, "Ad created successfully")
        );
    }

    public ResponseEntity<ResponseObject> removeAd(RemoveAdRequest request) {
        List<AdDto> ads = adRepository.retrieveAdsByCpf(request.cpf());

        if (ads == null || ads.isEmpty()) {
            return ResponseUtils.formatResponse(
                HttpStatus.NOT_FOUND, 
                ResponseObject.error("AD NOT FOUND")
            );
        }

        adRepository.removeAd(request.cpf());
        return ResponseUtils.formatResponse(
            HttpStatus.OK, 
            ResponseObject.builder().success("AD REMOVED SUCCESSFULLY").build()
        );
    }

    public ResponseEntity<ResponseObject> changeAd(ChangeAdRequest request) {
        List<AdDto> adsDto = adRepository.retrieveAdsByCpf(request.cpf());

        if (adsDto == null || adsDto.isEmpty()) {
            return ResponseUtils.formatResponse(
                HttpStatus.NOT_FOUND,
                ResponseObject.error("AD NOT FOUND")
            );
        }

        adRepository.changeAd(request.cpf(), request.ad());
        return ResponseUtils.formatResponse(
            HttpStatus.OK, 
            new ChangeAdResponse(request.cpf(), "Ad changed successfully")
        );
    }
}
