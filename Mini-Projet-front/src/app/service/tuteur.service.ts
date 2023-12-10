import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TuteurService {

  constructor(private http:HttpClient ) { }

  URI="http://localhost:8080/"
  addTuteur(tuteur: any): Observable<any> {
    return this.http.post<any>(`${this.URI}addtuteur`, tuteur);
  }
  
  fetchTuteurs(): Observable<any[]> {
    return this.http.get<any[]>(`${this.URI}tuteurs`);
  }
  
  deleteTuteur(id: any): Observable<string> {
    return this.http.delete(`${this.URI}deletetuteur/${id}`, { responseType: 'text' })
      .pipe(
        catchError((error: any) => {
          console.error('Error deleting tuteur:', error);
          throw error;
        })
      );
  }
  
  updateTuteur(id: any, tuteur: any): Observable<any> {
    return this.http.put<any>(`${this.URI}updatetuteur/${id}`, tuteur);
  }
}
