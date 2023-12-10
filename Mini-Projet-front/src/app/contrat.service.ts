import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ContratService {
  private apiUrl = 'http://localhost:8083/Api/contrats'; // Replace with your actual API URL

  constructor(private http: HttpClient) {}

  // Create a Contrat
  createContrat(contratData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}`, contratData);
  }

  // Read all Contrats
  getAllContrats(): Observable<any> {
    return this.http.get(`${this.apiUrl}`);
  }

  // Read a single Contrat by ID
  getContrat(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  // Update a Contrat by ID
  updateContrat(id: number, updatedContratData: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, updatedContratData);
  }

  // Delete a Contrat by ID
  deleteContrat(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
