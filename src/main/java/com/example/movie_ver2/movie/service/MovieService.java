package com.example.movie_ver2.movie.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.movie_ver2.movie.dto.MovieUploadRequestDto;
import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final AmazonS3Client amazonS3Client;
    private final MovieRepository movieRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public Movie saveMovieInfo(MovieUploadRequestDto requestDto, MultipartFile posterImg, List<MultipartFile> previewImgs) throws IOException {
        requestDto.setPosterImgPath(uploadToS3(posterImg));
        requestDto.setPreviewImgPath(uploadToS3(previewImgs));

        return movieRepository.save(requestDto.toEntity());
    }

    public String uploadToS3(MultipartFile multipartFile) throws IOException {
        String key = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getInputStream().available());

        amazonS3Client.putObject(bucketName, key, multipartFile.getInputStream(), metadata);

        return amazonS3Client.getUrl(bucketName, key).toString();
    }



    public List<String> uploadToS3(List<MultipartFile> multipartFile) throws IOException {

        List<String> imgUrlList = new ArrayList<>();

        multipartFile.forEach(file -> {
            String key = UUID.randomUUID() + "_" + file.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());

            try {
                amazonS3Client.putObject(bucketName, key, file.getInputStream(), metadata);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            imgUrlList.add(key);
        });
        return imgUrlList;
    }




}