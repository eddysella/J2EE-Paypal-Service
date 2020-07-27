package YABOY.Assignment.JSF;

import java.io.Serializable;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author George Parisis
 * @author 164645
 */
@Named
@RequestScoped
@RolesAllowed({"User", "Admin"})
public class LogoutBean implements Serializable {

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            //this method will disassociate the principal from the session (effectively logging him/her out)
            request.logout();
            context.addMessage(null, new FacesMessage("User is logged out"));
            return "logout";
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
        return null;
    }
}
