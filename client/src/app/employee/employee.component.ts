import {Component, OnInit} from '@angular/core';
import EmployeeModel from "../model/employee.model";

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit {

  employee: EmployeeModel;

  constructor(employee: EmployeeModel) {
    this.employee = employee;
  }

  ngOnInit(): void {
  }

}
