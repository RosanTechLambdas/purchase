package com.purshase.Purshase_Api.service;

import com.purshase.Purshase_Api.Request.PurshaseDetailRequest;
import com.purshase.Purshase_Api.response.PurshaseDetailResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PurchaseService {
    void savePurshase(PurshaseDetailRequest request);

    Page<PurshaseDetailResponse> getPurchaseDetail(String keyword);
}
