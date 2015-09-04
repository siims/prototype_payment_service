package eu.onepay.db.resource.data;

import java.util.List;

import eu.onepay.db.data.AbstractData;


public interface CRUDResource {

    public <T> T byId(Class<T> type, int id);

    public <T> List<T> list(Class<T> type);

    public <T extends AbstractData> int store(T data);

    public <T extends AbstractData> List<Integer> store(List<T> data);
}
