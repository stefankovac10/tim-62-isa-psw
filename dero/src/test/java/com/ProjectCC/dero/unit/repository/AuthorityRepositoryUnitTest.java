package com.ProjectCC.dero.unit.repository;


import com.ProjectCC.dero.model.Authority;
import com.ProjectCC.dero.repository.AuthorityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
public class AuthorityRepositoryUnitTest {

    @Autowired
    private AuthorityRepository authorityRepository;

    private static final String AUTHORITY_NAME_DOES_NOT_EXIST = "ROLE_STUDENT";
    private static final String AUTHORITY_NAME_EXIST = "ROLE_REQUEST";
    private static final Long AUTHORITY_ID = 1L;



    @Test
    public void shouldReturnEmptyOptionalWhenFindingNonExistingAuthorityByClinicId(){
        Authority authority = this.authorityRepository.findByName(AUTHORITY_NAME_DOES_NOT_EXIST);

        assertNull("Authority is not present.", authority);
    }

    @Test
    public void shouldReturnAuthorityWhenFindingExistingAuthorityById() {
        Authority authority = this.authorityRepository.findByName(AUTHORITY_NAME_EXIST);

        assertNotNull("Authority is present.", authority);
        assertCorrectAuthorityReturned(authority);
    }

    public void assertCorrectAuthorityReturned(Authority authority){
        assertEquals("Authority contains correct id", authority.getId(), AUTHORITY_ID);
    }

}
