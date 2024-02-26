//package com.engineering.journalApp.controller;
//
//import com.engineering.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryController {
//
//    Map<Long, JournalEntry> map=new HashMap<>();
//
//    @GetMapping//http:localhost:8080/journal GET
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(map.values());
//    }
//
//    @PostMapping//http:localhost:8080/journal Post
//    public Boolean createEntry(@RequestBody JournalEntry myEntry){
//        map.put(myEntry.getId(),myEntry);
//        return true;
//    }
//
//    @GetMapping("id/{myId}")
//    public JournalEntry getJournalById(@PathVariable Long myId){
//        return map.get(myId);
//    }
//
//    @DeleteMapping("id/{myId}")
//    public JournalEntry deleteJournalById(@PathVariable Long myId){
//        return map.remove(myId);
//    }
//
//    @PutMapping("id/{myId}")
//    public Boolean getJournalById(@PathVariable Long myId,@RequestBody JournalEntry myEntry){
//        map.put(myId,myEntry);
//        return true;
//    }
//}
