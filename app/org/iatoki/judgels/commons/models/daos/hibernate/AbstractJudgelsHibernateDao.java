package org.iatoki.judgels.commons.models.daos.hibernate;

import org.iatoki.judgels.commons.Page;
import org.iatoki.judgels.commons.InvalidPageNumberException;
import org.iatoki.judgels.commons.models.daos.interfaces.JudgelsDao;
import org.iatoki.judgels.commons.models.domains.AbstractJudgelsModel;
import org.iatoki.judgels.commons.models.metas.MetaAbstractJudgelsModel;
import play.db.jpa.JPA;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractJudgelsHibernateDao<E extends AbstractJudgelsModel> extends AbstractHibernateDao<Long, E> implements JudgelsDao<E> {

    @Override
    public void persist(E entity, String user, String ipAddress) {
        entity.jid = UUID.randomUUID().toString();

        super.persist(entity, user, ipAddress);
    }

    @Override
    public E findByJid(String jid) {
        CriteriaBuilder cb = JPA.em().getCriteriaBuilder();
        CriteriaQuery<E> query = cb.createQuery(getEntityClass());

        Root<E> root = query.from(getEntityClass());

        query = query.where(cb.equal(root.get(MetaAbstractJudgelsModel.jid), jid));

        return JPA.em().createQuery(query).getSingleResult();
    }

    @Override
    public Page<List<String>> pageString(long page, long pageSize, String sortBy, String order, String filterString, List<Field> filters) throws InvalidPageNumberException {
        CriteriaBuilder cb = JPA.em().getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<E> rootCount = countQuery.from(getEntityClass());

        List<Predicate> predicates = new ArrayList<>();

        for (Field filter : filters) {
            if (String.class.equals(filter.getType())) {
                predicates.add(cb.like(rootCount.get(filter.getName()), "%" + filterString + "%"));
            }
        }

        Predicate condition = cb.or(predicates.toArray(new Predicate[predicates.size()]));

        countQuery.select(cb.count(rootCount)).where(condition);
        Long count = JPA.em().createQuery(countQuery).getSingleResult();

        if ((page < 0) || (page * pageSize > count)) {
            throw new InvalidPageNumberException("Page number is invalid");
        } else {
            CriteriaQuery<E> query = cb.createQuery(getEntityClass());
            Root<E> root = query.from(getEntityClass());

            List<Selection<?>> selection = new ArrayList<>();
            predicates.clear();

            for (Field filter : filters) {
                selection.add(root.get(filter.getName()));
                if (String.class.equals(filter.getType())) {
                    predicates.add(cb.like(root.get(filter.getName()), "%" + filterString + "%"));
                }
            }

            condition = cb.or(predicates.toArray(new Predicate[predicates.size()]));

            Order orderBy = null;
            if ("asc".equals(order)) {
                orderBy = cb.asc(root.get(sortBy));
            } else {
                orderBy = cb.desc(root.get(sortBy));
            }

            query
                .multiselect(selection)
                .where(condition)
                .orderBy(orderBy);

            List<E> list = JPA.em().createQuery(query).setFirstResult((int) (page * pageSize)).setMaxResults((int) pageSize).getResultList();

            List<List<String>> listData = new ArrayList<>();
            for (E entity : list) {
                List<String> data = new ArrayList<>();
                for (Field filter : filters) {
                    try {
                        data.add(filter.get(entity).toString());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                listData.add(data);
            }

            return new Page<List<String>>(listData, count, page, pageSize);
        }
    }

}
