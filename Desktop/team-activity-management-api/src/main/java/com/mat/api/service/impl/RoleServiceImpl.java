/*package com.mat.api.service.impl;

import com.mat.api.core.errorhandling.businessexeption.BusinessException;
import com.mat.api.core.errorhandling.exeption.ErrorCode;
import com.mat.api.models.profiles.RoleEntity;
import com.mat.api.service.RoleService;
import com.mat.api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleEntity getProfileById(final Long profileId) {
        final Optional<RoleEntity> profileEntity = roleRepository.findById(profileId);
        if (profileEntity.isEmpty()) {
            throw new BusinessException(ErrorCode.PROFILE_NOT_EXIST);
        }
        return profileEntity.get();
    }
}

 */
