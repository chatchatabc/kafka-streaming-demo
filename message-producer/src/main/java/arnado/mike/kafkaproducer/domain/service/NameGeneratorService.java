package arnado.mike.kafkaproducer.domain.service;

import arnado.mike.common.domain.model.FullName;

public interface NameGeneratorService {
    FullName generate();
}
