package com.example.Schools.Controller;

import com.example.Schools.Domain.School;
import com.example.Schools.Response;
import com.example.Schools.Service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

@Controller
@RequiredArgsConstructor


    public class SchoolController {
    private final SchoolService service;

    @GetMapping("/index")
    public String add (Model model) {
        return "new";
    }

    @ResponseBody
    @PostMapping("/schoolList")
    public Response listAllSchool(){
        String message =null;
        boolean success = false;
        List<School> schoolList = new ArrayList<>();
        try{
            schoolList = service.listAll();
            success = true;
            message = "Successfully fetched School Data";

        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(schoolList,  message , success);
    }

    @ResponseBody
    @PostMapping("/edit")
    public  Response editSchool(
            @RequestParam Long id
    ){
        String message = null;
        boolean success= true;
        School school = new School();
        try{
            school = service.get(id);
            success = true;
            message =  "Successfully fetch school by Id";
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new Response(school, message, success);
    }

    @ResponseBody
    @PostMapping("/add")
    public Response saveOrUpdateSchool (
            @ModelAttribute("school") School sl
    ){
        School school = null;
        String message = "";
        boolean success =false;
        try {
            if(sl.getId() != null) {
                school = service.get(sl.getId());
                school. setSchoolId(sl.getSchoolId());
                school.setSchoolName(sl.getSchoolName());
                school.setSchoolLevel(sl.getSchoolLevel());
                school.setAddress(sl.getAddress());
                school.setCity(sl.getCity());
                school.setPhone(sl.getPhone());
            } else {
                school = new School();
                school.setId(sl.getId());
                school.setSchoolId(sl.getSchoolId());
                school.setSchoolName(sl.getSchoolName());
                school.setSchoolLevel(sl.getSchoolLevel());
                school.setAddress(sl.getAddress());
                school.setCity(sl.getCity());
                school.setPhone(sl.getPhone());
            }
          school= service.save(school);
           message ="Successfully Save School";
           success = true;
        }catch(Exception e){
            e.printStackTrace();
        }
    return new Response(school, message, success );
    }

    @ResponseBody
    @PostMapping("/delete")
    public Response deleteSchool(
            @RequestParam Long id
   ){
        String message = null;
        boolean success = false;
        try{
            service.delete(id);
            success = true;
            message = "Successfully Deleted";
        }catch (Exception e){
            e.printStackTrace();
        }
    return new Response(null, message, success);
    }
}
