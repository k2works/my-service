package my.service.component;

import my.service.model.MyData;
import my.service.repository.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class MyDataBean {

    @Autowired
    MyDataRepository repository;

    public String getTableTagById(Long id) {
        Optional<MyData> opt = repository.findById(id);
        MyData data = opt.get();
        String result = "<tr><td>" + data.getMail()
                + "</td><td>" + data.getMail() +
                "</td></td>" + data.getAge() +
                "</td><td>" + data.getMemo() +
                "</td></tr>";
        return result;
    }
}
