package com.project.board.DTO.QuerInterface;

import java.time.LocalDateTime;

public interface CommentList {
    public Long getComment_pid();
    public String getComment_content();
    public LocalDateTime getComment_create_date();
    public String getUser_nickname();
    public String getComment_target();
    public int getComment_mine();
}