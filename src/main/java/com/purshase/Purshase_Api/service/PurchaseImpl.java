package com.purshase.Purshase_Api.service;

import com.purshase.Purshase_Api.Mapper.PurshaseMapper;
import com.purshase.Purshase_Api.Request.PurshaseDetailRequest;
import com.purshase.Purshase_Api.config.GetCurrentUser;
import com.purshase.Purshase_Api.model.PurshaseDetail;
import com.purshase.Purshase_Api.repo.CustomReo;
import com.purshase.Purshase_Api.repo.PurshaseRepo;
import com.purshase.Purshase_Api.response.PurshaseDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class PurchaseImpl implements PurchaseService{
    @Autowired
    private PurshaseRepo purshaseRepo;

    @Autowired
    private PurshaseMapper purshaseMapper;

    @Autowired
    private CustomReo  customReo;



    @Override
    public void savePurshase(PurshaseDetailRequest request) {
        PurshaseDetail newPurshase=purshaseMapper.toPurshaseDetail(request);
        newPurshase.setUserId( GetCurrentUser.getCurrentUserId());
        newPurshase.setCustomerId(UUID.randomUUID().toString());
        purshaseRepo.save(newPurshase);
    }

    @Override
    public Page<PurshaseDetailResponse> getPurchaseDetail(String Keyword) {
        Page<PurshaseDetail> response= customReo.getPurchaseData(Keyword);
        List<PurshaseDetail> purchaseDetail=response.getContent();
        return new PageImpl<>(purshaseMapper.toPurshaseDetailResponse(purchaseDetail),response.getPageable(),response.getTotalElements());
    }


}
