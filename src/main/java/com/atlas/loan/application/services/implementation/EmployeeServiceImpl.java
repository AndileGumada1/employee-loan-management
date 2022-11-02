package com.atlas.loan.application.services.implementation;

import com.atlas.loan.application.controllers.dto.EmployeeRegisterRequest;
import com.atlas.loan.application.controllers.dto.LoginRequest;
import com.atlas.loan.application.exceptions.EmployeeAlreadyRegisteredException;
import com.atlas.loan.application.exceptions.EmployeeNotFoundException;
import com.atlas.loan.application.persistance.Department;
import com.atlas.loan.application.persistance.EmployeeRepository;
import com.atlas.loan.application.persistance.entity.Employee;
import com.atlas.loan.application.services.ConfirmationTokenService;
import com.atlas.loan.application.services.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
    public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ConfirmationTokenService tokenService;
    private final EmailService emailService;

    /**
     * Add method is used to create or post a new Employee object to the Database
     * @param request object to be sent or created
     * @return String object with a success message
     * */
    @Override
    public String addEmployee(EmployeeRegisterRequest request) {

        Employee employee = new Employee();
        employee.setEmployeeNumber(request.getEmployeeNumber());
        employee.setBranchName(request.getBranchName());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(Department.valueOf(request.getDepartment()));
        employee.setEmployeeNumber(request.getEmployeeNumber());
        employee.setPassword(encodePassword().encode(request.getPassword()));
        employee.setConfirmPassword(encodePassword().encode(request.getConfirmPassword()));
        Employee emp = employeeRepository.checkEmployee(employee.getEmail(), employee.getEmployeeNumber());
        if (emp != null) {
            throw new EmployeeAlreadyRegisteredException("Employee Already Registered: " + employee.getId());
        }
        employeeRepository.save(employee);
//        String token = UUID.randomUUID().toString();
//        //create a token and save the token
//        ConfirmationToken confirmationToken = new ConfirmationToken(
//              token,
//              LocalDateTime.now(),
//              LocalDateTime.now().plusMinutes(15),
//              employee
//        );
//        tokenService.saveConfirmationToken(confirmationToken);
//
//        //TODO SEND EMAIL
//        String link = "http://localhost:8084/api/v1/employees/confirm?token" + token;
//
//        emailService.send(employee.getEmail(),buildEmail(employee.getFirstName(),link));
        return "Employee successfully added";
    }

//    private Employee mapToEmployee(EmployeeRegisterRequest request) {
//        Employee employee = new Employee();
//        employee.setEmployeeNumber(request.getEmployeeNumber());
//        employee.setBranchName(request.getBranchName());
//        employee.setFirstName(request.getFirstName());
//        employee.setLastName(request.getLastName());
//        employee.setEmail(request.getEmail());
//        employee.setEmployeeNumber(request.getEmployeeNumber());
//        employee.setPassword(encodePassword().encode(request.getPassword()));
//        employee.setConfirmPassword(encodePassword().encode(request.getConfirmPassword()));
//    }

    /**
     * Method is for logging in a user with given email and password
     * @param request object to be passed
     * @return Integer represents the ID of the employee
     * */
    @Override
    public Integer doLogin(LoginRequest request) {
        Optional<Employee> employee = null;
        try {
            employee = employeeRepository.findByEmail(request.getEmail());
            log.info("Employee: " + employee + " Logged In Successfully");
            log.info("EmployeeID :"+employee.get().getId());
            return employee.get().getId();

        } catch (Exception e) {
            throw new EmployeeNotFoundException("Employee Not Found: " + employee);
        }
    }
    private String buildEmail(String firstName, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + firstName + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    /**
     * Method used to encode the password
     * @return PasswordEncoder
     * */
    @Bean
    public PasswordEncoder encodePassword() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }


    /**
     *Method used to update an employee object
     * @param employee represents the object to be passed
     * @return Employee updated employee object
     **/
    @Override
    public Employee updateEmployee(Employee employee) {

        Employee emp = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found: " + employee.getId()));
        emp.setEmployeeNumber(employee.getEmployeeNumber());
        emp.setBranchName(employee.getBranchName());
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setEmail(employee.getEmail());
        emp.setEmployeeNumber(employee.getEmployeeNumber());

        return employeeRepository.save(emp);

    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found: " + employeeId));
        log.info("Employee Found: " + employeeId);
        return employee;
    }
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return employeeRepository.findByEmail(email)
//                .orElseThrow( () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
//    }

//    public String verifyAccount(String token) {
//        ConfirmationToken confirmationToken = tokenService
//                .getToken(token)
//                .orElseThrow(() ->
//                        new IllegalStateException("token not found"));
//
//        if (confirmationToken.getConfirmedAt() != null) {
//            throw new IllegalStateException("email already confirmed");
//        }
//
//        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
//
//        if (expiredAt.isBefore(LocalDateTime.now())) {
//            throw new IllegalStateException("token expired");
//        }
//        tokenService.setConfirmedAt(token);
//       enableEmployee(
//                confirmationToken.getEmployee().getEmail());
//        return "Please Confirm Your Account";
//    }
//    public int enableEmployee(String email) {
//        return employeeRepository.enableEmployee(email);
//    }
}
