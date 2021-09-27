package org.oagBlog.blogapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.oagBlog.blogapp.model.dto.user.UserAdminDTO;
import org.oagBlog.blogapp.model.response.ApiResponse;
import org.oagBlog.blogapp.service.user.UserService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping

    public ResponseEntity getEntiyPageBySort(@RequestParam(defaultValue = "0") Optional<Integer> page,
                                             @RequestParam(defaultValue = "1") Optional<Integer> size,
                                             @RequestParam(defaultValue = "id") Optional<String> sortBy) {
        return ResponseEntity.ok(userService.getEntiyPageBySort(page, size, sortBy));
    }

    @GetMapping("{id}")
    public ResponseEntity getEntityById(@PathVariable UUID id) {
        Optional optional = userService.getEntityById(id);
        return ResponseEntity.status(optional.isEmpty() ? 404 : 200).body(optional.isEmpty() ? new ApiResponse("Bunday idli mijoz mavjud emas!!!", false) : optional.get());
    }

    @PostMapping
    public ResponseEntity addEntity(@RequestBody UserAdminDTO userAdminDTO) {
        ApiResponse apiResponse = userService.addEntity(userAdminDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }
}
