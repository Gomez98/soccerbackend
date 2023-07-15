package com.goan.football.services.impl;

import com.goan.football.models.*;
import com.goan.football.repositories.DueRepository;
import com.goan.football.repositories.RegistrationRepository;
import com.goan.football.repositories.StudentRepository;
import com.goan.football.repositories.WorkshopRepository;
import com.goan.football.services.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.goan.football.utils.ModelUtils.prepare;


@Service
@AllArgsConstructor
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final WorkshopRepository workshopRepository;
    private final StudentRepository studentRepository;
    private final DueRepository dueRepository;

    @Override
    public Registration save(Registration entity) {
        prepare(entity);
        LocalDate currentDate = LocalDate.now();

        Workshop workshop = workshopRepository.findByName(entity.getWorkshopName()).orElse(null);

        Due due = new Due();
        due.setName("Cuota 1");
        due.setAmount(workshop.getPrice());
        due.setCreationDate(currentDate);
        due.setDueDate(currentDate.plusDays(7));
        due.setStatus(DueStatusEnum.PENDING.name());
        due.setStudentId(entity.getStudent().getId());
        due.setRegistrationId(entity.getId());
        due.setWorkshopName(workshop.getName());
        dueRepository.save(due);


        return registrationRepository.save(entity);
    }

    @Override
    public Registration update(Registration entity) {
        return null;
    }

    @Override
    public Registration get(String id) {
        return null;
    }

    @Override
    public List<Registration> all(Search search) {
        List<Registration> registrations = registrationRepository.findAll();
        registrations = registrations.stream().filter(s -> !s.getDeleted()).collect(Collectors.toList());
        return registrations;
    }

    @Override
    public List<Registration> all(String id) {
        return null;
    }

    @Scheduled(cron = "0 0 2 * * *")
    private void generateDueForStudents() {
        log.info("Generating dues for students");
        List<Student> students = studentRepository.findAllByActive();
        for (Student student : students) {
            log.info("Processing registrations for student: {}", student.getFullName());
            LocalDate currentDate = LocalDate.now();
            List<Registration> registration = registrationRepository.findAllByStudentId(student.getId());
            for (Registration reg: registration) {
                List<Due> existingDues = dueRepository.findAllByStudentIdAndRegistrationId(reg.getStudent().getId(), reg.getId());
                Workshop workshop = workshopRepository.findByName(reg.getWorkshopName()).orElse(null);
                if (workshop == null) {
                    log.error("Workshop not found for registration: {}", reg.getId());
                    continue;
                }
                if (!existingDues.isEmpty()) {
                    Due latestDue = Collections.max(existingDues, Comparator.comparing(Due::getCreationDate));
                    if (latestDue.getCreationDate().plusDays(30).isEqual(LocalDate.now())) {
                        log.info("Generating due for registration: {}", reg.getId());
                        Due due = new Due();
                        due.setName("Cuota " + (existingDues.size() + 1));
                        due.setAmount(workshop.getPrice());
                        due.setCreationDate(currentDate);
                        due.setDueDate(currentDate.plusDays(7));
                        due.setStatus(DueStatusEnum.PENDING.name());
                        due.setStudentId(student.getId());
                        due.setRegistrationId(reg.getId());
                        due.setWorkshopName(workshop.getName());
                        dueRepository.save(due);

                    }
                    continue;
                }
                log.info("Generating due for registration: {}", reg.getId());
                Due due = new Due();
                due.setName("Cuota 1");
                due.setAmount(workshop.getPrice());
                due.setCreationDate(currentDate);
                due.setDueDate(currentDate.plusDays(7));
                due.setStatus(DueStatusEnum.PENDING.name());
                due.setStudentId(student.getId());
                due.setRegistrationId(reg.getId());
                due.setWorkshopName(workshop.getName());
                dueRepository.save(due);

            }
        }
    }
}
