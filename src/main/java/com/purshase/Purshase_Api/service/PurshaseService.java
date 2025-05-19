package com.purshase.Purshase_Api.service;

import com.purshase.Purshase_Api.Request.PurshaseDetailRequest;
import com.purshase.Purshase_Api.model.PurshaseDetail;
import com.purshase.Purshase_Api.response.PurshaseDetailResponse;

import java.util.List;

public interface PurshaseService {
    void savePurshase(PurshaseDetailRequest request);

    List<PurshaseDetailResponse> getPurchaseDetail();
}
