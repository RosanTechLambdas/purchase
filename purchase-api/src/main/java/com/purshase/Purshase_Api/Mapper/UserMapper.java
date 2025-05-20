package com.purshase.Purshase_Api.Mapper;

import com.purshase.Purshase_Api.Request.RequestUser;
import com.purshase.Purshase_Api.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RequestUser requestUser);
}
