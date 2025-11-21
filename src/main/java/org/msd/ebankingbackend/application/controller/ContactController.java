package org.msd.ebankingbackend.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.application.dto.request.ContactRequestDto;
import org.msd.ebankingbackend.application.dto.response.ContactResponseDto;
import org.msd.ebankingbackend.application.mapper.IControllerMapper;
import org.msd.ebankingbackend.domain.model.Contact;
import org.msd.ebankingbackend.domain.service.IContactService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/contacts")
@Tag(name = "Contacts", description = "API de gestion des contacts")
public class ContactController {

    private final IContactService contactService;
    private final IControllerMapper controllerMapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponseDto createContact(@Valid @RequestBody ContactRequestDto contactRequestDto) {
        Contact contact = controllerMapper.toContact(contactRequestDto);
        Contact savedContact = contactService.save(contact);
        return controllerMapper.toContactResponseDto(savedContact);
    }

    @GetMapping
    public List<ContactResponseDto> getAllContacts() {
        List<Contact> contacts = contactService.findAll();
        return contacts.stream()
                .map(controllerMapper::toContactResponseDto)
                .toList();
    }

    @GetMapping("/{contactId}")
    public ContactResponseDto getContact(@PathVariable Long contactId) {
        Contact contact = contactService.findById(contactId);
        return controllerMapper.toContactResponseDto(contact);
    }

    @GetMapping("/customer/{customerId}")
    public List<ContactResponseDto> getUserContacts(@PathVariable Long customerId) {
        List<Contact> contacts = contactService.findAllByCustomerId(customerId);
        return contacts.stream()
                .map(controllerMapper::toContactResponseDto)
                .toList();
    }

    @DeleteMapping("/{contactId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable Long contactId) {
        contactService.delete(contactId);
    }
}
