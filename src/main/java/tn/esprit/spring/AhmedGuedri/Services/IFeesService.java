package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.Fees;

import java.util.List;

public interface IFeesService {

    //implementing the methods of the interface
    public Fees addFees(Fees fees,Long idUser);

    public Fees updateFees(Fees fees,Long idUser);
    public void deleteFees(Long fees);
    public List<Fees> retrieveAllFees();
    public Fees retrieveFees(Long idFees);

}
