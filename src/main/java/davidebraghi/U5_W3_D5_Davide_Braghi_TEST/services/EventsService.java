package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.services;

import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.entities.Event;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.exceptions.BadRequestException;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.exceptions.NotFoundException;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.payloads_DTO.Event_DTO.NewEventDTO;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.repositories.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventsService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UsersService usersService;

    // FIND ALL

    public Page<Event> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.eventRepository.findAll(pageable);
    }

    // SAVE

    public Event save(NewEventDTO payload) {
        this.eventRepository.findByTitle(payload.title()).ifPresent(event -> {
                    throw new BadRequestException("The Event " + " is already insert.");
                }
        );

        Event newEvent = new Event(payload.title(),
                payload.description(),
                payload.startDateTime(),
                payload.totalSeats()
        );

        Event savedEvent = this.eventRepository.save(newEvent);

        log.info("The Event with ID: " + savedEvent.getId() + " has been duly saved.");

        return savedEvent;
    }

    // FIND BY ID

    public Event findById(Long id) {
        return this.eventRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    // FIND BY ID & UPDATE

    public Event findByIdAndUpdate(Long id, NewEventDTO payload) {
        Event found = this.findById(id);

        if (!found.getTitle().equals(payload.title())) {
            this.eventRepository.findByTitle(payload.title()).ifPresent(event -> {
                        throw new BadRequestException("The Event titled " + event.getTitle() + " is already insert.");
                    }
            );
        }

        found.setTitle(payload.title());
        found.setDescription(payload.description());
        found.setStartDateTime(payload.startDateTime());
        found.setTotalSeats(payload.totalSeats());

        Event modifyEvent = this.eventRepository.save(found);

        log.info("Event with ID: " + modifyEvent.getId() + " has been duly updated.");

        return modifyEvent;
    }

    // FIND BY ID & DELETE

    public void findByIdAndDelete(Long id) {
        Event found = this.findById(id);
        this.eventRepository.delete(found);
    }
}
