package com.app.ipl.batch;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author saurabh vaish
 * @Date 04-07-2022
 */

@Log4j2
@Component
public class JobNotificationListener implements JobExecutionListener {

    @Autowired
    private AfterJobProcessor afterJobProcessor;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info(" Job Status ::"+jobExecution.getStatus());
        log.info("Job Start time ::"+jobExecution.getStartTime());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info(" Job Status ::"+jobExecution.getStatus());

        log.info("total match saved == "+this.afterJobProcessor.getTotalMatches());

        log.info("start saving team data ");

        this.afterJobProcessor.saveTeamRecords();

        log.info("ends saving team data ");

        log.info("total team records = "+this.afterJobProcessor.getTotalTeams());

        log.info("Job End time ::"+jobExecution.getEndTime());
        log.info("Job Exit Status ::"+jobExecution.getExitStatus());
    }

}
