package tn.esprit.spring.AhmedGuedri.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repositories.FeesRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.entities.*;
import java.util.List;


@Service
@AllArgsConstructor
public class FeesService implements IFeesService{
    //implementing the methods of the interface with the help of the repository
    FeesRepository feesRepository;
UserRepository userRepository;
    @Override
    public Fees addFees(Fees fees,Long idUser) {
        // TODO Auto-generated method stub
        User u= userRepository.findById(idUser).get();
        fees.setUserFees(u);
        System.out.println(fees.getIdFees());
        System.out.println(fees.getUserFees());
        return  feesRepository.save(fees);
    }

    @Override
    public Fees updateFees(Fees fees,Long idUser) {
        User u =userRepository.findById(idUser).get();
        fees.setUserFees(u);
        feesRepository.save(fees);
        return fees;
    }
    @Override
    public void deleteFees(Long fees) {
        feesRepository.deleteById(fees);
    }
    @Override
    public List<Fees> retrieveAllFees() {
        List<Fees> fees = (List<Fees>) feesRepository.findAll();
        return fees;
    }
    @Override
    public Fees retrieveFees(Long idFees) {
        return feesRepository.findById(idFees).get();
    }

}
