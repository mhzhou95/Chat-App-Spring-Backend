package com.github.mhzhou95.MingChatAppServer.controller;

import com.github.mhzhou95.MingChatAppServer.model.ChatRoom;
import com.github.mhzhou95.MingChatAppServer.model.Message;
import com.github.mhzhou95.MingChatAppServer.model.User;
import com.github.mhzhou95.MingChatAppServer.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/chatroom")
@Controller
public class ChatRoomController {
    private ChatRoomService chatroomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatroomService){
        this.chatroomService = chatroomService;
        ChatRoom chatroom = new ChatRoom();
        chatroom.setName("general");
        chatroom.setMakerId("admin");
        this.createChatRoom(chatroom);
    }

    @CrossOrigin
    @PostMapping("/")
    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoom chatRoom){
        ChatRoom chatroom = chatroomService.createChatRoom(chatRoom);
        ResponseEntity<?> responseCreate = new ResponseEntity<>(chatroom, HttpStatus.CREATED);
        return  responseCreate;
    }

    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity<?> findAll(){
            Collection<ChatRoom> chatRooms = chatroomService.findAll();
            ResponseEntity<?> responseGetChatRooms = new ResponseEntity<>(chatRooms, HttpStatus.OK);
            return responseGetChatRooms;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getChatRoom(@PathVariable Long id){
        ChatRoom chatRoom = chatroomService.getChatRoom(id);
        ResponseEntity<?> responseGet = new ResponseEntity<>(chatRoom, HttpStatus.OK);
        return  responseGet;
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChatRoom(@PathVariable Long id, @RequestParam String userId){
        ChatRoom chatroom = chatroomService.getChatRoom(id);
        if(chatroom.getMakerId().equals(userId)) {
            chatroomService.delete(id);
            String deleted = "chatroom " + chatroom.getName() + " deleted";
            ResponseEntity<?> responseDelete = new ResponseEntity<>(deleted, HttpStatus.OK);
            return responseDelete;
        }else {
            String invalidRights = "You are not the creator of this chatroom " + userId;
            ResponseEntity<?> responseDelete = new ResponseEntity<>(invalidRights, HttpStatus.OK);
            return responseDelete;
        }
    }
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<?> editName(@PathVariable Long id, @RequestBody ChatRoom chatroom){
        ChatRoom editChatRoom = chatroomService.editName(id, chatroom);
        ResponseEntity<?> responseEdit = new ResponseEntity<>(editChatRoom, HttpStatus.OK);
        return responseEdit;
    }
}
