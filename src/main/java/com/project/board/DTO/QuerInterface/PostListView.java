package com.project.board.DTO.QuerInterface;

import java.time.LocalDateTime;

public interface PostListView {
   
    public Long getPost_pid();
    public String getPost_title();
    public String getPost_content();
    public LocalDateTime getPost_create_date();
    public String getUser_nickname();
    public String getCategory_name();
    public int getComment_count();
    public int getRec_count();
    public int getPost_view();
    public int getPost_mine();
    public int getPost_recommend();
}