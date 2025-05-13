package cholog;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void save() {
        customerRepository.save(new Customer("Jack", "Bauer"));

        Iterable<Customer> customers = customerRepository.findAll();
        assertThat(customers).extracting(Customer::getFirstName).containsOnly("Jack");
    }

    @Test
    void findAll() {
        entityManager.persist(new Customer("Jack", "Bauer"));
        entityManager.persist(new Customer("Chloe", "O'Brian"));

        Iterable<Customer> customers = customerRepository.findAll();
        assertThat(customers).extracting(Customer::getFirstName).containsOnly("Jack", "Chloe");
    }

    @Test
    void findById() {
        Customer jack = new Customer("Jack", "Bauer");
        Customer chloe = new Customer("Chloe", "O'Brian");
        entityManager.persist(jack);
        entityManager.persist(chloe);

        Customer customer = customerRepository.findById(jack.getId()).orElseThrow(IllegalArgumentException::new);
        assertThat(customer.getFirstName()).isEqualTo(jack.getFirstName());
    }

    @Test
    void count() {
        entityManager.persist(new Customer("Jack", "Bauer"));
        entityManager.persist(new Customer("Chloe", "O'Brian"));

        long count = customerRepository.count();
        assertThat(count).isEqualTo(2);
    }

    @Test
    void delete() {
        Customer jack = new Customer("Jack", "Bauer");
        Customer chloe = new Customer("Chloe", "O'Brian");
        entityManager.persist(jack);
        entityManager.persist(chloe);

        customerRepository.deleteById(jack.getId());
        assertThat(customerRepository.count()).isEqualTo(1);
    }
}
