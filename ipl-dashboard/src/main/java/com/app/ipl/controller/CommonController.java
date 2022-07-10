package com.app.ipl.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author saurabh vaish
 * @Date 05-07-2022
 */

@RestController
@Log4j2
@RequestMapping("/api/")
public class CommonController {

    @Autowired
    private JobLauncher launcher;

    @Autowired
    private Job job;

    @CacheEvict(value = "ipl",allEntries = true)
    @Operation(hidden = true)
    @GetMapping("execute-batch")
    public String runBatch() throws Exception{

        JobParameters params=new JobParametersBuilder().addDate("sysDate", new Date()).toJobParameters();
        //run the job
        JobExecution execution=launcher.run(job, params);

        log.info("Job completion  status ::"+execution.getExitStatus());

        return execution.getExitStatus().toString();
    }
}
