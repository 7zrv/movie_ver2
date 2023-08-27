document.addEventListener("DOMContentLoaded", function () {
    const genreForm = document.getElementById("genreForm");
    const genreMovies = document.getElementById("genreMovies");

    genreForm.addEventListener("submit", function (event) {
        event.preventDefault();
        const genre = genreForm.genre.value;

        fetch(`/api/movie/getMovieInfoByGenre?genre=${genre}`)
            .then(response => response.json())
            .then(data => {
                const movies = data.data;
                const genreMoviesHTML = movies.map(movie => `
                    <div class="movie-card">
                        <img src="${movie.posterImgPath}" alt="Movie Poster" class="movie-poster">
                        <p>${movie.openingDate}</p>
                        <p>${movie.title}</p>
                        <p>${movie.country}</p>
                        <a href="/movieDetail/${movie.id}" class="detail-button">상세보기</a>
                    </div>
                `).join("");

                genreMovies.innerHTML = `<div class="genre-movie-group">${genreMoviesHTML}</div>`;
            })
            .catch(error => {
                console.error("Error fetching movies:", error);
            });
    });
});
