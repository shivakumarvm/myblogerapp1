package com.myblog1.payload;

import com.myblog1.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private List<PostDto> Content;
    private int pageNo;
    private int pageSize;
    private int totalElemnets;
    private int totalPages;
    private boolean isLast;
}
