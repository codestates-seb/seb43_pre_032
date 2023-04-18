package com.dudung.preproject.helper;

import com.dudung.preproject.tag.domain.Tag;

public interface TagControllerHelper extends ControllerHelper<Tag>{
    String TAG_DEFAULT_URL = "/tags";
    String TAG_RESOURCE_ID = "/{tag-id}";
    String TAG_RESOURCE_URI = TAG_DEFAULT_URL + TAG_RESOURCE_ID;
}
