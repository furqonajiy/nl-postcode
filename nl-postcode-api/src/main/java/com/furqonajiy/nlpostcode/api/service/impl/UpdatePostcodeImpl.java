package com.furqonajiy.nlpostcode.api.service.impl;

import com.furqonajiy.nlpostcode.api.postgre.NLPostcodeDao;
import com.furqonajiy.nlpostcode.api.service.INLPostcodeService;
import com.furqonajiy.nlpostcode.api.service.IUpdatePostcodeService;
import com.furqonajiy.nlpostcode.api.utility.NLPostcodeTableCache;
import com.furqonajiy.nlpostcode.model.getnlpostcodedistance.Postcode;
import com.furqonajiy.nlpostcode.model.updatenlpostcode.UpdateNLPostcodeRq;
import com.furqonajiy.nlpostcode.model.updatenlpostcode.UpdatePostcode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UpdatePostcodeImpl extends GenericPostcodeImpl implements INLPostcodeService<UpdateNLPostcodeRq, UpdatePostcode>, IUpdatePostcodeService {
    @Autowired
    @Qualifier("nlPostcodeDao")
    private NLPostcodeDao nlPostcodeDao;

    @Autowired
    private NLPostcodeTableCache nlPostcodeTableCache;

    @Autowired
    private GetPostcodeDistanceImpl getPostcodeDistance;

    @Override
    public UpdatePostcode process(UpdateNLPostcodeRq request) {
        log.debug("Process Update NL Postcode");

        // Initialize Request Parameter
        String postcode = request.getPostcode();
        double latitude = request.getLatitude();
        double longitude = request.getLongitude();

        List<Map<String, Object>> nlPostcodeTable = nlPostcodeTableCache.getNlPostcodeTable();

        // Get Old Postcode
        Postcode oldPostcode = getPostCodeRow(postcode, nlPostcodeTable);

        // Update Table Cache in DB
        nlPostcodeDao.updateNLPostcode(postcode, latitude, longitude);

        // Update Table Cache in Service
        List<Map<String, Object>> updatedTableCache = updateNLPostcodeTableCache(postcode, latitude, longitude, nlPostcodeTable);
        nlPostcodeTableCache.setNlPostcodeTable(updatedTableCache);

        // Get New Postcode
        Postcode newPostcode = getPostCodeRow(postcode, nlPostcodeTable);

        // Compare Postcode
        return generateResponse(oldPostcode, newPostcode);
    }

    @Override
    public List<Map<String, Object>> updateNLPostcodeTableCache(String postcode, Double latitude, Double longitude, List<Map<String, Object>> nlPostcodeTable) {
        log.debug("Update NL Postcode Table Cache");

        for (Map<String, Object> nlPostCodeTableRow : nlPostcodeTable) {
            String id = nlPostCodeTableRow.get("postcode").toString();
            if (id.equalsIgnoreCase(postcode)) {
                nlPostCodeTableRow.put("latitude", latitude);
                nlPostCodeTableRow.put("longitude", longitude);

                break;
            }
        }

        return nlPostcodeTable;
    }

    @Override
    public UpdatePostcode generateResponse(Postcode oldPostcode, Postcode newPostcode) {
        log.debug("Generate Response");

        UpdatePostcode updatePostcode = new UpdatePostcode();
        updatePostcode.setOldPostcode(oldPostcode);
        updatePostcode.setNewPostcode(newPostcode);

        return updatePostcode;
    }
}
