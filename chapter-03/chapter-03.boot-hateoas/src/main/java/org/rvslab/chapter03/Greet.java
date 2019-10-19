package org.rvslab.chapter03;

import lombok.*;
import org.springframework.hateoas.ResourceSupport;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Greet extends ResourceSupport {
    @NonNull
    private String message;
}
