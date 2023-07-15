package lk.ijse.backend.webhandler.Controller;


import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.backend.webhandler.DTO.StudentDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@WebServlet(urlPatterns = "/student")
public class Student extends HttpServlet {
    private Connection con;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName(getServletContext().getInitParameter("mysql-driver"));
            con = DriverManager.getConnection(getServletContext().getInitParameter("mysql-url"),
                    getServletContext().getInitParameter("user-name"),
                    getServletContext().getInitParameter("password"));

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getContentType()==null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
        try{

        }
        Jsonb jsonb = JsonbBuilder.create();
        StudentDTO studentObj = jsonb.fromJson(req.getReader(), StudentDTO.class);
        //validation
        if (studentObj.getName() == null || studentObj.getName().matches("[A-Za-z]+")) {
            throw new RuntimeException("Invalid Name");
        } else if (studentObj.getCity() == null || studentObj.getCity().matches("[A-Za-z]+")) {
            throw new RuntimeException("Invalid City");
        } else if (studentObj.getEmail() == null || studentObj.getEmail().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            throw new RuntimeException("Invalid Email");
        } else if (studentObj.getLevel() <= 0) {
            throw new RuntimeException("Invalid Level");
        }

    }
}
