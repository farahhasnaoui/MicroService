// enseignant.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class EnseignantService {
  private enseignantsUrl = 'http://localhost:8083/Api/enseignants'; // Update the URL to match your API endpoint
  private contratUrl = 'http://localhost:8083/Api/contrats';  
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
  createEnseignant(enseignantData: any, contratId: number): Observable<any> {
    return this.http.post('http://localhost:8083/Api/enseignants', enseignantData, { params: { contratId: contratId } });
  }

  updateEnseignant(id: number, enseignant: any) {
    return this.http.put(`${this.enseignantsUrl}/${id}`, enseignant);
  }

  deleteEnseignant(id: number) {
    return this.http.delete(`${this.enseignantsUrl}/${id}`);
  }
  sendContractEmail(id: number) {
    return this.http.post(`${this.enseignantsUrl}/${id}/send-contract`, null);
  }
}
