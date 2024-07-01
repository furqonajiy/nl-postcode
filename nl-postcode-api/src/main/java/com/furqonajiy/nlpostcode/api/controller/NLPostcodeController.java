package com.furqonajiy.nlpostcode.api.controller;

import com.furqonajiy.nlpostcode.api.exception.ServiceUnavailableException;
import com.furqonajiy.nlpostcode.api.logging.ErrorLog;
import com.furqonajiy.nlpostcode.api.logging.TransactionLog;
import com.furqonajiy.nlpostcode.api.service.impl.GetPostcodeDistanceImpl;
import com.furqonajiy.nlpostcode.api.service.impl.UpdatePostcodeImpl;
import com.furqonajiy.nlpostcode.api.utility.SplunkLogger;
import com.furqonajiy.nlpostcode.model.Response;
import com.furqonajiy.nlpostcode.model.getnlpostcodedistance.GetNLPostcodeDistanceRq;
import com.furqonajiy.nlpostcode.model.getnlpostcodedistance.PostcodeDistance;
import com.furqonajiy.nlpostcode.model.updatenlpostcode.UpdateNLPostcodeRq;
import com.furqonajiy.nlpostcode.model.updatenlpostcode.UpdatePostcode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.MessageFormat;

@RestController
@Slf4j
public class NLPostcodeController {
    @Autowired
    private GetPostcodeDistanceImpl getPostcodeDistanceService;

    @Autowired
    private UpdatePostcodeImpl updatePostcodeService;

    @Autowired
    private SplunkLogger splunkLogger;

    @GetMapping(value = "nl/v1/postcode/distance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<PostcodeDistance>> getNLPostcodeDistance(
            @RequestParam(value = "postcode_a") String postcodeA,
            @RequestParam(value = "postcode_b") String postcodeB
    ) {
        log.debug("Receive Get NL Postcode Distances");
        long startMillis = System.currentTimeMillis();

        GetNLPostcodeDistanceRq request = new GetNLPostcodeDistanceRq();
        request.setPostcodeA(postcodeA);
        request.setPostcodeB(postcodeB);

        Response<PostcodeDistance> response = new Response<>();
        try {
            response.setStatusCode("00000");
            response.setStatusDesc("Success");

            PostcodeDistance postcodeDistanceRs = getPostcodeDistanceService.process(request);
            response.setData(postcodeDistanceRs);

            splunkLogger.info(new TransactionLog(
                    startMillis,
                    HttpStatus.OK.value(),
                    null,
                    response)
            );
            return ResponseEntity.ok(response);
        } catch (ServiceUnavailableException e) {
            response.setStatusCode(e.getStatusCode());
            response.setStatusDesc(e.getStatusDesc());

            splunkLogger.error(new ErrorLog(
                    startMillis,
                    HttpStatus.SERVICE_UNAVAILABLE.value(),
                    response.getStatusCode(),
                    response.getStatusDesc(),
                    null,
                    response,
                    e)
            );
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            response.setStatusCode("99999");
            response.setStatusDesc("Failed to Get NL Postcode Distance");

            splunkLogger.error(new ErrorLog(
                    startMillis,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    response.getStatusCode(),
                    response.getStatusDesc(),
                    null,
                    response,
                    e)
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "nl/v1/postcode/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<UpdatePostcode>> updateNLPostcode(
            @Valid @RequestBody UpdateNLPostcodeRq request
    ) {
        log.debug("Receive Update NL Postcode Request");
        long startMillis = System.currentTimeMillis();

        Response<UpdatePostcode> response = new Response<>();
        try {
            response.setStatusCode("00000");
            response.setStatusDesc("Coordinates are successfully updated");

            UpdatePostcode updatePostcodeRs = updatePostcodeService.process(request);
            response.setData(updatePostcodeRs);

            splunkLogger.info(new TransactionLog(
                    startMillis,
                    HttpStatus.OK.value(),
                    request,
                    response)
            );
            return ResponseEntity.ok(response);
        } catch (ServiceUnavailableException e) {
            response.setStatusCode(e.getStatusCode());
            response.setStatusDesc(e.getStatusDesc());

            splunkLogger.error(new ErrorLog(
                    startMillis,
                    HttpStatus.SERVICE_UNAVAILABLE.value(),
                    response.getStatusCode(),
                    response.getStatusDesc(),
                    request,
                    response,
                    e)
            );
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            log.debug("Exception occurs.", e);

            response.setStatusCode("99999");
            String statusDesc = MessageFormat.format("An error occured: {0}", request);
            response.setStatusDesc(statusDesc);

            splunkLogger.error(new ErrorLog(
                    startMillis,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    response.getStatusCode(),
                    response.getStatusDesc(),
                    request,
                    response,
                    e)
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
