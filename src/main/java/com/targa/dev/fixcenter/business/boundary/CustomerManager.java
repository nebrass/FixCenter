package com.targa.dev.fixcenter.business.boundary;

import com.targa.dev.fixcenter.business.entity.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Nebrass Lamouchi <lnibrass at gmail.com>
 */
@Stateless
public class CustomerManager {

    @PersistenceContext
    EntityManager em;

    public Customer findById(long id) {
        return this.em.find(Customer.class, id);
    }

    public void delete(long id) {
        try {
            Customer reference = this.em.getReference(Customer.class, id);
            this.em.remove(reference);
        } catch (EntityNotFoundException e) {
            //we want to remove it...
        }
    }

    public List<Customer> all() {
        return this.em.createNamedQuery(Customer.findAll, Customer.class).
                getResultList();
    }

    public Customer save(Customer customer) {
        return this.em.merge(customer);
    }

}
