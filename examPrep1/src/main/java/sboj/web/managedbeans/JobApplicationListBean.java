package sboj.web.managedbeans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.service.JobApplicationServiceModel;
import sboj.service.JobApplicationsService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("jobApplicationListBean")
@RequestScoped
public class JobApplicationListBean extends BaseBean {
    private List<JobApplicationServiceModel> jobApplications;
    private JobApplicationsService jobApplicationsService;

    public JobApplicationListBean() {
    }

    @Inject
    public JobApplicationListBean(JobApplicationsService jobApplicationsService) {
        this.jobApplicationsService = jobApplicationsService;
    }

    @PostConstruct
    public void init() {
        this.setJobApplications(this.jobApplicationsService.getAllJobApplications());
        this.getJobApplications().forEach(x -> x.setSector(x.getSector().toLowerCase()));

    }

    public void setJobApplications(List<JobApplicationServiceModel> jobApplications) {
        this.jobApplications = jobApplications;
    }

    public List<JobApplicationServiceModel> getJobApplications(){
        return this.jobApplications;

    }
}
