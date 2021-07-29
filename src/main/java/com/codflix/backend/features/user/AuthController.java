package com.codflix.backend.features.user;

import com.codflix.backend.core.Conf;
import com.codflix.backend.core.Template;
import com.codflix.backend.models.User;
import com.codflix.backend.utils.URLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Session;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserDao userDao = new UserDao();

    public String login(Request request, Response response) {
        if (request.requestMethod().equals("GET")) {
            Map<String, Object> model = new HashMap<>();
            return Template.render("auth_login.html", model);
        }

        // Get parameters
        Map<String, String> query = URLUtils.decodeQuery(request.body());
        String email = query.get("email");
        String password = query.get("password");
        System.out.println(query.get("email") + query.get("password"));

        // Authenticate user
        User user = userDao.getUserByCredentials(email, password);
        if (user == null) {
            logger.info("User not found. Redirect to login");
            response.removeCookie("session");
            response.redirect("/login");
            return "KO";
        }

        // Create session
        Session session = request.session(true);
        session.attribute("user_id", user.getId());
        response.cookie("/", "user_id", "" + user.getId(), 3600, true);

        // Redirect to medias page
        response.redirect(Conf.ROUTE_LOGGED_ROOT);
        return "OK";
    }

    public String signUp(Request request, Response response) {
        if (request.requestMethod().equals("GET")) {
            Map<String, Object> model = new HashMap<>();
            return Template.render("auth_signup.html", model);
        }

        Map<String, String> query = URLUtils.decodeQuery(request.body());
        String email = query.get("email");
        String password = query.get("password");
        String passwordConfirm = query.get("password_confirm");

        // Authenticate user
        System.out.println(email);
        User user = userDao.getUserByEmail(email);
        if (user != null) {
            logger.info("User already exist. Redirect to login");
            response.removeCookie("session");
            response.redirect("/login");
            return "KO";
        }
        if(!password.equals(passwordConfirm)) {
            logger.info("Password must be iso confirm password. Redirect to login");
            response.removeCookie("session");
            response.redirect("/signup");
            return "KO";
        }

        user = userDao.register(email,password);
        if(user !=null) {
            // Create session
            Session session = request.session(true);
            session.attribute("user_id", user.getId());
            response.cookie("/", "user_id", "" + user.getId(), 3600, true);

            // Redirect to medias page
            response.redirect("/login");
            return "OK";
        }

        response.redirect("/signup");
        return "KO";
    }

    public String logout(Request request, Response response) {
        Session session = request.session(false);
        if (session != null) {
            session.invalidate();
        }
        response.removeCookie("session");
        response.removeCookie("JSESSIONID");
        response.redirect("/");

        return "";
    }
}
