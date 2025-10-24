package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.services;

import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.entities.Reservation;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.exceptions.BadRequestException;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.exceptions.NotFoundException;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.payloads_DTO.Reservation_DTO.NewReservationDTO;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.repositories.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReservationsService {
    @Autowired
    private ReservationRepository reservationRepository;

    // FIND ALL

    public Page<Reservation> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.reservationRepository.findAll(pageable);
    }

    // SAVE

    public Reservation save(NewReservationDTO payload) {
        this.reservationRepository.findByStatusAndCreatedAt(payload.status(), payload.createdAt()).ifPresent(reservation -> {
                    throw new BadRequestException("The Reservation " + " is already insert.");
                }
        );

        Reservation newReservation = new Reservation(
                payload.status(),
                payload.createdAt()
        );

        Reservation savedReservation = this.reservationRepository.save(newReservation);

        log.info("The Reservation with ID: " + savedReservation.getId() + " has been duly saved.");

        return savedReservation;
    }

    // FIND BY ID

    public Reservation findById(Long id) {
        return this.reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    // FIND BY ID & DELETE

    public void findByIdAndDelete(Long id) {
        Reservation found = this.findById(id);
        this.reservationRepository.delete(found);
    }
}
