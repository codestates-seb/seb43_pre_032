package com.dudung.preproject.utils;

import com.dudung.preproject.tag.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@RequiredArgsConstructor
public class CsvReader {
    @Bean
    public FlatFileItemReader<Tag> csvFileItemReader() {
        // file read
        FlatFileItemReader<Tag> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("/csv/tag.csv"));
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setEncoding("UTF-8");

        // read하는 데이터를 내부적으로 LineMapper을 통해 Mapping
        DefaultLineMapper<Tag> defaultLineMapper = new DefaultLineMapper<>();

        // delimitedLineTokenizer : setNames를 통해 각각의 데이터의 이름 설정
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
        delimitedLineTokenizer.setNames("id", "tagName", "tagDescription");
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        // beanWrapperFieldSetMapper : Tokenizer에서 가지고온 데이터들을 VO로 바인드하는 역할
        BeanWrapperFieldSetMapper<Tag> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Tag.class);

        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        // lineMapper 지정
        flatFileItemReader.setLineMapper(defaultLineMapper);

        return flatFileItemReader;
    }
}
