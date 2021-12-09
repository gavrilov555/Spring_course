package com.geekbrains.spring.web.repositories;

import com.geekbrains.spring.web.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface ProductsRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("select p from Product p where p.cost between ?1 and ?2")
    List<Product> findAllByScoreBetween(Integer min, Integer max);
}
