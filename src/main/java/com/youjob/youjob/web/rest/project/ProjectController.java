package com.youjob.youjob.web.rest.project;

import com.youjob.youjob.domain.Consultation;
import com.youjob.youjob.domain.Project;
import com.youjob.youjob.service.ProjectService;
import com.youjob.youjob.web.vm.consultation.ResponseConsultationVM;
import com.youjob.youjob.web.vm.mapper.project.ProjectMapper;
import com.youjob.youjob.web.vm.project.ResponseProjectVM;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/project")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    public ProjectController(ProjectService projectService,ProjectMapper projectMapper){
        this.projectService=projectService;
        this.projectMapper=projectMapper;
    }
    @PostMapping("/confirm/{id}")
    public ResponseEntity<Map<String,Object>> confirmProject(@PathVariable UUID id){
        Project project=projectService.Confirm(id);
        ResponseProjectVM responseProjectVM=projectMapper.toResponseProjectVM(project);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Project confirmed and project in_progress");
        response.put("data", responseProjectVM);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable UUID id){
        projectService.deleteProject(id);
        return new ResponseEntity<>("Consulation deleted successfully", HttpStatus.OK);
    }
}
