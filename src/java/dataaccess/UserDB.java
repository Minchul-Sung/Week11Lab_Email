package dataaccess;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.User;

public class UserDB {
    public User get(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }
    
    public void update(User user){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        
        try {
            em.merge(user);
            trans.commit();           
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();           
        }
    }
    
    public User getUserByUUID(String uuid) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            User user = em.createNamedQuery("User.findByResetpasswordUUID", User.class).setParameter("resetpasswordUUID", uuid).getSingleResult();
            return user;
        } catch(Exception ex){
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
        } finally{
            em.close();
        }
        return null;
    }
}
