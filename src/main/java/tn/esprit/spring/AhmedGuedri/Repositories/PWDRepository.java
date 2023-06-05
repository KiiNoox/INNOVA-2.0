package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.HashedPWD;
import tn.esprit.spring.AhmedGuedri.entities.User;

@Repository
public interface PWDRepository extends JpaRepository<HashedPWD,Long> {
    HashedPWD findByUser(User user);

}
