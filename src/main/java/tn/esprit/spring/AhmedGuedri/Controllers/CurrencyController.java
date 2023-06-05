package tn.esprit.spring.AhmedGuedri.Controllers;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Services.ICurrencyService;
import tn.esprit.spring.AhmedGuedri.entities.Currency;
@AllArgsConstructor
@RestController
@RequestMapping("/currency")
@NoArgsConstructor
public class CurrencyController {
    @Autowired
    ICurrencyService ics;
    @GetMapping("/all")
    public List<Currency> getCurrencies() {
        List<Currency> list = ics.retrieveAllCurrencies();
        return list;
    }
    @PostMapping("/add")
    public Currency addCurrency(@RequestBody Currency c) {
        return ics.addCurrency(c);
    }
    @DeleteMapping("/remove/{Currency-id}")
    public void removeCurrency(@PathVariable("Currency-id") String id) {
        ics.deleteCurrency(id);
    }
    @PutMapping("/modify")
    public Currency modifyCurrency(@RequestBody Currency c) {
        return ics.updateCurrency(c);
    }
    @GetMapping("/retrieve-Currency/{Currency-id}")
    public Currency retrieveCurrency(@PathVariable("Currency-id") String id) {
        return ics.retrieveCurrency(id);
    }
    @PostMapping("/updatecurrates")
    public void updateexchangerate() {ics.updateCurrencyRatesnow();
    }
    }



