package lk.ijse.backend.Controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.backend.DTO.StudentDTO;
import lk.ijse.backend.Validation.StudentValidation;
import lk.ijse.backend.model.StudentModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import javafx.scene.control.*;


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
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String origin = req.getHeader("Origin");
        if (origin.contains("")){
            resp.setHeader("Access-Control-Allow-Origin",origin);
            resp.setHeader("Access-Control-Allow-Method","GET,POST,PUT,DELETE,HEADER");
            resp.setHeader("Access-Control-Allow-Headers","Content-Type");
            //resp.setHeader("Access-Control-Expose-");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("found");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getContentType()==null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
        Jsonb jsonb = JsonbBuilder.create();
        StudentDTO studentObj = jsonb.fromJson(req.getReader(), StudentDTO.class);
        //validation
        boolean b = StudentValidation.studentValidation(studentObj);
        if (b){
            try {
                //dbmangement
                int i = StudentModel.SaveStudent(studentObj, con);
                if (i !=1){
                    throw new RuntimeException("save failed");
                }else {
                    System.out.println("saved sucessfully");
                }
                resp.setStatus(HttpServletResponse.SC_CREATED);
                //the created json is sent to frontend
                resp.setContentType("application/json");
                jsonb.toJson(studentObj,resp.getWriter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getContentType()==null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
        Jsonb jsonb = JsonbBuilder.create();
        StudentDTO studentObj = jsonb.fromJson(req.getReader(), StudentDTO.class);
        //validation
        boolean b = StudentValidation.studentValidation(studentObj);
        if (b){
            try {
                //dbmangement
                int i = StudentModel.SaveStudent(studentObj, con);
                if (i !=1){
                    throw new RuntimeException("save failed");
                }else {
                    System.out.println("saved sucessfully");
                }
                resp.setStatus(HttpServletResponse.SC_CREATED);
                //the created json is sent to frontend
                resp.setContentType("application/json");
                jsonb.toJson(studentObj,resp.getWriter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
