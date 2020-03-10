package com.github.mhzhou95.MingChatAppServer.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
public class ChatRoom {
    @Id @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true) @NotEmpty
    private String name;
    private String makerId;

    public ChatRoom() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMakerId() {
        return makerId;
    }

    public void setMakerId(String createrId) {
        this.makerId = createrId;
    }

}
