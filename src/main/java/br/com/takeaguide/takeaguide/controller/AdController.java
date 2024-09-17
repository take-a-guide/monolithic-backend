package br.com.takeaguide.takeaguide.controller;

import static br.com.takeaguide.takeaguide.utils.ResponseUtils.formatResponse;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import br.com.takeaguide.takeaguide.dtos.ad.ChangeAdRequest;
import br.com.takeaguide.takeaguide.dtos.ad.ChangeAdResponse;
import br.com.takeaguide.takeaguide.dtos.ad.AdDto;
import br.com.takeaguide.takeaguide.dtos.ad.RetrieveAdRequest;
import br.com.takeaguide.takeaguide.dtos.ad.RetrieveAdResponse;
import br.com.takeaguide.takeaguide.dtos.ad.CreateAdRequest;
import br.com.takeaguide.takeaguide.dtos.ad.CreateAdResponse;
import br.com.takeaguide.takeaguide.dtos.ad.RemoveAdRequest;
import br.com.takeaguide.takeaguide.repositories.mysql.AdRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE})
@RequestMapping("/api/v1/take_a_guide/Ad")
@Tag(
    name = "APIs-TAKE-A-GUIDE: Ad",
    description = "CONTAINS ALL AD-RELATED ENDPOINTS"
)
public class AdController {

    @Autowired
    private AdRepository AdRepository;

    @PostMapping("/retrieve")
    @Operation(
        summary = "API USED TO RETRIEVE AN AD",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "AD DATA RETRIEVED SUCCESSFULLY",
                content = @Content(
                    schema = @Schema(implementation = RetrieveAdResponse.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "SOME OF THE REQUEST ITEMS ARE INVALID",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)   
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "AD NOT FOUND",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)
                )
            ),
            @ApiResponse(
                responseCode = "500",
                description = "AN ISSUE OCCURRED ON THE SERVER",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)
                )
            )
        }
    )
    public ResponseEntity<ResponseObject> retrieveAd(
        @io.swagger.v3.oas.annotations.parameters.RequestBody
        @RequestBody RetrieveAdRequest request){

        ResponseEntity<ResponseObject> validate = request.validate();

        if(validate != null){

            return validate;

        }

        List<AdDto> ads = null;

        if(request.idAd() != null){

            ads = AdRepository.retrieveAdsById(request.idAd());

        }

        if(request.userId() != null){

            ads = AdRepository.retrieveAdsByUserId(request.userId());

        }

        if(ads != null){

            return formatResponse(
                HttpStatus.OK, 
                new RetrieveAdResponse(ads, "Ads found")
            );

        }

        return formatResponse(
            HttpStatus.NOT_FOUND, 
            ResponseObject.builder().error("No Ad found").build()
        );

    }

    @DeleteMapping("/remove")
    @Operation(
        summary = "API USED TO REMOVE AN AD",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "AD REMOVED SUCCESSFULLY",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "SOME OF THE REQUEST ITEMS ARE INVALID",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)   
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "AD NOT FOUND",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)
                )
            ),
            @ApiResponse(
                responseCode = "500",
                description = "AN ISSUE OCCURRED ON THE SERVER",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)
                )
            )
        }
    )
    public ResponseEntity<ResponseObject> removeAd(
        @io.swagger.v3.oas.annotations.parameters.RequestBody
        @RequestBody RemoveAdRequest request){

        ResponseEntity<ResponseObject> validate = request.validate();

        if(validate != null){

            return validate;

        }

        List<AdDto> ads = AdRepository.retrieveAdsById(request.id());

        if(ads == null || ads.size() < 1){

            return formatResponse(
                HttpStatus.NOT_FOUND, 
                ResponseObject.error("AD NOT FOUND")
            );

        }

        AdRepository.removeAd(request.id());

        return formatResponse(
            HttpStatus.OK, 
            ResponseObject.builder().success("AD REMOVED SUCCESSFULLY").build()
        );

    }

    @PutMapping("/change")
    @Operation(
        summary = "API USED TO CHANGE AN AD",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "AD CHANGED SUCCESSFULLY",
                content = @Content(
                    schema = @Schema(implementation = ChangeAdResponse.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "SOME OF THE REQUEST ITEMS ARE INVALID",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)   
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "AD NOT FOUND",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)
                )
            ),
            @ApiResponse(
                responseCode = "500",
                description = "AN ISSUE OCCURRED ON THE SERVER",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)
                )
            )
        }
    )
    public ResponseEntity<ResponseObject> changeAd(
        @io.swagger.v3.oas.annotations.parameters.RequestBody
        @RequestBody ChangeAdRequest request){

        ResponseEntity<ResponseObject> validate = request.validate();

        if(validate != null){

            return validate;

        }

        
        List<AdDto> adsDto = AdRepository.retrieveAdsById(request.id());
        
        if(adsDto == null){
            
            return formatResponse(
                HttpStatus.NOT_FOUND,
                ResponseObject.error("AD NOT FOUND")
            );
                
        }

        /*BigInteger idAd =*/ AdRepository.ChangeAd(request.id(), request.ad());

        return formatResponse(
            HttpStatus.OK, 
            new ChangeAdResponse(new BigInteger(request.id() + ""), "Ad changed successfully")
        );

    }

    @PostMapping("/create")
    @Operation(
        summary = "API USED TO CREATE AN AD",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "AD CREATED SUCCESSFULLY",
                content = @Content(
                    schema = @Schema(implementation = CreateAdResponse.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "SOME OF THE REQUEST ITEMS ARE INVALID",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)   
                )
            ),
            @ApiResponse(
                responseCode = "500",
                description = "AN ISSUE OCCURRED ON THE SERVER",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)
                )
            )
        }
    )
    public ResponseEntity<ResponseObject> createAd(
        @io.swagger.v3.oas.annotations.parameters.RequestBody
        @RequestBody CreateAdRequest request){

        ResponseEntity<ResponseObject> validate = request.validate();

        if(validate != null){

            return validate;
            
        }

        BigInteger idAd = AdRepository.CreateAd(request.userId(), request.ad());

        if(idAd == null){

            return formatResponse(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                ResponseObject.builder().error("Ad can't be created").build()
            );

        }

        return formatResponse(
            HttpStatus.CREATED, 
            new CreateAdResponse(idAd, "Ad created successfully")
        );

    }

}