package com.youjob.youjob.web.vm.mapper.user;

import com.youjob.youjob.domain.Business;
import com.youjob.youjob.domain.Customer;
import com.youjob.youjob.domain.Handyman;
import com.youjob.youjob.domain.User;
import com.youjob.youjob.web.vm.auth.RegisterVM;
import com.youjob.youjob.web.vm.user.ProfileVM;
import com.youjob.youjob.web.vm.user.ResponseUserVM;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    ResponseUserVM toResponseUserVM(User user);

    @Mapping(target = "vatNumber", source = "vatNumber")
    @Mapping(target = "annonceList", ignore = true)
    @Mapping(target = "id", ignore = true)
    Business toBusiness(ProfileVM profileVM);

    @Mapping(target = "skills", source = "skills")
    @Mapping(target = "rating", source = "rating")
    @Mapping(target = "consultations", ignore = true)
    @Mapping(target = "id", ignore = true)
    Handyman toHandyman(ProfileVM profileVM);

    @Mapping(target = "annonceList", ignore = true)
    @Mapping(target = "id", ignore = true)
    Customer toCustomer(ProfileVM profileVM);

    default User mapToSpecificUser(ProfileVM profileVM) {
        switch (profileVM.getRole().name()) {
            case "BUSINESS":
                return toBusiness(profileVM);
            case "HANDYMAN":
                return toHandyman(profileVM);
            case "CUSTOMER":
                return toCustomer(profileVM);
            default:
                throw new IllegalArgumentException("Invalid user type: " + profileVM.getRole().name());
        }
    }



}
