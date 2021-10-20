package yte.intern.application.student.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yte.intern.application.student.entity.Book;

import java.time.LocalDate;


@Getter
@RequiredArgsConstructor
public class AddBookStudentRequest {

    private final String name;
    private final LocalDate publishDate;
    private final Integer pageCount;

    public Book toEntity(){
        return new Book(name,publishDate,pageCount);
    }





}
