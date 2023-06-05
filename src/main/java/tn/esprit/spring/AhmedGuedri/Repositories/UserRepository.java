package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.RolesTypes;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailEquals(String email);
    List<User> findByFirstNameOrLastNameContainingIgnoreCase(String firstName, String lastName);


    // <Optional> User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.roles = :r")
    public List<User> searchUserRoles(@Param("r") RolesTypes role);

    @Query(value="SELECT u.* \n" +
            "FROM user u , role r , user_roles ur\n" +
            "WHERE r.name='ROLE_DELIVERY' and ur.roles_id=r.id and u.id=ur.user_id;",nativeQuery = true)
    public List<User> All_Delivery();

    //sql query count int chatroom of user
    @Query(value = "SELECT COUNT(*) FROM user_chat_rooms r WHERE r.user_id = ?1",
            nativeQuery = true)
    public int countChatRoomByUser(Long userid);

    @Query("SELECT count(u) FROM User u WHERE u.Enabled = true")
    public int NumberOfSubs();

    @Query("select e,h from User e, HashedPWD h,Role r where e.email=:Email and e.hashedPWD.PassId=h.PassId")
    public List<Object[]> Authentification(@Param("Email") String email);

    Optional<User> findByEmail(String username);
    public Long findIdByEmail(String email);

    Boolean existsByEmail(String email);

    //kol user 9adeh aandou min produit
    @Query("select count(u) from User u,Products ")
    public int countUserByProduct();
    //  ki t7ot product yaatik li aandhom product hedheka w lezim stock ykoun >0

    //sql query search for available product with users
    //@Query("select u from User u,Products p where u.userProducts.IdProducts=p.IdProducts")
    //public List<User> searchTopBuyers();
    //tchouf enehou l user li ammel akher orders
    //@Query("select u from User u,Orders o where u.orders.id=o.id")
    //public List<User> searchUserByOrders();
    //

}
