package org.daniels.spring.jpa.optimisticlocking;


import org.daniels.spring.jpa.optimisticlocking.domain.Movie;
import org.daniels.spring.jpa.optimisticlocking.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class OptimisticLockingApplicationTests {

	@Autowired
	private MovieRepository movieRepository;

	@BeforeEach
	public void setUp() throws Exception {
		movieRepository.save(new Movie("The Witcher", 5));
	}

	@Test
	public void testConcurrencyWriting() {
		assertThat(movieRepository.count()).isEqualTo(1);

		Optional<Movie> firstFoundMove = movieRepository.findById(1L);
		Optional<Movie> secondFoundMovie = movieRepository.findById(1L);
		assertThat(firstFoundMove.get().getVersion()).isEqualTo(0);
		assertThat(secondFoundMovie.get().getVersion()).isEqualTo(0);

		// 1st update
		firstFoundMove.get().setRating(7);
		Movie firstUpdatedMovie = movieRepository.save(firstFoundMove.get());
		// here version should be updated to next version
		assertThat(firstUpdatedMovie.getVersion()).isEqualTo(1);

		// 2nd update
		secondFoundMovie.get().setRating(8);
		// here still version is 0 but previous update changed a version from 0 -> 1
		// this leads to the situation with optimistic locking exception
		Movie secondUpdatedMovie = movieRepository.save(secondFoundMovie.get());
	}

}
