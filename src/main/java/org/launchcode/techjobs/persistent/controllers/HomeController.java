package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "MyJobs");
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors,
                                    Model model,
                                    @RequestParam int employerId,
                                    @RequestParam List<Integer> skills) {

        //some things don't belong here
        //give value to an employer object, skill list object, and save
        //1. Check for error handling
        //2. Set the employer object to the job using the employerId.
        //3.set the skills to the job
        //4. Save job (edited)

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            return "add";
        }

        //query database for employer only if no validation errors
        Optional<Employer> result = employerRepository.findById(employerId);
        if (result.isEmpty()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("errorMsg", "Invalid employer selected.");
            return "add";
        }
        // find and set the employer
        Employer employer = result.get();
        newJob.setEmployer(employer);

        //query database for skills only if no validation errors
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        if (skillObjs.isEmpty()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            model.addAttribute("errorMsg", "No valid skills selected.");
            return "add";
        }
        newJob.setSkills(skillObjs);

        // save the new job
        jobRepository.save(newJob);

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        //handle the job object
        Optional<Job> jobResult = jobRepository.findById(jobId);

        if (jobResult.isEmpty()) {
            model.addAttribute("errorMsg", "Job not found. Please check the job ID.");
            return "error";
        }

        //add job to the model and display the view page
        Job job = jobResult.get();
        model.addAttribute("job", job);

        return "view";
    }

}
