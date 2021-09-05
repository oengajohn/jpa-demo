package io.training.dao;


import io.training.model.UserAccount;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserAccountDao {
  @PersistenceContext(unitName = "trainingUnit" )
  private EntityManager em;

  public List<UserAccount> getAllUsers(){
    return em.createQuery("select ua from UserAccount ua",UserAccount.class)
        .getResultList();

  }
  public Optional<UserAccount> getUserAccountWithGivenId(Long userId){
    var users=  em.createQuery("select ua from UserAccount ua where ua.id=:userId",UserAccount.class)
          .setParameter("userId",userId)
          .getResultList();
    if(users.size()>0){
         return Optional.of(users.get(0));
    }
    return Optional.empty();

  }

  public UserAccount updateUserAccount(UserAccount userAccount){
    return em.merge(userAccount);
  }

  public  void createUserAccount(UserAccount userAccount){
    em.persist(userAccount);

  }
  public  void deleteUserAccountWithGivenuserId(Long userId){
   var userAccountOptional = getUserAccountWithGivenId(userId);

   userAccountOptional.ifPresent(userAccount -> {
     em.merge(userAccount);
     em.remove(userAccount);
   });

  }

}
