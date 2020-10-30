package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import express.utils.Utils;

import java.sql.*;
import java.util.List;

public class Database {

    private Connection conn;

    public Database() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:todolist.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Task> getTasks() {
        List<Task> tasks = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todos");
            ResultSet rs = stmt.executeQuery();

            Task[] tasksFromRS = (Task[]) Utils.readResultSetToObject(rs, Task[].class);

            tasks = List.of(tasksFromRS);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
