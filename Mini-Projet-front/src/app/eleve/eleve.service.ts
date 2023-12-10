import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Eleve } from './eleve';
@Injectable({
  providedIn: 'root'
})
export class EleveService {

  constructor(private httpClient: HttpClient) { }

  
  GetAllEleve()
  {
      return this.httpClient.get<[]>('http://localhost:8088/Amine/Eleves');
  }

  
  AddEleve(etu : any):Observable<any> 
  {
  
    return this.httpClient.post('http://localhost:8088/Amine/add',etu )
  }

  updateEleve(id: number, updatedEleve: Eleve): Observable<Eleve> {
    const url = `http://localhost:8088/Amine/update/${id}`;
    return this.httpClient.put<Eleve>(url, updatedEleve);
  }

  deleteEleve(id: number): Observable<void> {
    const apiUrl = `http://localhost:8088/Amine/delete/${id}`;
    
    return this.httpClient.delete<void>(apiUrl);
  }

  
  searchEleve(query: string): Observable<any[]> {
    // Utilisez la requête HTTP appropriée pour la recherche
    const url = `http://localhost:8088/Amine/search?keyword=${query}`;
    return this.httpClient.get<any[]>(url);
  }

}
