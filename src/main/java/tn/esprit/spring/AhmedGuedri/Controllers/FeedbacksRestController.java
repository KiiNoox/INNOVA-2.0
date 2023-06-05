package tn.esprit.spring.AhmedGuedri.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import tn.esprit.spring.AhmedGuedri.Services.FeedbackService;
import tn.esprit.spring.AhmedGuedri.entities.Feedbacks;
import tn.esprit.spring.AhmedGuedri.entities.Inquiry;
import tn.esprit.spring.AhmedGuedri.entities.Products;

import java.util.List;
import java.util.Set;


@RestController
@AllArgsConstructor
@RequestMapping("/feedbacks")

public class FeedbacksRestController {

    FeedbackService feedbackService;
    @PostMapping("/add")
    Feedbacks addFeedback(@RequestBody Feedbacks feedback){
        return feedbackService.addOrUpdateFeedback(feedback);
    }
    @PostMapping("/update")
    Feedbacks updateFeedback(@RequestBody Feedbacks feedback){
        return feedbackService.addOrUpdateFeedback(feedback);
    }
    @GetMapping("/get/{id}")
    Feedbacks getFeedback(@PathVariable("id") Long Id){
        return feedbackService.getFeedback(Id);
    }
    @GetMapping("/all")
    List<Feedbacks> getAllFeedbacks(){return feedbackService.retrieveAllFeedbacks();}
    @DeleteMapping("/delete/{id}")
    void deleteInquiry(@PathVariable("id") Long Id){
        feedbackService.removeFeedback(Id);
    }

    @GetMapping("/getFeedbacksByProduct/{id}")
    List<Feedbacks> getFeedbacksByProduct(@PathVariable("id") Long Id){
        return feedbackService.getFeedbacksByProduct(Id);
    }
    @PostMapping("/addAndAssigntoProduct/{id}")
    Feedbacks addAndAssigntoProduct(@RequestBody Feedbacks feedback,@PathVariable("id") Long Id){
        return feedbackService.addAndAssigntoProduct(feedback,Id);
    }
    @GetMapping("/getAverageFeedbacks/{id}")
    float getAverageFeedbacks(@PathVariable("id") Long Id){
        return feedbackService.getAverageFeedbacks(Id);
    }
    @GetMapping("/getAverageFeedbacks5Stars")
    float getAverageFeedbacks5Stars(){
        return feedbackService.getAverageFeedbacks5Stars();
    }
    @GetMapping("/getAverageFeedbacksAllProducts")
    float getAverageFeedbacksAllProducts(){
        return feedbackService.getAverageFeedbacksAllProducts();
    }
    @GetMapping("/getMostRatedProduct")
    Products getMostRatedProduct(){
        return feedbackService.getMostRatedProduct();
    }
    @GetMapping("/getMostRatedProduct5Stars")
    Products getMostRatedProduct5Stars(){
        return feedbackService.getMostRatedProduct5Stars();
    }
    @GetMapping("/getLeastRatedProduct")

    Products getLeastRatedProduct(){
        return feedbackService.getLeastRatedProduct();
    }
    @GetMapping("/getUniqueProducts5Stars")
    Set<Products> getUniqueProducts5Stars(){
        return feedbackService.getUniqueProducts5Stars();
    }
    @GetMapping("/getUniqueProductsLessThan3Stars")
     Set<Products> getUniqueProductsLessThan3Stars(){
        return feedbackService.getUniqueProductsLessThan3Stars();
    }


}
