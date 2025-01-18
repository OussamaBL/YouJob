package com.youjob.youjob.web.vm.mapper.auth;

import com.youjob.youjob.domain.User;
import com.youjob.youjob.web.vm.auth.LoginVM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface LoginMapper {
    /*
   ResponseUserVM toResponseUserVM(User user);
*/
    User toUser(LoginVM loginVM);
}
