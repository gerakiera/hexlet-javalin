package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursesPage;

import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/courses", ctx -> {
            var courses = List.of(new Course("java","Курсы по программированию на java"),
                    new Course("php", "На этом курсе вы изучите основы языка PHP"),
                    new Course("HTML", "Язык разметки веб-страниц"));
            var header = "Курсы по программированию";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.start(7070);
    }
}