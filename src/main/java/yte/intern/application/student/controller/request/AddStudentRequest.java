package yte.intern.application.student.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import yte.intern.application.student.entity.Student;

@RequiredArgsConstructor
@Getter
@ToString
public class AddStudentRequest {

    private final String firstName;
    private final String lastName;
    private final String tcKimlikNumber;
    private final String email;
    private final String studentNumber;


    public Student toEntity(){
        return new Student(firstName,lastName,tcKimlikNumber,email,studentNumber);
    }
}
