package com.app.ipl.batch;

import com.app.ipl.model.Matches;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;

import javax.persistence.EntityManagerFactory;

/**
 * @Author saurabh vaish
 * @Date 04-07-2022
 */

@Log4j2
@NoArgsConstructor
public class MatchDataWriter extends JpaItemWriter<Matches> {

    public JpaItemWriter<Matches> getWriter(EntityManagerFactory entityManager){
        return new JpaItemWriterBuilder<Matches>().entityManagerFactory(entityManager).build();
    }

}
