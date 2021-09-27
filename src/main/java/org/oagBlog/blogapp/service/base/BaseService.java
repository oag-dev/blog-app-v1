package org.oagBlog.blogapp.service.base;


import org.oagBlog.blogapp.model.response.ApiResponse;
import org.springframework.data.domain.Page;


import java.util.Optional;

//Bu interface Hamma entity uchun hizmat qiluvchi service <Kiruvchi, Chiquvchi> bunda 1 chi Http body da kelga Object, Http body da ketadigan Object
public interface BaseService<Kiruvchi, Chiquvchi, ID> {

    //Create Entity
    ApiResponse addEntity(Kiruvchi kiruvchi);

    //Get Page<Entity>
    Page<Chiquvchi> getEntiyPageBySort(Optional<Integer> page, Optional<Integer> size, Optional<String> sortBy);

    //Get by id Optional<Entity> Sababi Http Status kodini tog'ri jo'natish uchun
    Optional<Chiquvchi> getEntityById(ID id);

    //Update Entity by id
    ApiResponse editState(ID id, Kiruvchi kiruvchi);

    //Delete Entity by id
    ApiResponse deleteEntityById(Integer id);
}
