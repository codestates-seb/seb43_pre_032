package com.dudung.preproject.utils;

import com.dudung.preproject.tag.domain.Tag;
import com.dudung.preproject.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CsvWriter implements ItemWriter<Tag> {
    private final TagRepository tagRepository;

    @Override
    public void write(List<? extends Tag> list) throws Exception {
        tagRepository.saveAll(new ArrayList<Tag>(list));
    }
}
