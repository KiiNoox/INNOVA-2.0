package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.Products;
import tn.esprit.spring.AhmedGuedri.entities.ShoppingCart;
import tn.esprit.spring.AhmedGuedri.entities.User;
import tn.esprit.spring.AhmedGuedri.payload.response.ShoppingCartResponse;

import java.util.List;

public interface IShoppingCartService {
    List<ShoppingCart> retrieveAllShoppingCarts();
    ShoppingCart addShoppingCart(ShoppingCart s);
    void deleteShoppingCart(String id);
    ShoppingCart updateShoppingCart(ShoppingCart s);
    ShoppingCart retrieveShoppingCart(String id);
    void clearShoppingCart(String id);
    void removeProductFromShoppingCartEmpty(String id);
    void removeProductFromShoppingCart(String id, String idProduct);
    void addProductToShoppingCart(String id, String idProduct);
    float getTotalPriceShoppingCart(String id);
    void createShoppingCartForUser(String idUser);
    void createShoppingCartForAllUsers();
    int getQuantityOfProductsInShoppingCart(String id);
    ShoppingCart getShoppingCartByUser(String id);
    List<Products> getProductsInShoppingCartByUser(String id);
    int getQuantityOfProductsInShoppingCartByUser(String id);
    float getTotalPriceShoppingCartByUser(String id);
    String getShoppingCartIdByUser(String id);
    ShoppingCartResponse retrieveAllShoppingCartsForCurrentUser();


}