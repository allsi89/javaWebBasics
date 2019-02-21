package sboj.web.managedbeans;

import sboj.domain.models.service.JobApplicationServiceModel;
import sboj.service.JobApplicationsService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("jobApplicationDetailsBean")
@RequestScoped
public class JobApplicationDetailsBean extends BaseBean {
    private JobApplicationsService jobApplicationsService;

    public JobApplicationDetailsBean() {
    }

    @Inject
    public JobApplicationDetailsBean(JobApplicationsService jobApplicationsService) {
        this.jobApplicationsService = jobApplicationsService;
    }

    public JobApplicationServiceModel getJobApplication(String id){
        return this.jobApplicationsService.getJobApplicationById(id);
    }


}
