package com.tactfactory.monprojetsb.repository;

import java.util.Arrays;
import java.util.Optional;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.tactfactory.monprojetsb.entities.User;

public class MockitoUserRepository {
	protected final UserRepository repository;

    public User entity;
    public User resultE;
    public Long number;
    public Optional<User> optional;

    public MockitoUserRepository(UserRepository repository) {
        this.repository = repository;
        this.number = 1L;

        this.entity = new User();
        this.entity.setFirstname("firstname");
        this.entity.setLastname("lastname");
    }

    public void intialize() {
        this.resultE = new User();
        this.resultE.setId(this.entity.getId());
        this.resultE.setFirstname(this.entity.getFirstname());
        this.resultE.setLastname(this.entity.getLastname());
        this.resultE.setId(1L);
        this.optional = Optional.of(this.resultE);

        Mockito.when(this.repository.findById(1L)).thenReturn(this.optional);

        Mockito.when(this.repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(new PageImpl<>(Arrays.asList(this.resultE)));

        Mockito.when(this.repository.save(ArgumentMatchers.any())).thenAnswer(new Answer<User>() {
            @Override
            public User answer(InvocationOnMock invocation) throws Throwable {
                User user = invocation.getArgument(0);
                user.setId(1L);
                MockitoUserRepository.this.number++;
                return MockitoUserRepository.this.resultE;
            }
        });

        Mockito.when(this.repository.count()).thenAnswer(new Answer<Long>() {
            @Override
            public Long answer(InvocationOnMock invocation) throws Throwable {
                return MockitoUserRepository.this.number;
            }
        });
    }
}
