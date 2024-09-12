package com.property.report.repository.custom;

import com.property.report.common.dto.ListResultRdo;
import com.property.report.model.Country;
import com.property.report.model.Property;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;


public class PropertyCustomRepositoryImpl implements PropertyCustomRepository{

    @Autowired
    EntityManager entityManager;

    @Override
    public ListResultRdo<Integer> getPropertyByCountryAndEmail(Integer pageNo, Integer pageSize, Country country) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);

        Root<Property> propertyRoot = cq.from(Property.class);

        Predicate countryPredicate = cb.equal(propertyRoot.get("country"), country);

        Predicate emailPredicate = cb.isNotNull(propertyRoot.get("email"));

        Predicate isDeletedNullCheck = cb.isNull(propertyRoot.get("isDeleted"));

        Predicate googleParserPredicate = cb.isNull(propertyRoot.get("googleParser"));

        Predicate isDeletedCheck = cb.equal(propertyRoot.get("isDeleted"),false);

        Predicate isDeletedPredicate= cb.or(isDeletedCheck,isDeletedNullCheck);

        Predicate forTestingNullCheck = cb.isNull(propertyRoot.get("forTesting"));

        Predicate forTestingCheck = cb.equal(propertyRoot.get("forTesting"),false);

        Predicate forTestingPredicate= cb.or(forTestingNullCheck,forTestingCheck);

        Predicate predicate=cb.and(countryPredicate,emailPredicate,forTestingPredicate,isDeletedPredicate,googleParserPredicate);
        cq.where(predicate);

        cq.select(propertyRoot.get("id"));

        cq.orderBy(cb.desc(propertyRoot.get("id")));

        if(pageNo==null || pageSize==null) {
            pageNo = 0;
            pageSize = 1000;
        }

        TypedQuery<Integer> query = entityManager.createQuery(cq).setFirstResult(pageNo*pageSize).setMaxResults(pageSize);
        return new ListResultRdo<Integer>().getListResultRdo( query.getResultList(), pageNo, pageSize,
                entityManager.createQuery(cq).getResultList().size());

    }

    @Override
    public ListResultRdo<Integer> getPropertyByCountryAndGoogle(Integer pageNo, Integer pageSize,Country country) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);

        Root<Property> propertyRoot = cq.from(Property.class);

        Predicate countryPredicate = cb.equal(propertyRoot.get("country"), country);

        Predicate googlePredicate = cb.isNull(propertyRoot.get("googleAddress"));

        Predicate googleParserPredicate = cb.isNull(propertyRoot.get("googleParser"));

        Predicate isDeletedNullCheck = cb.isNull(propertyRoot.get("isDeleted"));

        Predicate isDeletedCheck = cb.equal(propertyRoot.get("isDeleted"),false);

        Predicate isDeletedPredicate= cb.or(isDeletedCheck,isDeletedNullCheck);

        Predicate forTestingNullCheck = cb.isNull(propertyRoot.get("forTesting"));

        Predicate forTestingCheck = cb.equal(propertyRoot.get("forTesting"),false);

        Predicate forTestingPredicate= cb.or(forTestingNullCheck,forTestingCheck);

        Predicate predicate=cb.and(countryPredicate,googlePredicate,forTestingPredicate,isDeletedPredicate,googleParserPredicate);

        cq.where(predicate);

        cq.select(propertyRoot.get("id"));

        cq.orderBy(cb.desc(propertyRoot.get("id")));

        if(pageNo==null || pageSize==null) {
            pageNo = 0;
            pageSize = 1000;
        }

        TypedQuery<Integer> query = entityManager.createQuery(cq).setFirstResult(pageNo*pageSize).setMaxResults(pageSize);
        return new ListResultRdo<Integer>().getListResultRdo( query.getResultList(), pageNo, pageSize,
                entityManager.createQuery(cq).getResultList().size());

    }

    @Override
    public ListResultRdo<Integer> getPropertyForGoogleParser(Integer pageNo, Integer pageSize, Country country) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);

        Root<Property> propertyRoot = cq.from(Property.class);

        Predicate countryPredicate = cb.equal(propertyRoot.get("country"), country);

        Predicate sentAtNull = cb.isNull(propertyRoot.get("sentAt"));

        Predicate freeGoogleBookingLinksPredicate = cb.equal(propertyRoot.get("freeGoogleBookingLinks"),false);

        Predicate isDeletedNullCheck = cb.isNull(propertyRoot.get("isDeleted"));

        Predicate isDeletedCheck = cb.equal(propertyRoot.get("isDeleted"),false);

        Predicate isDeletedPredicate= cb.or(isDeletedCheck,isDeletedNullCheck);

        Predicate forTestingNullCheck = cb.isNull(propertyRoot.get("forTesting"));

        Predicate forTestingCheck = cb.equal(propertyRoot.get("forTesting"),false);

        Predicate forTestingPredicate= cb.or(forTestingNullCheck,forTestingCheck);

        Predicate predicate=cb.and(countryPredicate,forTestingPredicate,isDeletedPredicate,freeGoogleBookingLinksPredicate,sentAtNull);
        cq.where(predicate);

        cq.select(propertyRoot.get("id"));

        cq.orderBy(cb.desc(propertyRoot.get("id")));

        if(pageNo==null || pageSize==null) {
            pageNo = 0;
            pageSize = 100;
        }

        TypedQuery<Integer> query = entityManager.createQuery(cq).setFirstResult(pageNo*pageSize).setMaxResults(pageSize);
        return new ListResultRdo<Integer>().getListResultRdo( query.getResultList(), pageNo, pageSize,
                entityManager.createQuery(cq).getResultList().size());

    }

}
