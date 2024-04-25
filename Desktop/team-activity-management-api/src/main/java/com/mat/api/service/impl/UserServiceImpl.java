package com.mat.api.service.impl;


import com.mat.api.core.errorhandling.businessexeption.BusinessException;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.core.errorhandling.exeption.ErrorCode;
import com.mat.api.core.payload.request.RegisterRequest;
import com.mat.api.mapper.converter.IMapClassWithDto;
import com.mat.api.mapper.dto.UserDto;
import com.mat.api.models.profiles.UserEntity;
import com.mat.api.repository.UserRepository;
import com.mat.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    //private final RoleService roleService;

    private final IMapClassWithDto mapper;

    private final PasswordEncoder passwordEncoder;

    public UserDto addUser(final RegisterRequest registerRequest) {
        final Boolean existUser = !existsByUsername(registerRequest.getUsername());
        if (Boolean.TRUE.equals(existUser)) {
            if (registerRequest.getPassword() != null) {
                // Récupérer le rôle à partir de l'énumération Role
                //Role role = Role.valueOf(registerRequest.getRole());

                // Crypter le mot de passe
                registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

                // Convertir RegisterRequest en UserEntity
                UserEntity userEntity = mapper.convert(registerRequest, UserEntity.class);

                // Définir le rôle pour l'utilisateur
                userEntity.setRole(registerRequest.getRole());

                // Enregistrer l'utilisateur dans la base de données
                userEntity = userRepository.save(userEntity);

                // Convertir UserEntity en UserDto et le retourner
                return mapper.convert(userEntity, UserDto.class);
            } else {
                throw new BusinessException(ErrorCode.PASSWORD_AND_CONFIRM_PASSWORD_NOT_EQUALS);
            }
        } else {
            throw new BusinessException(ErrorCode.ALREADY_AUTHENTICATED);
        }
    }


    @Override
    public UserDto getCurrentUser() {
        String userName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            userName = authentication.getName();
        }
        return mapper.convert(getUserByUserName(userName), UserDto.class);

    }

    @Override
    public Boolean existsByUsername(final String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserEntity getUserByUserName(final String username) {
        if (username == null) {
            throw new BusinessException(ErrorCode.LOGIN_OR_PASSWORD_MUST_BE_NOT_NULL);
        }
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }

    @Override
    public UserDto updateUser(final Long id, final RegisterRequest registerRequest, final boolean pass) {

        if (registerRequest == null) {
            throw new BusinessException(CommonStatusCode.INVALID_PAYLOAD);
        } else if (StringUtils.isEmpty(registerRequest.getEmail())) {
            throw new BusinessException(CommonStatusCode.EMAIL_IS_MISSING);
        } else {
            UserEntity userEntity = mapper.convert(registerRequest, UserEntity.class);
            userEntity.setId(id);
            if (pass) {
                if (StringUtils.isEmpty(userEntity.getPassword())) {
                    throw new BusinessException(CommonStatusCode.PASSWORD_IS_MISSING);
                }
                String password = passwordEncoder.encode(registerRequest.getPassword());
                userEntity.setPassword(password);
                userEntity = userRepository.save(userEntity);
            } else {
                UserEntity oldUser = userRepository.findById(id)
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));
                userEntity.setPassword(oldUser.getPassword());
                userEntity = userRepository.save(userEntity);
            }
            return mapper.convert(userEntity, UserDto.class);
        }
    }

    @Override
    public void deleteUser(final Long id) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
         userRepository.deleteById(id);}

    @Override
    public Page<UserDto> getPagedListUser(Integer page, Integer size) {
        page = (page == null || page < 1) ? 0 : page - 1;
        size = (size == null || size < 5) ? 5 : size;

        Pageable pageRequest = PageRequest.of(page, size);

        Page<UserEntity> pageOfEntities = null;

        pageOfEntities = userRepository.findAll(pageRequest);

        if (CollectionUtils.isEmpty(pageOfEntities.getContent())) {
            throw new BusinessException(CommonStatusCode.NO_CONTENT_EXCEPTION);
        }


        return pageOfEntities.map(source -> mapper.convert(source,UserDto.class));

    }

    @Override
    public UserDto getUserById(final Long id) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));
        UserDto userDto= mapper.convert(user, UserDto.class);
        /*userDto.setRoleIds(user.getRoles().stream()
                .map(RoleEntity::getId)
                .collect(Collectors.toList()));*/
        return userDto;
    }


}
