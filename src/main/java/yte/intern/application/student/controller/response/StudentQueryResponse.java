package yte.intern.application.student.controller.response;

import lombok.Getter;
import yte.intern.application.student.entity.Student;

@Getter
public class StudentQueryResponse {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String tcKimlikNumber;
    private final String email;
    private final String studentNumber;

    public StudentQueryResponse(Student student){
        this.id=student.getId();
        this.firstName=student.getFirstName();
        this.lastName=student.getLastName();
        this.tcKimlikNumber=student.getTcKimlikNumber();
        this.email=student.getEmail();
        this.studentNumber=student.getStudentNumber();

    }

}
