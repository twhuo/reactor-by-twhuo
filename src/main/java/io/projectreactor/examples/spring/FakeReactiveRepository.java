package io.projectreactor.examples.spring;

import io.projectreactor.examples.Sir;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.function.Function;

/**
 * A fake incomplete Spring Data {@link ReactiveCrudRepository} to naively demonstrate
 * a reactive findOne.
 *
 * @author Simon Baslé
 */
@Repository
public class FakeReactiveRepository implements ReactiveCrudRepository<Sir, String> {

	@Override
	public <S extends Sir> Mono<S> save(S s) {
		return null;
	}

	@Override
	public <S extends Sir> Flux<S> saveAll(Iterable<S> iterable) {
		return null;
	}

	@Override
	public <S extends Sir> Flux<S> saveAll(Publisher<S> publisher) {
		return null;
	}

	@Override
	public Mono<Sir> findById(String s) {
		Function<String,Mono> fun = id -> {
			if ("notfound".equalsIgnoreCase(id))
				return Mono.empty();
			else
				return Mono.just(new Sir(id, "GUY"));
		};
		return Mono.just(s).then(fun.apply(s));
	}

	@Override
	public Mono<Sir> findById(Publisher<String> publisher) {
		return null;
	}

	@Override
	public Mono<Boolean> existsById(String s) {
		return null;
	}

	@Override
	public Mono<Boolean> existsById(Publisher<String> publisher) {
		return null;
	}

	@Override
	public Flux<Sir> findAll() {
		return null;
	}

	@Override
	public Flux<Sir> findAllById(Iterable<String> iterable) {
		return null;
	}

	@Override
	public Flux<Sir> findAllById(Publisher<String> publisher) {
		return null;
	}

	@Override
	public Mono<Long> count() {
		return null;
	}

	@Override
	public Mono<Void> deleteById(String s) {
		return null;
	}

	@Override
	public Mono<Void> deleteById(Publisher<String> publisher) {
		return null;
	}

	@Override
	public Mono<Void> delete(Sir sir) {
		return null;
	}

	@Override
	public Mono<Void> deleteAll(Iterable<? extends Sir> iterable) {
		return null;
	}

	@Override
	public Mono<Void> deleteAll(Publisher<? extends Sir> publisher) {
		return null;
	}

	@Override
	public Mono<Void> deleteAll() {
		return null;
	}
}
