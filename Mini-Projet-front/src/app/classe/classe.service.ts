import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Class } from './Classe';

@Injectable({
  providedIn: 'root'
})
export class ClasseService {

  constructor(private httpClient: HttpClient) { }

  
  
  GetAllClasse()
  {
      return this.httpClient.get<[]>('http://localhost:8088/Classe/classes');
  }

  AddClasse(etu : any):Observable<any> 
  {
  
    return this.httpClient.post('http://localhost:8088/Classe/addClasse',etu )
  }

  deleteClasse(id: number): Observable<void> {
    const apiUrl = `http://localhost:8088/Classe/deleteClasse/${id}`;
    
    return this.httpClient.delete<void>(apiUrl);
  }

  sortClasse(): Observable<any> {
    const url = 'http://localhost:8088/Classe/sort';
    return this.httpClient.get(url);
  }

  
  updateClasse(id: number, updatedClasse: Class): Observable<Class> {
    const url = `http://localhost:8088/Classe/updateClasse/${id}`;
    return this.httpClient.put<Class>(url, updatedClasse);
  }

  getClassesAndStudents() {
    return this.httpClient.get('http://localhost:8088/Classe/classes-and-students');
  }
}
