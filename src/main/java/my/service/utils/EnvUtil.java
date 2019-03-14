package my.service.utils;

import org.springframework.web.servlet.ModelAndView;

public class EnvUtil {
    private static String ENV = "ENV";

    public static void setEnv(ModelAndView mav) {
        mav.addObject("env", getEnv());
    }

    public static String getEnv() {
        String env = System.getenv(ENV);
        if (env == null) return "development";
        return env;
    }
}
