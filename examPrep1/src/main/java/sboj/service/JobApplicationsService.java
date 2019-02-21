package sboj.service;

import sboj.domain.models.service.JobApplicationServiceModel;

import java.util.List;

public interface JobApplicationsService {
    JobApplicationServiceModel createJobApplication(JobApplicationServiceModel jobApplicationServiceModel);

    JobApplicationServiceModel getJobApplicationById(String id);

    void delete(String id);

    List<JobApplicationServiceModel> getAllJobApplications();
}
