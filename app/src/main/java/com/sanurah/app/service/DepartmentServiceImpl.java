package com.sanurah.app.service;

import com.sanurah.app.entity.Department;
import com.sanurah.app.model.DepartmentModel;
import com.sanurah.app.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;

public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;
    ContactService contactService;
    ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ContactService contactService,
            ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.contactService = contactService;
        this.modelMapper = modelMapper;
    }

    @Override
    public DepartmentModel createDepartment(DepartmentModel departmentModel) {
        return map(departmentRepository.save(map(departmentModel)));
    }

    private Department map(DepartmentModel departmentModel) {
        return modelMapper.map(departmentModel, Department.class);
    }

    private DepartmentModel map(Department department) {
        return modelMapper.map(department, DepartmentModel.class);
    }
}
