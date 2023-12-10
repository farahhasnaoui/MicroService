import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ParentServiceService {

  constructor(private http:HttpClient ) { }

   URI="http://localhost:8080/"
   addParent(Parent:any){
    return this.http.post(this.URI+"addparent",Parent)
   }
   fetchParent(){
    return this.http.get(this.URI+"parents")
   }
   deleteParent(id: any): Observable<string> {
    return this.http.delete(this.URI + 'deleteparent/' + id, { responseType: 'text' })
      .pipe(
        map(response => response as string), 
        catchError((error: any) => {
          console.error('Error deleting parent:', error);
          throw error;
        })
      );
  }
   updateParent(id:any , Parent:any){
    return this.http.put(this.URI+"updateparent/"+id,Parent)
   }
   affecterParentTuteur(idP: any, idT: any): Observable<string> {
    return this.http.post(this.URI + 'affectertuteurparent/' + idP + '/' + idT, null, { responseType: 'text' })
      .pipe(
        catchError((error: any) => {
          console.error('Error Affect', error);
          throw error;
        })
      );
  }
  
}
