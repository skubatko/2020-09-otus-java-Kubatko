package ru.skubatko.dev.otus.java.hw30.service;

import ru.skubatko.dev.otus.java.hw30.domain.User;
import ru.skubatko.dev.otus.java.hw30.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public User findByLogin(String login) {
        return repository.findByLogin(login).orElseThrow();
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }
}
