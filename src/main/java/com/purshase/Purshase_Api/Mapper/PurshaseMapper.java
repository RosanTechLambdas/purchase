package com.purshase.Purshase_Api.Mapper;

import com.purshase.Purshase_Api.Request.PurshaseDetailRequest;
import com.purshase.Purshase_Api.model.PurshaseDetail;
import com.purshase.Purshase_Api.response.PurshaseDetailResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurshaseMapper {
    PurshaseDetail toPurshaseDetail(PurshaseDetailRequest purshaseDetailRequest);
    PurshaseDetailResponse toPurshaseDetailResponse(PurshaseDetail purshaseDetail);
    List<PurshaseDetailResponse> toPurshaseDetailResponse(List<PurshaseDetail> purshaseDetail);
}
