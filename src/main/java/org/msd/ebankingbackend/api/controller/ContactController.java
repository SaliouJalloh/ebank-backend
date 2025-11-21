package org.msd.ebankingbackend.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.msd.ebankingbackend.api.dto.ContactDto;
import org.msd.ebankingbackend.api.mapper.IControllerMapper;
import org.msd.ebankingbackend.domain.model.Contact;
import org.msd.ebankingbackend.domain.service.IContactService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/contacts")
public class ContactController {

    private final IContactService contactService;
    private final IControllerMapper controllerMapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDto createContact(@Valid @RequestBody Contact contact) {
        Contact savedContact = contactService.save(contact);
        return controllerMapper.toContactDto(savedContact);
    }

    @GetMapping
    public List<ContactDto> getAllContacts() {
        List<Contact> contacts = contactService.findAll();
        return contacts.stream()
                .map(controllerMapper::toContactDto)
                .toList();
    }

    @GetMapping("/{contactId}")
    public ContactDto getContact(@PathVariable Long contactId) {
        Contact contact = contactService.findById(contactId);
        return controllerMapper.toContactDto(contact);
    }

    @GetMapping("/customer/{customerId}")
    public List<ContactDto> getUserContacts(@PathVariable Long customerId) {
        List<Contact> contacts = contactService.findAllByCustomerId(customerId);
        return contacts.stream()
                .map(controllerMapper::toContactDto)
                .toList();
    }

    @DeleteMapping("/{contactId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable Long contactId) {
        contactService.delete(contactId);
    }
}
