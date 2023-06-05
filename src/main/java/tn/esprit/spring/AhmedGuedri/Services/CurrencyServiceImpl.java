package tn.esprit.spring.AhmedGuedri.Services;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.AhmedGuedri.Repositories.CurrencyRepository;
import tn.esprit.spring.AhmedGuedri.entities.Currency;
import tn.esprit.spring.AhmedGuedri.entities.CurrencyType;

@Service
public class CurrencyServiceImpl implements ICurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;
    @Override
    public List<Currency> retrieveAllCurrencies() {
        List<Currency> currencies = (List<Currency>) currencyRepository.findAll();
        return currencies;
    }
    public Currency getCurrencyByName(CurrencyType CurrencyType) {
        return currencyRepository.findByCurrencyType(CurrencyType);
    }

    @Override
    public Currency addCurrency(Currency c) {
        return currencyRepository.save(c);
    }
    @Override
    public void deleteCurrency(String id) {
        currencyRepository.deleteById(Long.parseLong(id));
    }
    @Override

    public Currency updateCurrency(Currency c) {
        return currencyRepository.save(c);
    }
    @Override

    public Currency retrieveCurrency(String id) {
        return currencyRepository.findById(Long.parseLong(id)).get();
    }
    @Override
    @Scheduled(fixedRate = 60000) // run every hour
    public void updateCurrencyRates() {
        try {
            List<Currency> currencies =currencyRepository.findAll();
            for (Currency currency : currencies) {
                CurrencyType name = currency.getCurrencyType();
                System.out.println("name"+name);
                String namec= String.valueOf(name);
                System.out.println("namec"+namec);
                String url = "https://www.xe.com/currencyconverter/convert/?Amount=1&From="+namec.toUpperCase()+"&To=TND";
                System.out.println("URL  "+url);
                Document doc = Jsoup.connect(url).get();
// Select the <p> element with the given class and get its text content
                String pText = doc.select("p.result__BigRate-sc-1bsijpp-1.iGrAod").text();

// Extract the first 4 characters of the text content (which contain the rate)
                String rateStr = pText.substring(0, 4);

// Convert the rate to a double
                double rate = Double.parseDouble(rateStr);
                System.out.println("taaditt  ");
                System.out.println("rate "+rate);
                Currency cur = currencyRepository.findByCurrencyType(name);
                Date dt=Date.from((LocalDateTime.now()).atZone(ZoneId.systemDefault()).toInstant());
                cur.setDateC(dt);
                cur.setExchangeRate((float) rate);
                currencyRepository.save(cur);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateCurrencyRatesnow() {
        try {
            List<Currency> currencies =currencyRepository.findAll();
            for (Currency currency : currencies) {
                CurrencyType name = currency.getCurrencyType();
                System.out.println("name"+name);
                String namec= String.valueOf(name);
                System.out.println("namec"+namec);
                String url = "https://www.xe.com/currencyconverter/convert/?Amount=1&From="+namec.toUpperCase()+"&To=TND";
                System.out.println("URL  "+url);
                Document doc = Jsoup.connect(url).get();
                //System.out.println("Doc  "+doc);


// Select the <p> element with the given class and get its text content
                String pText = doc.select("p.result__BigRate-sc-1bsijpp-1.iGrAod").text();

// Extract the first 4 characters of the text content (which contain the rate)
                String rateStr = pText.substring(0, 4);

// Convert the rate to a double
                double rate = Double.parseDouble(rateStr);
                System.out.println("taaditt  ");
                System.out.println("rate "+rate);
                Currency cur = currencyRepository.findByCurrencyType(name);
                Date dt=Date.from((LocalDateTime.now()).atZone(ZoneId.systemDefault()).toInstant());
                cur.setDateC(dt);
                cur.setExchangeRate((float) rate);
                currencyRepository.save(cur);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







}

    /*@Override
    public String getExchangeRate(String CurrencyType) {
        try {
            // Build the URL for the exchange rate website
            String url = "https://www.x-rates.com/calculator/?from=" + CurrencyType.toUpperCase()+"&to=TND";

            // Scrape the exchange rate from the website
            Document doc = Jsoup.connect(url).get();
            String exchangeRate = doc.select("#cc-ratebox").first().text();

            return "The exchange rate for " + CurrencyType.toUpperCase() + " is " + exchangeRate;
            return currencyRepository.find
        } catch (Exception e) {
            return "Error retrieving exchange rate for " + CurrencyType.toUpperCase();
        }
    }
    //create a web scraping function for an exchange rate conversion*/





