import { Component } from '@angular/core';
import { User } from './User';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
})
export class AuthComponent {
  showSignin = true; 
  user: User = new User(); // Créez une instance de l'utilisateur
  email: string = '';
  password: string = '';
  accessToken: any;

  constructor(private userService: AuthService,private route:Router) { }
  
  toggleView() {
    this.showSignin = !this.showSignin;
  }

  signUp() {
    this.userService.signUp(this.user).subscribe(
      (data) => {
        this.route.navigate(['dashboard']);
      },
      (error) => {
        console.error('ti Erreur ', error);
        
      }
    );
  }

  // signIn(email: string, password: string) {
  //   this.userService.signIn(email, password).subscribe(
  //     (data: any) => {
  //       this.route.navigate(['dashboard']);
  //     },
  //     (error: Error) => {
  //       console.error('Erreur :', error);
  //     }
  //   );
  // }

  signIn(email: string, password: string) {
    this.userService.signIn(email, password).subscribe(
      (data: any) => {
        // Ici, data est la réponse JSON de votre serveur
        const accessToken = data.accessToken;
        // Faites quelque chose avec l'access token, par exemple, le stocker dans une variable du composant
        this.accessToken = accessToken;
        console.log('token',accessToken)
        // Vous pouvez également rediriger l'utilisateur vers une autre page ici
        this.route.navigate(['dashboard']);
      },
      (error: Error) => {
        console.error('Erreur :', error);
      }
    );
  }
  
}
