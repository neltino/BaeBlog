package com.example.fashionblog.Service;

import com.example.fashionblog.DTO.LikesDTO;

public interface LikesService {
    int likePost(LikesDTO userId);
    int unlikePost(LikesDTO userId);
}
