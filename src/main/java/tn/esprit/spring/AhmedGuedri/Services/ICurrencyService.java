package tn.esprit.spring.AhmedGuedri.Services;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.spring.AhmedGuedri.entities.Currency;
import tn.esprit.spring.AhmedGuedri.entities.CurrencyType;

public interface ICurrencyService {
    public List<Currency> retrieveAllCurrencies();
    public Currency addCurrency(Currency c);
    public void deleteCurrency(String id);
    public Currency updateCurrency(Currency c);
    public Currency retrieveCurrency(String id);
    //public Currency getCurrencyByName(String CurrencyType);

    @Scheduled(fixedRate = 3600000) // run every hour
    void updateCurrencyRates();

    void updateCurrencyRatesnow();
}
