package my.service.model;

import java.util.List;

public interface MsgDataDao<T> {

    public List<MsgData> getAll();
    public MsgData findById(long id);
}
