import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }


  // signUp(username: string, email: string, password: string, roles: string[]) {
  //   const user = { username, email, password, roles };
  //   return this.http.post('http://localhost:8082/api/auth/signup', user);
  // }
  signUp(user: User): Observable<any> {
    return this.http.post('http://localhost:8082/api/auth/signup', user);
  }
  
  signIn(email: string, password: string) {
    const credentials = { email, password };
    return this.http.post('http://localhost:8082/api/auth/signin', credentials); // Assurez-vous d'ajuster l'URL selon votre configuration serveur.
  }

}
