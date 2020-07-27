package YABOY.DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import YABOY.Assignment.Entities.Credentials;
import YABOY.Assignment.Entities.UserGroup;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author 164645
 */
@Stateless
public class UserGroupStorageServiceBean implements UserGroupStorageService {
    
    @PersistenceContext
    EntityManager em;

    @Resource
    SessionContext session;
    
    TypedQuery<Credentials> curUserQuery;
    Credentials user;
    
    public UserGroupStorageServiceBean(){}

    @Override
    public UserGroup find(Credentials user) {
       return em.find(UserGroup.class, user.getId());
    }

    @Override
    public void addGroupForUser(UserGroup sys_user_group) {
        em.persist(sys_user_group);
    }
}
