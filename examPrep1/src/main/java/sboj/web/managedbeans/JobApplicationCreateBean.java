package sboj.web.managedbeans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.binding.JobApplicationCreateBindingModel;
import sboj.domain.models.service.JobApplicationServiceModel;
import sboj.service.JobApplicationsService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("jobApplicationCreateBean")
@RequestScoped
public class JobApplicationCreateBean extends BaseBean {
    private JobApplicationCreateBindingModel jobApplication;
    private JobApplicationsService jobApplicationsService;
    private ModelMapper modelMapper;

    public JobApplicationCreateBean() {
    }

    @Inject
    public JobApplicationCreateBean(JobApplicationsService jobApplicationsService, ModelMapper modelMapper) {
        this.jobApplicationsService = jobApplicationsService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        this.jobApplication = new JobApplicationCreateBindingModel();
    }

    public void create(){
        this.jobApplicationsService.createJobApplication(
                this.modelMapper.map(this.jobApplication, JobApplicationServiceModel.class)
        );

        this.redirect("/home");
    }

    public JobApplicationCreateBindingModel getJobApplication() {
        return jobApplication;
    }

    public void setJobApplication(JobApplicationCreateBindingModel jobApplication) {
        this.jobApplication = jobApplication;
    }
}
