// enseignant.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EnseignantService {
  private enseignantsUrl = 'http://localhost:8086/enseignants'; // Update the URL to match your API endpoint
  private contratUrl = 'http://localhost:8086/contrats';  
  constructor(private http: HttpClient) {}

  // Enseignant CRUD operations
  getAllEnseignants() {
    return this.http.get(this.enseignantsUrl);
  }
  getContrats() {
    return this.http.get(this.contratUrl);
  }


  getEnseignantById(id: number) {
    return this.http.get(`${this.enseignantsUrl}/${id}`);
  }

  createEnseignant(enseignantData: any) {
    return this.http.post(this.enseignantsUrl, enseignantData);
  }
  

  updateEnseignant(id: number, enseignant: any) {
    return this.http.put(`${this.enseignantsUrl}/${id}`, enseignant);
  }

  deleteEnseignant(id: number) {
    return this.http.delete(`${this.enseignantsUrl}/${id}`);
  }
}
