package com.purshase.Purshase_Api.repo;


import com.purshase.Purshase_Api.config.GetCurrentUser;
import com.purshase.Purshase_Api.model.PurshaseDetail;
import com.purshase.Purshase_Api.response.PurshaseDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomRepoImpl implements CustomReo{
    @Autowired
    private MongoTemplate mongoTemplate;

//    String existUserId= GetCurrentUser.getCurrentUserId();

    @Override
    public Page<PurshaseDetail> getPurchaseData(String keyword) {
        Query query = new Query();
        Pageable pageable = PageRequest.of(0, 10);

        if (keyword != null && !keyword.trim().isEmpty()) {
            System.out.println(keyword);
            query.addCriteria(Criteria.where("customerName").regex(".*" + keyword + ".*", "i"));
        }

        query.with(pageable);
        query.addCriteria(Criteria.where("userId").is(GetCurrentUser.getCurrentUserId()));
        List<PurshaseDetail> results = mongoTemplate.find(query, PurshaseDetail.class);
        long count = mongoTemplate.count(query.skip(-1).limit(-1), PurshaseDetail.class);

        return new PageImpl<>(results, pageable, count);
    }

}
