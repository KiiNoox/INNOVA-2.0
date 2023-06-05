package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.ChatRoom;
import tn.esprit.spring.AhmedGuedri.entities.ProductCategory;
import tn.esprit.spring.AhmedGuedri.entities.Products;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

    @Query("SELECT p FROM Products p ORDER BY size(p.Product_order)")
    List<Products> findAllOrderByNumberOfOrders();

    @Query("SELECT p, COUNT(o) as num_orders FROM Products p JOIN p.Product_order o GROUP BY p ORDER BY num_orders DESC")
    List<Object[]> findProductsOrderByNumOrders();

    List<Products>findProductsByUserProducts(Long id);

    @Query("SELECT p FROM Products p JOIN p.userProducts u WHERE u.id = :userId")
    List<Products> findByUserId(@Param("userId") Long userId);

    @Query("select IdProducts,AdressProducts,Description,NameProducts,NumberOfStock,Price,Available from Products ")
    List<Products> getAllProducts();
    @Query("select p from Products p where p.IdProducts = :id")
    Products findProductsByIdProducts(Long id);
}