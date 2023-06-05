package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.Feedbacks;
import tn.esprit.spring.AhmedGuedri.entities.Inquiry;
import tn.esprit.spring.AhmedGuedri.entities.Products;

import java.util.List;
import java.util.Set;


public interface IFeedbackService {
    List<Feedbacks> retrieveAllFeedbacks();
    Feedbacks addOrUpdateFeedback(Feedbacks f);
    Feedbacks getFeedback(Long IdFeedbacks);
    void removeFeedback(Long IdFeedbacks);

    List<Feedbacks> getFeedbacksByProduct(Long IdProduct);

    Feedbacks addAndAssigntoProduct(Feedbacks e, Long IdProduct);
    float getAverageFeedbacks(Long IdProduct);
    float getAverageFeedbacks5Stars();
    float getAverageFeedbacksAllProducts();
    Products getMostRatedProduct();
    Products getMostRatedProduct5Stars();
    Products getLeastRatedProduct();
    Set<Products> getUniqueProducts5Stars();
    Set<Products> getUniqueProductsLessThan3Stars();
}
