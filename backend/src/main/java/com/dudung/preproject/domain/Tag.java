package com.dudung.preproject.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

//  Todo 태그가 여러개인데 1:N 관계가 맞는가?
    @OneToMany(fetch = FetchType.LAZY)
    private List<Question> questions;


//  Todo 태그들을 하나씩 만들어서 넣어야하는것인가?
    private String java;
    private String javascript;
    private String python;

}
