package tn.esprit.spring.AhmedGuedri.Services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.AhmedGuedri.Repositories.InquiryRepository;

import tn.esprit.spring.AhmedGuedri.Repositories.ProductsRepository;
import tn.esprit.spring.AhmedGuedri.Services.ProductService;
import tn.esprit.spring.AhmedGuedri.entities.Inquiry;
import tn.esprit.spring.AhmedGuedri.entities.Products;

@Service

public class InquiryServiceImpl implements IInquiryService {
    @Autowired
    InquiryRepository inquiryRepository;

    ProductService productService;
    @Autowired
    ProductsRepository productsRepository;

    
    @Override
    public List<Inquiry> retrieveAllInquiries() {
        List<Inquiry> inquiries = (List<Inquiry>) inquiryRepository.findAll();
        return inquiries;
    }
    @Override
    public Inquiry addInquiry(Inquiry i) {
        return inquiryRepository.save(i);
    }
    @Override
    public void deleteInquiry(String id) {
        inquiryRepository.deleteById(Long.parseLong(id));
    }
    @Override
    public Inquiry updateInquiry(Inquiry i) {
        return inquiryRepository.save(i);
    }
    @Override
    public Inquiry retrieveInquiry(String id) {
        return inquiryRepository.findById(Long.parseLong(id)).get();
    }


    //add product to inquiry
    @Override
    public void addProductToInquiry(String id, Long productId) {
    
        Products p = productsRepository.findById(productId).get();
        Inquiry inquiry = retrieveInquiry(id);
        inquiry.getProductList().add(p);
        updateInquiry(inquiry);
    }

    //clear products from inquiry
    @Override
    public void clearInquiry(String id) {
        Inquiry inquiry = retrieveInquiry(id);
        inquiry.setProductList(null);
        updateInquiry(inquiry);
    }

 
    //remove products from inquiry when product stock is 0
    @Override
    public void removeProductFromInquiry(String id) {
        Inquiry inquiry = retrieveInquiry(id);
        inquiry.getProductList().removeIf(p -> p.getNumberOfStock() == 0);
        updateInquiry(inquiry);
        
    }
    //get products from inquiry
    @Override
    public List<Products> getProductsFromInquiry(String id) {
        Inquiry inquiry = retrieveInquiry(id);
        return inquiry.getProductList();
    }


}
