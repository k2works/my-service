package my.service.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySampleBean {
    private int counter = 0;
    private int max = 10;

    @Autowired
    public MySampleBean(ApplicationArguments args) {
        List<String> files = args.getNonOptionArgs();
        if (files.size() == 0) return;
        try {
            max = Integer.parseInt(files.get(0));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public int count() {
        counter++;
        counter = counter > max ? 0 : counter;
        return counter;
    }
}
