package br.com.takeaguide.takeaguide.controller;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import br.com.takeaguide.takeaguide.dtos.ad.*;
import br.com.takeaguide.takeaguide.services.AdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE})
@RequestMapping("/api/v1/take_a_guide/ad")
@Tag(name = "APIs-TAKE-A-GUIDE: Ad", description = "CONTAINS ALL AD-RELATED ENDPOINTS")
public class AdController {

    @Autowired
    private AdService adService;

    @PostMapping("/retrieve")
    @Operation(summary = "API USED TO RETRIEVE AN AD", responses = {
            @ApiResponse(responseCode = "200", description = "AD DATA RETRIEVED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = RetrieveAdResponse.class))),
            @ApiResponse(responseCode = "400", description = "SOME OF THE REQUEST ITEMS ARE INVALID", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
            @ApiResponse(responseCode = "404", description = "AD NOT FOUND", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
            @ApiResponse(responseCode = "500", description = "AN ISSUE OCCURRED ON THE SERVER", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> retrieveAd(@RequestBody RetrieveAdRequest request) {
        return adService.retrieveAd(request);
    }

    @PostMapping("/create")
    @Operation(summary = "API USED TO CREATE AN AD", responses = {
            @ApiResponse(responseCode = "201", description = "AD CREATED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = CreateAdResponse.class))),
            @ApiResponse(responseCode = "400", description = "SOME OF THE REQUEST ITEMS ARE INVALID", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
            @ApiResponse(responseCode = "500", description = "AN ISSUE OCCURRED ON THE SERVER", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> createAd(@RequestBody CreateAdRequest request) {
        return adService.createAd(request);
    }

    @DeleteMapping("/remove")
    @Operation(summary = "API USED TO REMOVE AN AD", responses = {
            @ApiResponse(responseCode = "200", description = "AD REMOVED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
            @ApiResponse(responseCode = "400", description = "SOME OF THE REQUEST ITEMS ARE INVALID", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
            @ApiResponse(responseCode = "404", description = "AD NOT FOUND", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
            @ApiResponse(responseCode = "500", description = "AN ISSUE OCCURRED ON THE SERVER", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> removeAd(@RequestBody RemoveAdRequest request) {
        return adService.removeAd(request);
    }

    @PutMapping("/change")
    @Operation(summary = "API USED TO CHANGE AN AD", responses = {
            @ApiResponse(responseCode = "200", description = "AD CHANGED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = ChangeAdResponse.class))),
            @ApiResponse(responseCode = "400", description = "SOME OF THE REQUEST ITEMS ARE INVALID", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
            @ApiResponse(responseCode = "404", description = "AD NOT FOUND", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
            @ApiResponse(responseCode = "500", description = "AN ISSUE OCCURRED ON THE SERVER", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> changeAd(@RequestBody ChangeAdRequest request) {
        return adService.changeAd(request);
    }
}
