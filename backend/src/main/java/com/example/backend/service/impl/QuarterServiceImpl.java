package com.example.backend.service.impl;

import com.example.backend.config.AppConstant;
import com.example.backend.entities.Quarter;
import com.example.backend.entities.Role;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.payload.QuarterDto;
import com.example.backend.repository.QuarterRepo;
import com.example.backend.repository.RoleRepo;
import com.example.backend.service.QuarterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuarterServiceImpl implements QuarterService {
    @Autowired
    private QuarterRepo quarterRepo;

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public List<QuarterDto> getAllFactory() {
        Role role = roleRepo.findById(AppConstant.FACTORY_ROLE_ID).orElseThrow(
                () -> new ResourceNotFoundException("Role", "RoleID", AppConstant.FACTORY_ROLE_ID)
        );
        List<Quarter> factories = quarterRepo.findAllByRole(role);
        List<QuarterDto> factoriesDto = factories.stream().map(
                factory -> mapper.map(factory, QuarterDto.class)).collect(Collectors.toList());
        return factoriesDto;
    }

    @Override
    public List<QuarterDto> getAllShop() {
        Role role = roleRepo.findById(AppConstant.SHOP_ROLE_ID).orElseThrow(
                () -> new ResourceNotFoundException("Role", "RoleID", AppConstant.SHOP_ROLE_ID)
        );
        List<Quarter> shops = quarterRepo.findAllByRole(role);
        List<QuarterDto> shopsDto = shops.stream().map(
                shop -> mapper.map(shop, QuarterDto.class)).collect(Collectors.toList());
        return shopsDto;
    }

    @Override
    public List<QuarterDto> getAllWarrantyCenter() {
        Role role = roleRepo.findById(AppConstant.WARRANTY_CENTER_ROLE_ID).orElseThrow(
                () -> new ResourceNotFoundException("Role", "RoleID", AppConstant.WARRANTY_CENTER_ROLE_ID)
        );
        List<Quarter> warrantyCenters = quarterRepo.findAllByRole(role);
        List<QuarterDto> warrantyCentersDto = warrantyCenters.stream().map(
                warrantyCenter -> mapper.map(warrantyCenter, QuarterDto.class)).collect(Collectors.toList());
        return warrantyCentersDto;
    }

    @Override
    public QuarterDto findQuarterByID(Long quarterID) {
        Quarter quarter = quarterRepo.findById(quarterID).orElseThrow(
                () -> new ResourceNotFoundException("Quarter", "QuarterID", quarterID)
        );
        QuarterDto quarterDto = mapper.map(quarter, QuarterDto.class);
        return quarterDto;
    }

    @Override
    public QuarterDto createQuarter(QuarterDto quarterDto, Long roleID) {
        Role role = roleRepo.findById(roleID).orElseThrow(
                () -> new ResourceNotFoundException("Role", "RoleID", roleID)
        );
        Quarter quarter = mapper.map(quarterDto, Quarter.class);
        quarter.setRole(role);
        Quarter newQuarter = quarterRepo.save(quarter);
        return mapper.map(newQuarter, QuarterDto.class);
    }

    @Override
    public QuarterDto updateQuarter(QuarterDto quarterDto, Long roleID, Long quarterID) {
        Role role = roleRepo.findById(roleID).orElseThrow(
                () -> new ResourceNotFoundException("Role", "RoleID", roleID)
        );
        Quarter quarter = quarterRepo.findById(quarterID).orElseThrow(
                () -> new ResourceNotFoundException("Quarter", "QuarterID", quarterID)
        );
        Quarter updateQuarter = mapper.map(quarterDto, Quarter.class);
        updateQuarter.setId(quarterID);
        quarterRepo.save(updateQuarter);
        return quarterDto;
    }

    @Override
    public void deleteQuarter(Long quarterID) {
        Quarter quarter = quarterRepo.findById(quarterID).orElseThrow(
                () -> new ResourceNotFoundException("Quarter", "QuarterID", quarterID)
        );
        quarterRepo.delete(quarter);
    }
}
