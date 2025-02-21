package com.youjob.youjob.service.impl;

import com.youjob.youjob.domain.Enum.AnnonceStatus;
import com.youjob.youjob.domain.Enum.ProjectProgress;
import com.youjob.youjob.domain.Project;
import com.youjob.youjob.exception.project.ProjectInvalidException;
import com.youjob.youjob.exception.project.ProjectNotFoundException;
import com.youjob.youjob.repository.ProjectRepository;
import com.youjob.youjob.service.ProjectService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    public ProjectServiceImpl(ProjectRepository projectRepository){
        this.projectRepository=projectRepository;
    }
    @Override
    public Project Confirm(UUID id) {
        Optional<Project> project=projectRepository.findById(id);
        project.orElseThrow(()->new ProjectNotFoundException("project not found"));
        Project project1=project.get();
        project1.setProgress(ProjectProgress.IN_PROGRESS);
        project1.setConfirmedDate(LocalDateTime.now());
        project1.setAccepted(true);
        return projectRepository.save(project1);
    }

    @Override
    public void deleteProject(UUID uuid) {
        Optional<Project> project=projectRepository.findById(uuid);
        project.orElseThrow(()->new ProjectNotFoundException("project not found"));
        Project project1=project.get();
        projectRepository.delete(project1);
    }

    @Override
    public Project CompleteProject(UUID uuid) {
        Optional<Project> project=projectRepository.findById(uuid);
        project.orElseThrow(()->new ProjectNotFoundException("project not found"));
        Project project1=project.get();
        if(project1.getProgress()!=ProjectProgress.IN_PROGRESS) throw new ProjectInvalidException("Status should be in progress");
        project1.setDateComplete(LocalDateTime.now());
        project1.setProgress(ProjectProgress.COMPLETED);
        project1.getAnnonce().setStatus(AnnonceStatus.ARCHIVED);
        return projectRepository.save(project1);
    }

}
