package tn.esprit.spring.AhmedGuedri.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.*;
 import tn.esprit.spring.AhmedGuedri.Services.IFeesService;
 import tn.esprit.spring.AhmedGuedri.entities.Fees;

 import java.util.List;

 @RestController
 @RequestMapping("/fees")
 public class FeesController {

     @Autowired
     IFeesService iFeesService;

     @PostMapping("/addFees")
     public Fees addFees(@RequestBody Fees fees,Long idUser){

         return iFeesService.addFees(fees,idUser);
     }

     @PutMapping("/updateFees")
     public Fees updateFees(@RequestBody Fees fees,Long idUser){
         return iFeesService.updateFees(fees,idUser);
     }

     @DeleteMapping("/deleteFees/{idFees}")
     public void deleteFees(@PathVariable("idFees") Long idFees){
         iFeesService.deleteFees(idFees);
     }

     @GetMapping("/retrieveAllFees")
     public List<Fees> retrieveAllFees(){
         return iFeesService.retrieveAllFees();
     }

     @GetMapping("/retrieveFees/{idFees}")
     public Fees retrieveFees(@PathVariable("idFees") Long idFees){
         return iFeesService.retrieveFees(idFees);
     }

 }



