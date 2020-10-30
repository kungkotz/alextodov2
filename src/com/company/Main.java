package com.company;

import express.Express;
import express.middleware.Middleware;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        Express app = new Express();
        Database db = new Database();

        app.get("/rest/tasks", (req, res) -> {
            List<Task> tasks = db.getTasks();

            res.json(tasks);
        });


        try {
            app.use(Middleware.statics(Paths.get("src/www").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        app.listen(3000);

    }
}
