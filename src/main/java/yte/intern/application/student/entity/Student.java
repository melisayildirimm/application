package yte.intern.application.student.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import yte.intern.application.common.dto.MessageResponse;
import yte.intern.application.common.dto.MessageType;
import yte.intern.application.common.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Student extends BaseEntity {

    private String firstName;
    private String lastName;
    private String tcKimlikNumber;
    private String email;
    private String studentNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="student_id")
    private Set<Book> books = new HashSet<>();

    public Student(String firstName,
                   String lastName,
                   String tcKimlikNumber,
                   String email,
                   String studentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tcKimlikNumber = tcKimlikNumber;
        this.email = email;
        this.studentNumber = studentNumber;

        Book algorithmBook = new Book("Introduction to Algorithms", LocalDate.parse("1989-01-01"),1312);
        books.add(algorithmBook);
    }

    public MessageResponse canAddBook(Book toBeAddedBook){
        if(hasSameBook(toBeAddedBook)){
            return new MessageResponse("Student already has the book %s".formatted(toBeAddedBook.getName()), MessageType.ERROR);
        }
        return new MessageResponse("",MessageType.SUCCESS);
    }


    private boolean hasSameBook(Book toBeAddedBook){
        return books.stream()
                .anyMatch(book -> book.hasSameNameAs(toBeAddedBook));

    }

    public void addBook(Book toBeAddedBook){
        if(canAddBook(toBeAddedBook).hasError()){
            throw new IllegalArgumentException();
        }
        books.add(toBeAddedBook);

    }

    public void updateStudent(Student newStudent) {
        this.firstName = newStudent.firstName;
        this.lastName = newStudent.lastName;
        this.email = newStudent.email;
        this.tcKimlikNumber=newStudent.tcKimlikNumber;
        this.studentNumber = newStudent.studentNumber;
    }
}
