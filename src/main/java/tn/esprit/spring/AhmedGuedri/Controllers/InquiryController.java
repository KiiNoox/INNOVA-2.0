 package tn.esprit.spring.AhmedGuedri.Controllers;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.PutMapping;
 import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.AhmedGuedri.entities.Products;
import tn.esprit.spring.AhmedGuedri.Services.IInquiryService;
import tn.esprit.spring.AhmedGuedri.Services.ProductService;
import tn.esprit.spring.AhmedGuedri.entities.Inquiry;
 @RestController
 @CrossOrigin(origins = "*", maxAge = 3600)
 public class InquiryController {
     @Autowired
     IInquiryService iis;
     @GetMapping("/retrieve-all-Inquiries")
     public List<Inquiry> getInquiries() {
         return  iis.retrieveAllInquiries();
         
     }
     @PostMapping("/add-Inquiry")
     public Inquiry addInquiry(@RequestBody Inquiry i) {
         return iis.addInquiry(i);
     }
     @DeleteMapping("/remove-Inquiry/{Inquiry-id}")
     public void removeInquiry(@PathVariable("Inquiry-id") String id) {
         iis.deleteInquiry(id);
     }
     @PutMapping("/modify-Inquiry")
     public Inquiry modifyInquiry(@RequestBody Inquiry i) {
         return iis.updateInquiry(i);
     }
     @GetMapping("/retrieve-Inquiry/{Inquiry-id}")
     public Inquiry retrieveInquiry(@PathVariable("Inquiry-id") String id) {
         return iis.retrieveInquiry(id);
     }

        @PutMapping("/clear-Inquiry/{Inquiry-id}")
        public void clearInquiry(@PathVariable("Inquiry-id") String id) {
            iis.clearInquiry(id);
 }
 
        @PutMapping("/remove-product-from-Inquiry-empty/{Inquiry-id}")
        public void removeProductFromInquiry(@PathVariable("Inquiry-id") String id) {
            iis.removeProductFromInquiry(id);
 }
 @PutMapping("/add-product-to-Inquiry/{Inquiry-id}/{product-id}")
    public void addProductToInquiry(@PathVariable("Inquiry-id") String id,
            @PathVariable("product-id") Long productId) {
        iis.addProductToInquiry(id, productId);
    }
@GetMapping("get-products-from-Inquiry/{Inquiry-id}")
    public List<Products> getProductsFromInquiry(@PathVariable("Inquiry-id") String id) {
        return iis.getProductsFromInquiry(id);
    }
 

}

