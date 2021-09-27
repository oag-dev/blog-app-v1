package org.oagBlog.blogapp.service.user;

import org.oagBlog.blogapp.entity.user.UserEntity;
import org.oagBlog.blogapp.model.dto.user.UserAdminDTO;
import org.oagBlog.blogapp.service.base.BaseService;

import java.util.UUID;

public interface UserService extends BaseService<UserAdminDTO, UserEntity, UUID> {

}
