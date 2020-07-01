package com.dovile.springbootrest.springbootrest;

import com.dovile.springbootrest.springbootrest.entities.Owner;
import com.dovile.springbootrest.springbootrest.repository.OwnerRepository;
import com.dovile.springbootrest.springbootrest.service.OwnerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceTest {

    @InjectMocks
    private OwnerService ownerService;

    @Mock
    private OwnerRepository ownerRepository;

    @Test
    public void shouldSavedOwnerSuccessFully() {
        final Owner owner = new Owner(null, "Tom");
        given(ownerRepository.save(owner)).willAnswer(invocation -> invocation.getArgument(0));
        Owner savedOwner = ownerService.createOwner(owner);
        assertThat(savedOwner).isNotNull();
        verify(ownerRepository).save(any(Owner.class));
    }

    @Test
    public void shouldReturnAllOwners() {
        List<Owner> ownerList = new ArrayList();
        ownerList.add(new Owner(1,  "Tom"));
        ownerList.add(new Owner(2,  "Tom1"));
        ownerList.add(new Owner(3,  "Tom2"));
        given(ownerRepository.findAll()).willReturn(ownerList);
        List<Owner> expected = ownerService.findAllOwners();
        assertEquals(expected, ownerList);
    }

    @Test
    public void findOwnerById(){
        final Integer id = 1;
        final Owner owner = new Owner(1,  "Tom");
        given(ownerRepository.findById(id)).willReturn(Optional.of(owner));
        final Optional<Owner> expected = ownerService.findOwnerById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    public void findOwnerByName(){
        final String name = "Tom";
        final Owner owner = new Owner(1,  "Tom");
        given(ownerRepository.findBYName(name)).willReturn(owner);
        final Owner expected = ownerService.findOwnerByName(name);
        assertThat(expected).isNotNull();
    }

    @Test
    public void shouldDeleteOwner() {
        final Integer ownerId = 1;
        ownerService.deleteOwnerById(ownerId);
        ownerService.deleteOwnerById(ownerId);
        verify(ownerRepository, times(2)).deleteById(ownerId);
    }


}
