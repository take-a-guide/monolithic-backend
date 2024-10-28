package br.com.takeaguide.takeaguide.domain.services;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import br.com.takeaguide.takeaguide.dtos.ad.*;
import org.springframework.http.ResponseEntity;

public interface AdService {

    ResponseEntity<ResponseObject> retrieveAd(RetrieveAdRequest request);

    ResponseEntity<ResponseObject> createAd(CreateAdRequest request);

    ResponseEntity<ResponseObject> removeAd(RemoveAdRequest request);

    ResponseEntity<ResponseObject> changeAd(ChangeAdRequest request);
}
