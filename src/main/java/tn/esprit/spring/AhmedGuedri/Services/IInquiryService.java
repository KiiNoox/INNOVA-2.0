package tn.esprit.spring.AhmedGuedri.Services;
import tn.esprit.spring.AhmedGuedri.entities.Inquiry;

import tn.esprit.spring.AhmedGuedri.entities.Products;
import java.util.List;


public interface IInquiryService {
    public List<Inquiry> retrieveAllInquiries();
    public Inquiry addInquiry(Inquiry i);
    public void deleteInquiry(String id);
    public Inquiry updateInquiry(Inquiry i);
    public Inquiry retrieveInquiry(String id);

    public void clearInquiry(String id);
    public void removeProductFromInquiry(String id);
    void addProductToInquiry(String id, Long productId);
    List<Products> getProductsFromInquiry(String id);

}
