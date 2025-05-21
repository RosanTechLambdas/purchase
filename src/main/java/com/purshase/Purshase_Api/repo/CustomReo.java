package com.purshase.Purshase_Api.repo;

import com.purshase.Purshase_Api.model.PurshaseDetail;
import com.purshase.Purshase_Api.response.PurshaseDetailResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomReo {
    Page<PurshaseDetail> getPurchaseData(String keyword);
}
