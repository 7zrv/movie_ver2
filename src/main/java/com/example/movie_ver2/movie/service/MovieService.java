package com.example.movie_ver2.movie.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.movie_ver2.movie.dto.MovieUpdateRequestDto;
import com.example.movie_ver2.movie.dto.MovieUploadRequestDto;
import com.example.movie_ver2.movie.entity.Movie;
import com.example.movie_ver2.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final AmazonS3Client amazonS3Client;
    private final MovieRepository movieRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    //영화정보 저장
    public Movie saveMovieInfo(MovieUploadRequestDto requestDto, MultipartFile posterImg, List<MultipartFile> previewImgs) throws IOException{

        requestDto.setPosterImgPath(uploadToS3(posterImg));
        requestDto.setPreviewImgPath(uploadToS3(previewImgs));
        return movieRepository.save(requestDto.toEntity());
    }

    //영화 이미지 s3 업로드
    public String uploadToS3(MultipartFile multipartFile) throws IOException {
        String key = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getInputStream().available());

        amazonS3Client.putObject(bucketName, key, multipartFile.getInputStream(), metadata);

        return amazonS3Client.getUrl(bucketName, key).toString();
    }

    public List<String> uploadToS3(List<MultipartFile> multipartFile){

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

            imgUrlList.add(amazonS3Client.getUrl(bucketName, key).toString());
        });
        return imgUrlList;
    }


    //영화 정보 페이징 조회
    public List<Movie> getMovies(int pageNum, int pageSize, String criteria) {

        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, criteria));

        Page<Movie> page = movieRepository.findAll(pageable);

        return page.getContent();
    }

    //영화 정보 단건조회
    public Movie getMovie(Long movieId) throws NoSuchElementException{

        Optional<Movie> optionalMovie = movieRepository.findById(movieId);

        return optionalMovie.orElseThrow(NoSuchElementException::new);
    }

    //장르별 영화 정보 조회
    public List<Movie> getMoviesByGenre(String genre) {

        List<Movie> movies = movieRepository.findByGenreContaining(genre);

        if (movies.isEmpty()) {
            throw new NoSuchElementException();
        }
        return movies;
    }


    //영화정보 업데이트
    @Transactional
    public Movie updateMovieInfo(Long movieId, MovieUpdateRequestDto requestDto){

        Movie movie = movieRepository.findById(movieId).orElseThrow(NoSuchElementException::new);

        movie.updateMovie(requestDto);

        return movie;
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }
}