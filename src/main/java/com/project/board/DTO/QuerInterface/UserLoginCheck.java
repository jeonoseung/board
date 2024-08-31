package com.project.board.DTO.QuerInterface;


import lombok.Getter;

import java.time.LocalDateTime;


public interface UserLoginCheck {

    public Long getUser_pid();
    public String getUser_id();
    public String getUser_pass();
    public String getUser_nickname();
    public LocalDateTime getUser_create_date();
}