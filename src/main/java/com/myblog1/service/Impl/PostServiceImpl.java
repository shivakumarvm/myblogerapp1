package com.myblog1.service.Impl;

import com.myblog1.exception.ResourceNotFoundException;
import com.myblog1.payload.PostResponse;
import org.modelmapper.ModelMapper;
import com.myblog1.entity.Post;
import com.myblog1.payload.PostDto;
import com.myblog1.repository.PostRepository;
import com.myblog1.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //Post post = mapToEntity(postDto);
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);

        //PostDto dto = mapToDto(newPost);
        PostDto dto = new PostDto();
        dto.setId(newPost.getId());
        dto.setTitle(newPost.getTitle());
        dto.setDescription(newPost.getDescription());
        dto.setContent(newPost.getContent());

        return dto;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
               Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
      //  Sort sort = null;
      //  if(sortDir.equalsIgnoreCase("asc")){
      //     sort= Sort.by(sortBy).ascending();
      //  }else{
      //      sort = Sort.by(sortBy).descending()
      //  }
        PageRequest pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Post> content = postRepository.findAll(pageable);
        List<Post> posts = content.getContent();

        List<PostDto> dto = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(dto);
        postResponse.setPageNo(content.getNumber());
        postResponse.setPageSize(content.getSize());
        postResponse.setTotalPages(content.getTotalPages());
        postResponse.setTotalElemnets((int)content.getTotalElements());
        postResponse.setLast(content.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Post","Id",id)
        );
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Post","Id",id)
        );

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatePost = postRepository.save(post);
        return mapToDto(updatePost);
    }

    @Override
    public void deletePostById(long id) {
       Post post = postRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Post","Id",id)
        );
       postRepository.deleteById(id);

//        Optional<Post> byId = postRepository.findById(id);
//        if(byId.isPresent()){
//            postRepository.deleteById(id);
//        }else
//        {
//
//        }
    }

    PostDto mapToDto(Post post){
     PostDto postDto = mapper.map(post, PostDto.class);
     return postDto;
    }

    Post mapToEntity(PostDto postDto){
        Post post = mapper.map(postDto,Post.class);
        return post;
    }
}
