package com.jre.hireout;

import com.jre.hireout.database.entities.companies.Company;
import com.jre.hireout.database.entities.jobs.Job;
import com.jre.hireout.database.entities.users.Role;
import com.jre.hireout.database.entities.users.User;
import com.jre.hireout.services.companies.CompanyService;
import com.jre.hireout.services.jobs.JobService;
import com.jre.hireout.services.users.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
@Slf4j
public class HireOutApplication {

    public static void main(String[] args) {
        SpringApplication.run(HireOutApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    // TODO: Move this to its own class once fully implemented!
    @Bean
    CommandLineRunner initialiseUserTable(UserService userService) {
        return args -> {
            if(userService.getUsers().isEmpty()) {
                if(userService.getRoles().isEmpty()) {
                    userService.saveRole(new Role(null, "ROLE_ADMIN"));
                    userService.saveRole(new Role(null, "ROLE_USER"));
                }

                userService.saveUser(new User(
                        null,
                        "Jamie",
                        "Edwards",
                        "jamie",
                        "password1",
                        new HashSet<>(),
                        new ArrayList<>()
                ));
                userService.addRoleToUser("jamie", "ROLE_ADMIN");
                userService.addRoleToUser("jamie", "ROLE_USER");
            }
        };
    }

    // TODO: Move this into its own class once fully implemented!
    @Bean
    CommandLineRunner initialiseJobTable(JobService jobService, UserService userService) {
        log.info("Creating new job and adding to database.");
        return args -> {
            if(jobService.getJobs().isEmpty()) {
                jobService.saveJob(new Job(
                        null,
                        userService.getUser("jamie"),
                        "First Job",
                        LocalDate.now(),
                        LocalTime.of(12, 0),
                        true,
                        LocalDate.now(),
                        LocalTime.of(12, 0),
                        LocalDate.of(2022, 8, 29),
                        LocalTime.of(12, 30),
                        LocalDate.of(2022, 8, 29),
                        LocalTime.of(12, 30),
                        1,
                        24,
                        "This is a note worth taking",
                        "PO/232-458-JRE",
                        12.5f,
                        true,
                        true
                ));
            }
        };
    }

    // TODO: Move this into its own class once fully implemented!
    @Bean
    CommandLineRunner initialiseCompanyTable(CompanyService companyService) {
        log.info("Creating new Companies and adding to database.");
        return args -> {
            if(companyService.getCompanies().isEmpty()) {
                companyService.saveCompany(new Company(
                        null,
                        "The Celtic Manor",
                        "Coldra Wood, The Usk Valley, Chepstow Road, Newport",
                        "NP18 1HQ",
                        "01633 413000",
                        "",
                        "GB 779475461",
                        "Interesting note here",
                        "http://www.celtic-manor.com/",
                        new ArrayList<>()
                ));
            }
        };
    }
}