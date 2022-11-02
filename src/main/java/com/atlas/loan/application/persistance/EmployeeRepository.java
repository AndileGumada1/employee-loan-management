package com.atlas.loan.application.persistance;

import com.atlas.loan.application.persistance.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("select e from Employee e where e.email=?1 or e.employeeNumber=?2")
    Employee checkEmployee(String email, String employeeNumber);

    /**
     * @param email
     * @param password
     * @return
     */
//    @Query("select e.id from Employee e where e.email=?1 and e.password=?2")
    Integer findIdByEmailAndPassword(String email, String password);

    Optional<Employee> findByEmail(String email);
//    @Transactional
//    @Modifying
//    @Query("UPDATE Employee e " +
//            "SET e.enabled = TRUE WHERE e.email = ?1")
//    int enableEmployee(String email);
}
