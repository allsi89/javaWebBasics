package sboj.web.managedbeans;

import sboj.domain.models.service.JobApplicationServiceModel;
import sboj.service.JobApplicationsService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named("jobApplicationDeleteBean")
@RequestScoped
public class JobApplicationDeleteBean extends BaseBean {
    private JobApplicationsService jobApplicationsService;

    public JobApplicationDeleteBean() {
    }

    @Inject
    public JobApplicationDeleteBean(JobApplicationsService jobApplicationsService) {
        this.jobApplicationsService = jobApplicationsService;
    }

    public void delete() {
        String id = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("id");
        this.jobApplicationsService.delete(id);
        this.redirect("/home");
    }

    public JobApplicationServiceModel getJobApplication(String id){
        return this.jobApplicationsService.getJobApplicationById(id);
    }

}
