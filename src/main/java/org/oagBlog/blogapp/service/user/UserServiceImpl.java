package org.oagBlog.blogapp.service.user;

import org.oagBlog.blogapp.entity.user.UserEntity;
import org.oagBlog.blogapp.model.dto.user.UserAdminDTO;
import org.oagBlog.blogapp.model.response.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public ApiResponse addEntity(UserAdminDTO userAdminDTO) {
        return null;
    }

    @Override
    public Optional<UserEntity> getEntityById(UUID id) {
        return Optional.empty();
    }

    @Override
    public ApiResponse editState(UUID uuid, UserAdminDTO userAdminDTO) {
        return null;
    }


    @Override
    public ApiResponse deleteEntityById(Integer id) {
        return null;
    }

    @Override
    public Page getEntiyPageBySort(Optional page, Optional size, Optional sortBy) {
        return null;
    }
}
