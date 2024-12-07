package io.github.geniusay.engine;

import io.github.geniusay.template.OTOWTemplate;

public abstract class Engine<T extends OTOWTemplate, R> {

    public abstract R generate(T template);

}
