package services;

import dataaccess.DBUtil;
import dataaccess.UserDB;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import models.User;

public class AccountService {
    
    public User login(String email, String password, String path) {
        
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            
            if (password.equals(user.getPassword())) {
                // Log activity
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);                
		// Send E-mail		
                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                
                GmailService.sendMail(to, subject, template, tags);
                return user;
            }
        } catch (Exception e) {
        }       
        return null;
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
    
    public void resetPassword(String email, String path, String url){
        String uuid = UUID.randomUUID().toString();
        String link = url + "?uuid=" + uuid;
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);            
				// Log activity
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Password reset request by {0}", email);
               
				// Send E-mail
				
                String to = user.getEmail();
                String subject = "Notes App Password Reset";
                String template = path + "/emailtemplates/resetpassword.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("username", user.getEmail());
                tags.put("link", link);
                
                GmailService.sendMail(to, subject, template, tags);
                
                user.setResetpasswordUUID(uuid);
                userDB.update(user);        
        } catch (Exception e) {
        }
    }
    
    public boolean changePassword(String uuid, String password) throws Exception{
        UserDB us = new UserDB();
        
        try{
            User user = us.getUserByUUID(uuid);
            user.setPassword(password);
            user.setResetpasswordUUID(null);
            us.update(user);
            return true;
        } catch(Exception ex){
            return false;
        }
    }
}
