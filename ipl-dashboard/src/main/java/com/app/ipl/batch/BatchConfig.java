package com.app.ipl.batch;

import com.app.ipl.model.Matches;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

/**
 * @Author saurabh vaish
 * @Date 03-07-2022
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public FlatFileItemReader<MatchInputData> matchDataReader(){
        return new MatchDataReader().getReader();
    }

    @Bean
    public MatchDataProcessor matchProcessor(){
        return new MatchDataProcessor();
    }

    @Bean
    public JpaItemWriter<Matches> matchDataWriter(){
        return new MatchDataWriter().getWriter(entityManagerFactory);
    }


    @Bean("step1")
    public Step step(JpaItemWriter<Matches> writer){
        return stepBuilderFactory.get("step1").<MatchInputData,Matches>chunk(10)
                .reader(matchDataReader())
                .processor(matchProcessor())
                .writer(matchDataWriter()).faultTolerant().skip(Exception.class).skipLimit(1)
                .build();
    }

    @Bean("job")
    public Job job(JobNotificationListener listener,Step step){
        return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end().build();
    }


}
