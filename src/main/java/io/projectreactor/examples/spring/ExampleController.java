package io.projectreactor.examples.spring;

import io.projectreactor.examples.MyReactiveLibrary;
import io.projectreactor.examples.Sir;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Function;

/**
 * @author Simon Baslé
 */
@RestController
public class ExampleController {
	private Logger logger = LoggerFactory.getLogger(ExampleController.class);
	private final MyReactiveLibrary reactiveLibrary;

	//Note Spring Boot 4.3+ autowires single constructors now
	public ExampleController(MyReactiveLibrary reactiveLibrary) {
		this.reactiveLibrary = reactiveLibrary;
	}

	@GetMapping("hello/{who}")
	public Mono<String> hello(@PathVariable String who) {
		return Mono.just(who)
		           .map(w -> "Hello " + w + "!");
	}

	@GetMapping("helloDelay/{who}")
	public Mono<String> helloDelay(@PathVariable String who) {
		return reactiveLibrary.withDelay("Hello " + who + "!!", 2);
	}

	@PostMapping("heyMister")
	public Flux<String> hey(@RequestBody Mono<Sir> body) {
		logger.info("-------11-------");
//		logger.info(body.toString());
//		logger.info(body.block().toString());
		Function<Sir, Mono<String>> fun = sir -> Flux.fromArray(sir.getLastName().split("")).next();
//		fun.apply((Sir)body.block());
		logger.info("-------12-------");
		return Mono.just("Hey mister ")
				.concatWith(body
						.flatMap(fun)
						.map(String::toUpperCase)
						.take(Duration.ofSeconds(1))
				).concatWith(Mono.just(". how are you?"));
	}
}
