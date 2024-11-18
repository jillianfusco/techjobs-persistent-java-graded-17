package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired
    //AutoWired injects EmployerRepository into the controller
    //& enables Spring to automatically manage the dependency lifecycle ensuring EmployerRepository instance is correctly initialized
    private EmployerRepository employerRepository;


    @GetMapping("/")
    //fetches employer data and connects it to view
    public String index(Model model) {
        model.addAttribute("employers", employerRepository.findAll());
        //findAll() method provided by CrudRepository interface
        return "employers/index";
    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {
        //@Valid triggers validation annotations @NotBlank and @Size on Employer Object. Validation errors are handled by binding to Model for user feedback

        if (errors.hasErrors()) {
            return "employers/add";
        }

        employerRepository.save(newEmployer); // saves the valid Employer object to the database
        return "redirect:/employers";
    }

    //lives at /employers/view/#
    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {
        //employerId is passes as a path variable and retrieved Employer is added to model to populate view
        Optional<Employer> optEmployer = employerRepository.findById(employerId); //fetch the employer by ID
        //findById() returns an optional to handle cases where requested Employer may not exist, preventing a NullPointerException

        if (optEmployer.isPresent()) { //isPresent() safely checks if object exists before accessing
            Employer employer = optEmployer.get(); //get the Employer object if present
            employer.getJobs().size();
            model.addAttribute("employer", employer); //add the employer to the model
            return "employers/view"; // render the employers/view template
        } else {
            return "redirect:";

        }
    }
}
