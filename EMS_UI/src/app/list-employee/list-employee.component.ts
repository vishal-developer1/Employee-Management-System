import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { EmployeeService } from './../employee.service';
import { Employee } from './../employee';
import { Component, } from '@angular/core';

@Component({
  selector: 'app-list-employee',
  templateUrl: './list-employee.component.html',
  styleUrls: ['./list-employee.component.css'],

})
export class ListEmployeeComponent {

  employees:Employee[]=[];

  constructor(private employeeService:EmployeeService,private router:Router){}

  ngOnInit():void{

    this.getEmployees();


  }

 private getEmployees(): void {
    this.employeeService.getEmployeeList().subscribe(data => {
      this.employees = data;
    });
  }


updateEmployee(id: number) {
  this.router.navigate(['update-employee', id]);
}



viewEmployee(id: number) {
  this.router.navigate(['employee-details', id]);
}


deleteEmployee(id: number) {

  this.employeeService.deleteEmployeeById(id).subscribe(
    data => {
      console.log(data);
      this.getEmployees();
    },
    error => console.log(error)
  );
}




}





