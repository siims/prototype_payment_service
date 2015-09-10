package eu.onepay.db.resource.data;

import java.util.List;

import eu.onepay.db.data.AbstractData;


public interface CRUDResource {

    public <T> T getById(Class<T> type, long id);

    public <T> List<T> list(Class<T> type);

    public <T extends AbstractData> long store(T data);

    public <T extends AbstractData> List<Long> store(List<T> data);
}
