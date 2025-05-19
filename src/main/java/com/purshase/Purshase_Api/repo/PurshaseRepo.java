package com.purshase.Purshase_Api.repo;

import com.purshase.Purshase_Api.model.PurshaseDetail;
import com.purshase.Purshase_Api.response.PurshaseDetailResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PurshaseRepo extends MongoRepository<PurshaseDetail,String> {
    List<PurshaseDetail> findByUserId(String existUserId);
}
