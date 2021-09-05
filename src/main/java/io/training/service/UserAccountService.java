package io.training.service;

import io.training.dao.TodoDao;
import io.training.dao.UserAccountDao;
import io.training.model.Address;
import io.training.model.Company;
import io.training.model.Geo;
import io.training.model.Todo;
import io.training.model.UserAccount;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.xml.registry.infomodel.User;
import org.jboss.logging.Logger;

@Singleton
@Startup
public class UserAccountService {
  private Logger logger = Logger.getLogger(UserAccountService.class);
  @Inject UserAccountDao accountDao;
  @Inject
  TodoDao todoDao;

  @PostConstruct
  public void setup() {
    UserAccount user = createUser1();

    accountDao.createUserAccount(user);
    logger.info("Creating user 1");
    accountDao.getAllUsers().forEach(userAccount -> logger.info(userAccount));
    logger.info("Creating user 2");
    var user2 =createUser2();
    accountDao.createUserAccount(user2);
    accountDao.getAllUsers().forEach(userAccount -> logger.info(userAccount));
    logger.info("Updating user 1");
    user.setWebsite("io.training.com");
    var updateUserAccount = accountDao.updateUserAccount(user);
    logger.info("Updated website:"+updateUserAccount.getWebsite());
    logger.info("Querying use:2");
    var userAccountOptional = accountDao.getUserAccountWithGivenId(10L);
    userAccountOptional.ifPresentOrElse(
        queriedUser -> logger.info(queriedUser),
        ()->logger.error("User does not exists")
    );
    accountDao.deleteUserAccountWithGivenuserId(user2.getUserId());
    accountDao.getAllUsers().forEach(userAccount -> logger.info(userAccount));
    logger.info("======================TODO=============");
    var todo1 = new Todo();
    todo1.setCompleted(true);
    todo1.setUserAccount(user);
    todo1.setTitle("Connecting to database via JDBC");
    todoDao.createTodo(todo1);
    todoDao.getAllTodos().forEach(todo -> logger.info(todo));




  }

  private UserAccount createUser1() {
    var user = new UserAccount();

    user.setName("Kevo");
    user.setEmail("kevin@kimathi.com");
    user.setPhone("07123469874");
    user.setWebsite("kevin.kimathi.com");
    // Geo
    var geo = new Geo();
    geo.setLat("-37.3159");
    geo.setLng("81.1496");

    //Address
    var  address = new Address();
    address.setStreet("jogoo");
    address.setCity("nairobi");
    address.setSuite("Jogoo");
    address.setZipcode("56641165");
    address.setGeo(geo);
    user.setAddress(address);
    //Company
    var company = new Company();
    company.setName("Romaguera-Crona");
    company.setCatchPhrase("Multi-layered client-server neural-net");
    company.setBs("harness real-time e-markets");
    user.setCompany(company);
    return user;
  }
  private UserAccount createUser2() {
    var user = new UserAccount();

    user.setName("Rose Ann");
    user.setEmail("rose@ann.com");
    user.setPhone("071234678874");
    user.setWebsite("rose.ann.com");
    // Geo
    var geo = new Geo();
    geo.setLat("-37.3159");
    geo.setLng("81.1496");

    //Address
    var  address = new Address();
    address.setStreet("jogoo");
    address.setCity("nairobi");
    address.setSuite("Jogoo");
    address.setZipcode("56641165");
    address.setGeo(geo);
    user.setAddress(address);
    //Company
    var company = new Company();
    company.setName("Rosann and Sons and Daughters ltd");
    company.setCatchPhrase("Proactive didactic contingency");
    company.setBs("synergize scalable supply-chains");
    user.setCompany(company);
    return user;
  }
}
