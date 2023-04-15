package com.dudung.preproject.tag.mapper;

import com.dudung.preproject.tag.domain.Tag;
import com.dudung.preproject.tag.dto.TagDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    List<TagDto.ResponseForList> tagsToTagsResponse(List<Tag> tagList);
}
