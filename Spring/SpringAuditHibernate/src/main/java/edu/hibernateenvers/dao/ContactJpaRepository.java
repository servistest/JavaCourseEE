package edu.hibernateenvers.dao;

import edu.hibernateenvers.model.ContactAudit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Admin on 23.09.2016.
 */
public interface ContactJpaRepository extends JpaRepository<ContactAudit,Long> {

    @Query("select c from ContactAudit c where c.firstName = ?1")
    List<ContactAudit> findByMyName(String firstName);

    @Query("select c from ContactAudit c where c.firstName like concat(?1,'%')")
    List<ContactAudit> findByMyNameLike(String firstNameLike);

    @Query(value = "select c from ContactAudit c where c.lastName =?1 "
//            countQuery = "select c from ContactAudit c where c.lastName=?1"
    )
    Page<ContactAudit> findByLastName(String lastName, Pageable pageable);


    @Query(value = "select c from ContactAudit c where c.lastName like concat('%',?1,'%')  ")
    List<ContactAudit> findByNameAndSort(String firstNameLike, Sort sort);

    @Query(value = "select c from ContactAudit c where c.lastName=:lastName or c.firstName=:firstName")
    List<ContactAudit> findByFirstNameOrLastName(@Param("firstName") String firstName,
                                                 @Param("lastName") String lastName
    );

}
