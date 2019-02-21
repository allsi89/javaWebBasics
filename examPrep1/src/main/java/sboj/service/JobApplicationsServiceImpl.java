package sboj.service;

import org.modelmapper.ModelMapper;
import sboj.domain.entities.JobApplication;
import sboj.domain.models.service.JobApplicationServiceModel;
import sboj.repository.JobApplicationRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobApplicationsServiceImpl implements JobApplicationsService {
    private final JobApplicationRepository jobApplicationRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobApplicationsServiceImpl(JobApplicationRepository jobApplicationRepository, ModelMapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public JobApplicationServiceModel createJobApplication(JobApplicationServiceModel jobApplicationServiceModel) {
        return this.modelMapper.map(
                this.jobApplicationRepository
                        .save(this.modelMapper.map(jobApplicationServiceModel, JobApplication.class)),
                JobApplicationServiceModel.class);
    }

    @Override
    public JobApplicationServiceModel getJobApplicationById(String id) {
        return this.modelMapper
                .map(
                        this.jobApplicationRepository.findById(id),
                        JobApplicationServiceModel.class);
    }

    @Override
    public void delete(String id) {
        this.jobApplicationRepository.delete(id);
    }

    @Override
    public List<JobApplicationServiceModel> getAllJobApplications() {
        return this.jobApplicationRepository
                .findAll()
                .stream()
                .map(x -> this.modelMapper.map(x, JobApplicationServiceModel.class))
                .collect(Collectors.toList());
    }
}
