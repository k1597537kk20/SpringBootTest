package com.jhang.demo.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jhang.demo.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
/*
    易讀訪問方法 : https://www.cnblogs.com/xiaoqi/p/queries-in-spring-data-mongodb.html

    // 找出name欄位值有包含參數的所有文件，且不分大小寫
    List<Product> findByNameLikeIgnoreCase(String name);

    // 找出id欄位值有包含在參數之中的所有文件
    List<Product> findByIdIn(List<String> ids);

    // 是否有文件的email欄位值等於參數
    boolean existsByEmail(String email);

    // 找出username與password欄位值皆符合參數的一筆文件
    Optional<User> findByUsernameAndPassword(String email, String pwd);
*/

    @Query("{'$and': [{'price': {'$gte': ?0, '$lte': ?1}}, {'name': {'$regex': ?2, '$options': 'i'}}]}")
    List<Product> findByPriceBetweenAndNameLikeIgnoreCase(int priceFrom, int priceTo, String name, Sort sort);

    @Query("{'price': {'$gte': ?0, '$lte': ?1}}")
    List<Product> findByPriceBetween(int from, int to);

    @Query("{'name': {'$regex': ?0, '$options': 'i'}}")
    List<Product> findByNameLikeIgnoreCase(String name);

    @Query("{'$and': [{'price': {'$gte': ?0, '$lte': ?1}}, {'name': {'$regex': ?2, '$options': 'i'}}]}")
    List<Product> findByPriceBetweenAndNameLikeIgnoreCase(int priceFrom, int priceTo, String name);

    @Query(value = "{'_id': {'$in': ?0}}", count = true)
    int countByIdIn(List<String> ids);

    @Query(value = "{'_id': {'$in': ?0}}", exists = true)
    boolean existsByIdIn(List<String> ids);

    @Query(delete = true)
    void deleteByIdIn(List<String> ids);

    @Query(sort = "{'name': 1, 'price': -1}")
    List<Product> findByIdInOrderByNameAscPriceDesc(List<String> ids);

}
