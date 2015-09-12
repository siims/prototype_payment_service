package eu.onepay.db.resource.data.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import eu.onepay.db.data.AbstractData;
import eu.onepay.db.data.FinServiceImage;
import eu.onepay.db.resource.Resource;
import eu.onepay.db.resource.data.CRUDResource;


@SuppressWarnings("unchecked")
@Transactional
public abstract class AbstractResource implements Resource, CRUDResource {

    @Autowired
    private SessionFactory sessionFactory;

    public <T> T getById(Class<T> type, long id) {
        return (T) session().get(type, id);
    }

    public <T> List<T> list(Class<T> type) {
        return session().createCriteria(type).list();
    }

    public <T extends AbstractData> List<Long> store(List<T> data) {
        return data.stream().map(this::store).collect(Collectors.toList());
    }

    public <T extends AbstractData> long store(T data) {
        return (long) session().save(data);
    }

    @Override
    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    protected Long getResultRowIdCode(AbstractData queryResult) {
        return queryResult != null ? queryResult.getId() : null;
    }
}
