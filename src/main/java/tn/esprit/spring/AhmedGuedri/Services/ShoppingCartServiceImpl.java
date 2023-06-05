package tn.esprit.spring.AhmedGuedri.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.AhmedGuedri.Repositories.ProductsRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.ShoppingCartRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.entities.Products;
import tn.esprit.spring.AhmedGuedri.entities.ShoppingCart;
import tn.esprit.spring.AhmedGuedri.entities.User;
import tn.esprit.spring.AhmedGuedri.payload.response.ShoppingCartResponse;
import tn.esprit.spring.AhmedGuedri.security.services.UserDetailsImpl;

@Service

public class ShoppingCartServiceImpl implements IShoppingCartService {
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<ShoppingCart> retrieveAllShoppingCarts() {
        List<ShoppingCart> shoppingCarts = (List<ShoppingCart>) shoppingCartRepository.findAll();
        return shoppingCarts;
    }

    @Override
    public ShoppingCartResponse retrieveAllShoppingCartsForCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ShoppingCart shoppingCart = userRepository.getById(((UserDetailsImpl) auth.getPrincipal()).getId())
                .getShoppingCart();
        Map<Products, Integer> productCountMap = new HashMap<>();

        for (Products product : shoppingCart.getProductsList()) {
            if (productCountMap.containsKey(product)) {
                productCountMap.put(product, productCountMap.get(product) + 1);
            } else {
                productCountMap.put(product, 1);
            }
        }
        List<Map<String, Object>> listProduct = new ArrayList<>();
        for (Map.Entry<Products, Integer> entry : productCountMap.entrySet()) {
            Map<String, Object> productDistinct = new HashMap<>();
            productDistinct.put("product", entry.getKey());
            productDistinct.put("count", entry.getValue());
            listProduct.add(productDistinct);
        }
        float total = (float) shoppingCart.getProductsList().stream().mapToDouble(Products::getPrice).sum();
        float totalTax = (float) shoppingCart.getProductsList().stream().mapToDouble(products ->
                products.getTax().getTaxableAmount()
        ).sum();
        return new ShoppingCartResponse(shoppingCart.getCart_Id(), listProduct, total, totalTax, total + totalTax);

    }


    @Override
    public ShoppingCart addShoppingCart(ShoppingCart s) {
        return shoppingCartRepository.save(s);
    }

    @Override
    public void deleteShoppingCart(String id) {
        shoppingCartRepository.deleteById(Long.parseLong(id));
    }

    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart s) {
        return shoppingCartRepository.save(s);
    }

    @Override
    public ShoppingCart retrieveShoppingCart(String id) {
        return shoppingCartRepository.findById(Long.parseLong(id)).get();
    }

    //clear products from shopping cart
    @Override
    public void clearShoppingCart(String id) {
        ShoppingCart shoppingCart = retrieveShoppingCart(id);
        shoppingCart.setProductsList(null);
        updateShoppingCart(shoppingCart);
    }

    //remove products from shopping cart when product stock is 0
    @Override
    public void removeProductFromShoppingCartEmpty(String id) {
        ShoppingCart shoppingCart = retrieveShoppingCart(id);
        shoppingCart.getProductsList().removeIf(p -> p.getNumberOfStock() == 0);
        updateShoppingCart(shoppingCart);
    }

    //remove a product from shopping cart
    @Override
    public void removeProductFromShoppingCart(String id, String idProduct) {
        ShoppingCart shoppingCart = retrieveShoppingCart(id);
        AtomicInteger index = new AtomicInteger();
        shoppingCart.getProductsList().removeIf(p -> {
            System.out.println(index.get());
            if (p.getIdProducts() == Long.parseLong(idProduct) && index.get() == 0) {
                index.incrementAndGet();
                return true;
            }
            return false;


        });
        index.set(0);
        updateShoppingCart(shoppingCart);
    }


    //add product to shopping cart
    @Override
    public void addProductToShoppingCart(String id, String idProduct) {
        ShoppingCart shoppingCart = retrieveShoppingCart(id);
        shoppingCart.getProductsList().add(productsRepository.findById(Long.parseLong(idProduct)).get());
        updateShoppingCart(shoppingCart);
    }


 
    //create a shopping cart for every user created
    @Override
    public void createShoppingCartForUser(String id) {
        User u = userRepository.findById(Long.parseLong(id)).get();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(u);
        addShoppingCart(shoppingCart);
        u.setShoppingCart(shoppingCart);
        userRepository.save(u);

    }

    //create a shopping cart for all users
    @Scheduled(cron = "0 0 0 * * ?")
    @AfterReturning("execution(* tn.esprit.spring.AhmedGuedri.Services.UserServiceImpl.addUser(..))")
    @Override
    public void createShoppingCartForAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getShoppingCart() == null)
                createShoppingCartForUser(String.valueOf(users.get(i).getId()));
        }
    }

    //get quantity of products in a shoppingcart
    @Override
    public ShoppingCart getShoppingCartByUser(String id) {
        User u = userRepository.findById(Long.parseLong(id)).get();
        return u.getShoppingCart();
    }
    //get products in shopping cart by user
    @Override   
    public List<Products> getProductsInShoppingCartByUser(String id) {
        ShoppingCart shoppingCart = getShoppingCartByUser(id);
        return shoppingCart.getProductsList();
    }


//get quantity of products in a shoppingcart
@Override
public int getQuantityOfProductsInShoppingCart(String id) {
    ShoppingCart shoppingCart = retrieveShoppingCart(id);
    return shoppingCart.getProductsList().size();
}
//get total price of shopping cart
@Override
public float getTotalPriceShoppingCart(String id) {
    ShoppingCart shoppingCart = retrieveShoppingCart(id);
    float totalPrice = 0;
    for (int i = 0; i < shoppingCart.getProductsList().size(); i++) {
        totalPrice += shoppingCart.getProductsList().get(i).getPrice();
    }
    return totalPrice;
}
//get quantity of products in a shoppingcart by User
    @Override
    public int getQuantityOfProductsInShoppingCartByUser(String id) {
        ShoppingCart shoppingCart = getShoppingCartByUser(id);
        return shoppingCart.getProductsList().size();
    }
//get total price of shopping cart by user
    @Override
    public float getTotalPriceShoppingCartByUser(String id) {
        ShoppingCart shoppingCart = getShoppingCartByUser(id);
        float totalPrice = 0;
        for (int i = 0; i < shoppingCart.getProductsList().size(); i++) {
            totalPrice += shoppingCart.getProductsList().get(i).getPrice();
        }
        return totalPrice;
    }
    //get shopping cart id by user
    @Override
    public String getShoppingCartIdByUser(String id) {
        ShoppingCart shoppingCart = getShoppingCartByUser(id);
        return String.valueOf(shoppingCart.getIdShoppingCart());
    }
    
}




