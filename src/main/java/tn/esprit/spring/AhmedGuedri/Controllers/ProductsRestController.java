package tn.esprit.spring.AhmedGuedri.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Repositories.ProductsRepository;
import tn.esprit.spring.AhmedGuedri.Services.IProductService;
import tn.esprit.spring.AhmedGuedri.entities.CurrencyType;
import tn.esprit.spring.AhmedGuedri.entities.DetailedOrders;
import tn.esprit.spring.AhmedGuedri.entities.Products;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductsRestController {
    private IProductService productService;
    private ProductsRepository productsRepository;
    @PostMapping("/add/{id}")
    @PreAuthorize("hasRole('PROVIDER') or hasRole('ADMIN')")
    Products addProduct(@RequestBody Products products, @PathVariable("id") Long Id, @RequestParam CurrencyType currencyType){
        return productService.addOrUpdateProduct(products,Id,currencyType);
    }
    @PreAuthorize("hasRole('PROVIDER') or hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    Products updateProduct(@RequestBody Products products, @PathVariable("id") Long Id,@RequestParam CurrencyType currencyType){
        return productService.addOrUpdateProduct(products,Id,currencyType);
    }
    @GetMapping("/get/{id}")
    Products getProduct(@PathVariable("id") Long IdProduct){
        return productService.getProduct(IdProduct);
    }
    @GetMapping("/getbyuser/{id}")
    List<Products> getProductbyuser(@PathVariable("id") Long IdUser){
        return productService.getProductsbyUser(IdUser);
    }
    @GetMapping("/All")
    List<Products> getAllProducts(){
        return productService.retrieveAllProducts();
        }
    @PreAuthorize("hasRole('PROVIDER') or hasRole('ADMIN')")
    @GetMapping("/getbyuser1/{userId}")
    public List<Products> getProductsByUser(@PathVariable Long userId) {
        return productService.getProductsByUser(userId);
    }
    @PreAuthorize("hasRole('PROVIDER') or hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    void deleteProducts(@PathVariable("id") Long IdProduct){
        productService.removeProduct(IdProduct);
    }
    @PreAuthorize("hasRole('PROVIDER') or hasRole('ADMIN')")
    @GetMapping("/order/get/{dates}/{datee}/{id}")
    List<DetailedOrders> getDetailedOrdersbyDaterange(@PathVariable("dates")@DateTimeFormat(pattern="yyyy-MM-dd") Date dates, @PathVariable("datee")@DateTimeFormat(pattern="yyyy-MM-dd") Date datee, @PathVariable("id") Long id){
        return productService.getDetailedOrdersbyDaterange(dates,datee,id);
    }
    @PreAuthorize("hasRole('PROVIDER') or hasRole('ADMIN')")
    @GetMapping("/order/getstats/{dates}/{datee}/{id}")
    String getDetailedOrdersbyDaterangeStats(@PathVariable("dates")@DateTimeFormat(pattern="yyyy-MM-dd") Date dates, @PathVariable("datee")@DateTimeFormat(pattern="yyyy-MM-dd") Date datee, @PathVariable("id") Long id){
        return productService.getStatisticsbyDaterange(dates,datee,id);
    }
    @PreAuthorize("hasRole('PROVIDER') or hasRole('ADMIN')")
    @GetMapping("/order/getprods/{id}")
    String getprodsandstockbysup(@PathVariable("id") Long id){
        return productService.getnumberofordersbyeveryproductforasupplier(id);
    }
    /*@GetMapping("/sortByOrders")
    public List<Products> getProductsSortedByOrders() {
        List<Products> productsList = productService.retrieveAllProducts();
        System.out.println(productsList);
        //Collections.sort(productsList, (p1, p2) ->
          //      Integer.compare(p2.getProduct_order().size(), p1.getProduct_order().size()));
        return productsList;
    }
    @GetMapping("/sortedByNumOrders")
    public List<Products> getProductsSortedByNumOrders() {
        List<Object[]> productsWithNumOrders = productsRepository.findAllSortedByNumOrders();
        List<Products> sortedProducts = new ArrayList<>();

        for (Object[] productWithNumOrders : productsWithNumOrders) {
            Products product = (Products) productWithNumOrders[0];
            sortedProducts.add(product);
        }

        return sortedProducts;
    }*/

    @GetMapping("/sortedByNumOrders")
    public List<Products> getAllProductsSortedByOrderCount() {
        List<Products> products = productsRepository.findAll();
        products.sort(Comparator.comparingInt(p -> p.getProduct_order().size()));
        return products;
    }
    @GetMapping("/first")
    public Products getfirst() {
        List<Products> prds = productsRepository.findAll();
        if (!prds.isEmpty()) {
            return prds.get(0);
        } else {
            return null;
        }
    }
    @GetMapping("/Aexf")
    List<Products> getAllProductsexf(){
        List<Products> objects = productService.retrieveAllProducts();
        if (!objects.isEmpty()) {
            objects.remove(0); // remove the first object
        }
        return objects;
    }
    }
