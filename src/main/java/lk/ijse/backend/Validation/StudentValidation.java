package lk.ijse.backend.Validation;

import lk.ijse.backend.DTO.StudentDTO;
import javafx.scene.control.*;

public class StudentValidation {

    public static boolean studentValidation(StudentDTO studentDTO){
        if (studentDTO.getName() == null || studentDTO.getName().matches("[A-Za-z]+")) {
            if (studentDTO.getCity() == null || studentDTO.getCity().matches("[A-Za-z]+")) {
                if (studentDTO.getEmail() == null || studentDTO.getEmail().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
                    if (studentDTO.getLevel() >= 0) {
                        return true;
                    }else{
                       throw new RuntimeException("Invalid Level");
                    }
                }else {
                    throw new RuntimeException("Invalid Email");
                }
            }else {
               throw new RuntimeException("Invalid City");
            }
        }else {
            throw new RuntimeException("Invalid Name");
        }
    }
}
