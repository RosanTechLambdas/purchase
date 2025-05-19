package com.purshase.Purshase_Api.service;

import com.purshase.Purshase_Api.Mapper.PurshaseMapper;
import com.purshase.Purshase_Api.Request.PurshaseDetailRequest;
import com.purshase.Purshase_Api.config.GetCurrentUser;
import com.purshase.Purshase_Api.model.PurshaseDetail;
import com.purshase.Purshase_Api.repo.PurshaseRepo;
import com.purshase.Purshase_Api.response.PurshaseDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class PurshaseImpl implements PurshaseService{
    @Autowired
    private PurshaseRepo purshaseRepo;

    @Autowired
    private PurshaseMapper purshaseMapper;

    String existUserId= GetCurrentUser.getCurrentUserId();

    @Override
    public void savePurshase(PurshaseDetailRequest request) {
        PurshaseDetail newPurshase=purshaseMapper.toPurshaseDetail(request);
        newPurshase.setUserId(existUserId);
        newPurshase.setCustomerId(UUID.randomUUID().toString());
        purshaseRepo.save(newPurshase);
    }

    @Override
    public List<PurshaseDetailResponse> getPurchaseDetail() {
        List<PurshaseDetail> purchaseData=purshaseRepo.findByUserId(existUserId);

        if(purchaseData.isEmpty()){
            throw new NullPointerException("No Purchase Data Found");
        }
        return purshaseMapper.toPurshaseDetailResponse(purchaseData);
    }
}
