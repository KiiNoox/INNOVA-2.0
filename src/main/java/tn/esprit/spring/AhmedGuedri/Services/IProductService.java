package tn.esprit.spring.AhmedGuedri.Services;

import org.springframework.mail.javamail.JavaMailSender;
import tn.esprit.spring.AhmedGuedri.entities.CurrencyType;
import tn.esprit.spring.AhmedGuedri.entities.DetailedOrders;
import tn.esprit.spring.AhmedGuedri.entities.Products;

import java.util.Date;
import java.util.List;

public interface IProductService {
    List<Products> retrieveAllProducts();
    Products addOrUpdateProduct(Products p, Long Id, CurrencyType currencyType);
    Products getProduct(Long IdProducts);
    void removeProduct(Long IdProducts);

    void addOrUpdatedetailedOrders();

    List<DetailedOrders> getDetailedOrdersbyDaterange(Date startdate, Date enddate, Long supplier);

    String getStatisticsbyDaterange(Date startdate, Date enddate, Long Supplier);

    void sendEmail(JavaMailSender javaMailSender, String toEmail, String subject, String message);

    //String getordersforeveryproductandstockbysupplier(Long id);

    String getnumberofordersbyeveryproductforasupplier(Long id);

    //List<Products> getAllProductsSortedByNumberOfOrders();

    List<Products> getProductsSortedByNumOrders();

    List<Products> getProductsbyUser(Long idUser);

    List<Products> getProductsByUser(Long userId);

}
