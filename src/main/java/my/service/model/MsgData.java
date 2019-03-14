package my.service.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "msgdata")
public class MsgData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private long id;

    @Column
    private String title;

    @Column(nullable = false)
    @NotEmpty
    private String message;

    @ManyToOne
    private MyData mydata;

    public MsgData() {
        super();
        mydata = new MyData();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MyData getMyData() {
        return mydata;
    }

    public void setMyData(MyData mydata) {
        this.mydata = mydata;
    }
}
