package com.engineering.journalApp.controller;

import com.engineering.journalApp.entity.JournalEntry;
import com.engineering.journalApp.entity.User;
import com.engineering.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.engineering.journalApp.service.JournalEntryService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;


    @GetMapping("{userName}")//http:localhost:8080/journal GET
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User user=userService.findByUserName(userName);
        List<JournalEntry> all=user.getJournalEntries();
        if(all != null && !all.isEmpty())
        return new ResponseEntity<>(all,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")//http:localhost:8080/journal Post
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName) {
        try{
            journalEntryService.saveEntry(myEntry,userName);
            return  new ResponseEntity<>(myEntry,HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry=journalEntryService.getById(myId);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        if(journalEntry.isPresent())
//            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myId, @PathVariable String userName) {
        journalEntryService.deleteById(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry,@PathVariable String userName) {
        JournalEntry old=journalEntryService.getById(myId).orElse(null);
        if(old!=null){
            old.setContent(newEntry.getContent()!=null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());
            old.setTitle(newEntry.getTitle()!=null && !newEntry.equals("") ? newEntry.getTitle() : old.getTitle());
            journalEntryService.saveEntry(old, userName);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
