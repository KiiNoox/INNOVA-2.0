//create shopping cart controller
package tn.esprit.spring.AhmedGuedri.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.AhmedGuedri.Services.IShoppingCartService;
import tn.esprit.spring.AhmedGuedri.entities.Products;
import tn.esprit.spring.AhmedGuedri.entities.ShoppingCart;
import tn.esprit.spring.AhmedGuedri.payload.response.ShoppingCartResponse;


@RestController
@RequestMapping("/shoppingCart")
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
public class ShoppingCartController {

    @Autowired
    IShoppingCartService shoppingCartService;
    @GetMapping("/retrieve-all-shoppingCarts")
    public List<ShoppingCart> getShoppingCarts() {
        List<ShoppingCart> list = shoppingCartService.retrieveAllShoppingCarts();
        return list;
    }

    @GetMapping("/retrieve-shoppingCart-for-current-user")
    public ShoppingCartResponse getShoppingCartsForCurrentUser() {

        return shoppingCartService.retrieveAllShoppingCartsForCurrentUser();
    }


    @PostMapping("/add-shoppingCart")
    public ShoppingCart addShoppingCart(@RequestBody ShoppingCart s) {
        ShoppingCart shoppingCart = shoppingCartService.addShoppingCart(s);
        return shoppingCart;
    }
    @DeleteMapping("/remove-shoppingCart/{shoppingCart-id}")
    public void removeShoppingCart(@PathVariable("shoppingCart-id") String shoppingCartId) {
        shoppingCartService.deleteShoppingCart(shoppingCartId);
    }
    @PutMapping("/modify-shoppingCart")
    public ShoppingCart modifyShoppingCart(@RequestBody ShoppingCart s) {
        return shoppingCartService.updateShoppingCart(s);
    }
    @GetMapping("/retrieve-shoppingCart/{shoppingCart-id}")
    public ShoppingCart retrieveShoppingCart(@PathVariable("shoppingCart-id") String shoppingCartId) {
        return shoppingCartService.retrieveShoppingCart(shoppingCartId);
    }
    @PutMapping("/clear-shoppingCart/{shoppingCart-id}")
    public void clearShoppingCart(@PathVariable("shoppingCart-id") String shoppingCartId) {
        shoppingCartService.clearShoppingCart(shoppingCartId);
    }
    @PutMapping("/remove-product-from-shoppingCart-empty/{shoppingCart-id}")
    public void removeProductFromShoppingCartEmpty(@PathVariable("shoppingCart-id") String shoppingCartId) {
        shoppingCartService.removeProductFromShoppingCartEmpty(shoppingCartId);
    }
    @PutMapping("/remove-product-from-shoppingCart/{shoppingCart-id}/{product-id}")
    public void removeProductFromShoppingCart(@PathVariable("shoppingCart-id") String shoppingCartId,
            @PathVariable("product-id") String productId) {
        shoppingCartService.removeProductFromShoppingCart(shoppingCartId, productId);
    }
    @PutMapping("/add-product-to-shoppingCart/{shoppingCart-id}/{product-id}")
    public void addProductToShoppingCart(@PathVariable("shoppingCart-id") String shoppingCartId,
            @PathVariable("product-id") String productId) {
        shoppingCartService.addProductToShoppingCart(shoppingCartId, productId);
    }
    @PutMapping("/create-shoppingCart-for-user/{user-id}")
    public void createShoppingCartForUser(@PathVariable("user-id") String userId) {
        shoppingCartService.createShoppingCartForUser(userId);
    }
    
    @PutMapping("/create-shoppingCart-for-all-users")
    public void createShoppingCartForAllUsers() {
        shoppingCartService.createShoppingCartForAllUsers();
    }
    @GetMapping("/get-total-price-shoppingCart/{shoppingCart-id}")
    public float getTotalPriceShoppingCart(@PathVariable("shoppingCart-id") String shoppingCartId) {
        return shoppingCartService.getTotalPriceShoppingCart(shoppingCartId);
    }
    @GetMapping("/get-quantity-of-products-in-shoppingCart/{shoppingCart-id}")
    public int getQuantityOfProductsInShoppingCart(@PathVariable("shoppingCart-id") String shoppingCartId) {
        return shoppingCartService.getQuantityOfProductsInShoppingCart(shoppingCartId);
    }
    @GetMapping("/get-shoppingCart-by-user/{user-id}")
    public ShoppingCart getShoppingCartByUser(@PathVariable("user-id") String userId) {
        return shoppingCartService.getShoppingCartByUser(userId);
    }
    @GetMapping("/get-products-in-shoppingCart-by-user/{user-id}")
    public List<Products> getProductsInShoppingCartByUser(@PathVariable("user-id") String userId) {
        return shoppingCartService.getProductsInShoppingCartByUser(userId);
    }
    @GetMapping("/get-total-price-shoppingCart-by-user/{user-id}")
    public float getTotalPriceShoppingCartByUser(@PathVariable("user-id") String userId) {
        return shoppingCartService.getTotalPriceShoppingCart(userId);
    }
    @GetMapping("/get-quantity-of-products-in-shoppingCart-by-user/{user-id}")
    public int getQuantityOfProductsInShoppingCartByUser(@PathVariable("user-id") String userId) {
        return shoppingCartService.getQuantityOfProductsInShoppingCart(userId);
    }
    //get shopping cart id by user id
    @GetMapping("/get-shoppingCart-id-by-user/{user-id}")
    public String getShoppingCartIdByUser(@PathVariable("user-id") String userId) {
        return shoppingCartService.getShoppingCartIdByUser(userId);
    }
    
}