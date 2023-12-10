import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProgrammesActiviteService {
  private programmesUrl = 'http://localhost:8095/programmes'; 
  private activitesUrl = 'http://localhost:8095/activites';  

  constructor(private http: HttpClient) { }

  // Programmes CRUD operations
  getAllProgrammes() {
    return this.http.get(this.programmesUrl);
  }

  getProgrammeById(id: number) {
    return this.http.get(`${this.programmesUrl}/${id}`);
  }

  createProgramme(programme: any) {
    return this.http.post(this.programmesUrl, programme);
  }

  updateProgramme(id: number, programme: any) {
    return this.http.put(`${this.programmesUrl}/${id}`, programme);
  }

  deleteProgramme(id: number) {
    return this.http.delete(`${this.programmesUrl}/${id}`);
  } 
  getProgramCount() {
    return this.http.get<number>(`${this.programmesUrl}/count`);
  }

  getAverageActivitiesPerProgram() {
    return this.http.get<number>(`${this.programmesUrl}/average-activities`);
  }

  getProgramsWithLongestDuration() {
    return this.http.get<any[]>(`${this.programmesUrl}/longest-duration`);
  }

 //Activities ::::::::::::::::::::::::::::::::::::::::::
  getAllActivites() {
    return this.http.get(this.activitesUrl);
  }

  getActiviteById(id: number) {
    return this.http.get(`${this.activitesUrl}/${id}`);
  }

  createActivite(activite: any) {
    return this.http.post(this.activitesUrl, activite);
  }

  updateActivite(id: number, activite: any) {
    return this.http.put(`${this.activitesUrl}/${id}`, activite);
  }

  deleteActivite(id: number) {
    return this.http.delete(`${this.activitesUrl}/${id}`);
  } 
  getAverageActivityDuration() {
    return this.http.get<number>(`${this.activitesUrl}/average-activity-duration`);
  }

  getAverageActivitiesPerDay() {
    return this.http.get<number>(`${this.activitesUrl}/average-activities-per-day`);
  }

  getMaximumActivityDuration() {
    return this.http.get<number>(`${this.activitesUrl}/maximum-activity-duration`);
  }

  getNumberOfActivities() {
    return this.http.get<number>(`${this.activitesUrl}/number-of-activities`);
  }
}
