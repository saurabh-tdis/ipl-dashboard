package com.app.ipl.batch;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.core.io.ClassPathResource;

/**
 * @Author saurabh vaish
 * @Date 03-07-2022
 */

@NoArgsConstructor
@Log4j2
public class MatchDataReader extends FlatFileItemReader<MatchInputData> {

    private final String[] FIELD_NAMES = {"id","city","date","player_of_match","venue","neutral_venue","team1","team2","toss_winner","toss_decision","winner","result","result_margin","eliminator","method","umpire1","umpire2"};

    public FlatFileItemReader<MatchInputData> getReader(){
        return new FlatFileItemReaderBuilder<MatchInputData>()
                .name("csv file reader")
                .resource(new ClassPathResource("IPL Matches 2008-2020.csv"))
                .delimited()
                .names(FIELD_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInputData>(){{setTargetType(MatchInputData.class);}})
                .build();
    }

}
