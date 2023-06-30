package com.mineirinho.mercado.services;

import com.mineirinho.mercado.dto.UserDTO;
import com.mineirinho.mercado.model.User;
import com.mineirinho.mercado.model.exception.ResourceNotFoundException;
import com.mineirinho.mercado.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Método para retornar uma lista de Usuários
     * @return Lista de Usuários.
     */
    public List<UserDTO> obterTodos() {
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> new ModelMapper().map(user, UserDTO.class)).collect(Collectors.toList());
    }


    /**
     * Método para remover um Usuário por Id.
     * @param id do Usuário
     */
    public void deletar(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new ResourceNotFoundException("Usuário não encontrado, O Usuário com id: " + id + " não foi encontrado para ser excluído.");
        }

        userRepository.deleteById(id);
    }

    /**
     * Método para adicionar um Usuário
     * @param userDto
     * @return um usuário adicionado no banco de dados.
     */
    public UserDTO adicionar(UserDTO userDto) {
        userDto.setId(null);
        User user = new ModelMapper().map(userDto, User.class);
        user = userRepository.save(user);
        userDto.setId(user.getId());

        return userDto;
    }

    /**
     * Method for updating a User
     * @param id
     * @param userDto
     * @return
     */
    public UserDTO atualizar(Long id, UserDTO userDto) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()){
            throw new ResourceNotFoundException("Usuário não encontrado, O Usuário com id: " + id + " não foi encontrado para ser atualizado.");
        }
        userDto.setId(id);
        User user = new ModelMapper().map(userDto, User.class);
        userRepository.save(user);

        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user!= null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }

    }
}
