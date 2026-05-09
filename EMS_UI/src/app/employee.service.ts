import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

private baseURL = "http://localhost:8080/employees";

constructor(private httpClient: HttpClient) { }

getEmployeeList(): Observable<Employee[]> {
  return this.httpClient.get<Employee[]>(`${this.baseURL}`);
}



createEmployee(employee: Employee): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, employee);
  }


getEmployeeById(id: number): Observable<Employee> {
  return this.httpClient.get<Employee>(`${this.baseURL}/${id}`
  );

}


// Update Employee
updateEmployee(id: number, employee: Employee): Observable<Object> {

  return this.httpClient.put(
    `${this.baseURL}/${id}`,
    employee
  );
}


// Delete Employee
deleteEmployeeById(id: number): Observable<Object> {

  return this.httpClient.delete(
    `${this.baseURL}/${id}`
  );
}

}

