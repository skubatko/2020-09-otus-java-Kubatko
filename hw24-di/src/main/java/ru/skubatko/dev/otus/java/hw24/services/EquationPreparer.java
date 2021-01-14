package ru.skubatko.dev.otus.java.hw24.services;

import ru.skubatko.dev.otus.java.hw24.model.Equation;

import java.util.List;

public interface EquationPreparer {
    List<Equation> prepareEquationsFor(int base);
}
