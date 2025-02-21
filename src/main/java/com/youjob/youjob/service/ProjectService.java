package com.youjob.youjob.service;

import com.youjob.youjob.domain.Project;

import java.util.UUID;

public interface ProjectService {
    Project Confirm(UUID id);
    void deleteProject(UUID uuid);
    Project CompleteProject(UUID uuid);
}
