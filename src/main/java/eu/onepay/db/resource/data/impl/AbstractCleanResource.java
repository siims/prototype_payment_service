package eu.onepay.db.resource.data.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import eu.onepay.db.data.AbstractData;
import eu.onepay.db.resource.Resource;


@SuppressWarnings("unchecked")
@Transactional
public abstract class AbstractCleanResource implements Resource {

    @Autowired
    private SessionFactory cleanSessionFactory;

    public <T> T byId(Class<T> type, int id) {
        return (T) session().get(type, id);
    }

    public <T> List<T> list(Class<T> type) {
        return session().createCriteria(type).list();
    }

    public <T extends AbstractData> List<Integer> store(List<T> data) {
        return data.stream().map(this::store).collect(Collectors.toList());
    }

    public <T extends AbstractData> int store(T data) {
        return (int) session().save(data);
    }

    @Override
    public Session session() {
        return cleanSessionFactory.getCurrentSession();
    }

    protected Integer getResultRowIdCode(AbstractData queryResult) {
        return queryResult != null ? queryResult.getId() : null;
    }
}
