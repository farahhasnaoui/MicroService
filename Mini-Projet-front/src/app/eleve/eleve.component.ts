import { Component } from '@angular/core';
import { Eleve } from './eleve';
import { EleveService } from './eleve.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-eleve',
  templateUrl: './eleve.component.html',
  styleUrls: ['./eleve.component.scss']
})
export class EleveComponent {
  aa :string = '';;
  updatedEleve: Eleve = new Eleve(); 
  id:any;
  nom: string = '';
  prenom: string = '';
  email: string = '';
  updatedNom: string = '';
  updatedPrenom: string = '';
  updatedEmail: string = '';
  eleveIdToUpdate?: any;
  listeEleves: Eleve[] = [];
  eleves: Eleve = new Eleve();
  isAddPopupOpen: boolean = false;
  isUpdatePopupOpen: boolean = false;
  message: string | null = null;

  constructor( private eleveService:EleveService,private route:Router){}

  
  ngOnInit(): void {
    this.getAllEleve();;

  }

  getAllEleve(){
    this.eleveService.GetAllEleve().subscribe(res => this.listeEleves = res)
    
  }

  toggleAddPopup() {
    this.isAddPopupOpen = !this.isAddPopupOpen;
    
  }

  // openUpdatePopup(eleve: Eleve) {
  //   this.isUpdatePopupOpen = true;
   
  // }
  toggleclosePopup() {
    this.isAddPopupOpen = false;
    this.isUpdatePopupOpen = false;
    // Réinitialisez les valeurs des champs de formulaire si nécessaire
    this.nom = '';
    this.prenom = '';
    this.email = '';
  }
  refreshPage() {
    window.location.reload();
  }

  // addEleve() {
  //   this.eleveService.AddEleve(this.eleves).subscribe(
  //     (response) => {
  //       console.log('Reclamation ajoutée avec succès', response);
  //       // Mettez à jour le message de confirmation
  //       this.message = 'L\'envoi de la réclamation a été effectué avec succès.';
  //       // Redirigez l'utilisateur vers une autre page ou effectuez d'autres actions si nécessaire.
  //       // this.router.navigate(['/reclamations']);
  //     },
  //     (error) => {
  //       console.error('Erreur lors de l\'ajout de la réclamation', error);
  //       // Gérez les erreurs ici.
  //       this.message = 'Erreur lors de l\'ajout de la réclamation. Veuillez réessayer.';
  //     }
  //   );
  // }

  addEleve() {
    // Create an Eleve object from the input values
    const newEleve: Eleve = {
       id:this.id,
        nom: this.nom,
        prenom: this.prenom,
        email: this.email
    };

    // Call the service to add the new Eleve
    this.eleveService.AddEleve(newEleve).subscribe(
        (response) => {
            console.log('Élève ajouté avec succès', response);
            
            this.toggleclosePopup(); // Close the pop-up on success
            // Optionally, you can reset the input fields here
            this.nom = '';
            this.prenom = '';
            this.email = '';

        },
        (error) => {
            console.error('Erreur lors de l\'ajout de l\'élève', error);
            // Handle errors here
        }
    );
}


// updateEleve(id: number, updatedEleve: Eleve): void {
//   this.eleveService.updateEleve(id, updatedEleve)
//     .subscribe(updated => {
//       console.log('Élève mis à jour avec succès:', updated);
//       // Handle the success and response as needed
//     }, error => {
//       console.error('Erreur lors de la mise à jour de l\'élève:', error);
//       // Handle errors
//     });
// }
openUpdatePopup(eleve: Eleve) {
  this.isUpdatePopupOpen = true;
  this.eleveIdToUpdate = eleve.id;
  this.updatedNom = eleve.nom;
  this.updatedPrenom = eleve.prenom;
  this.updatedEmail = eleve.email;
}

// updateEleve(id: number, updatedEleve: Eleve): void {
//   this.eleveService.updateEleve(id, updatedEleve)
//       .subscribe(updated => {
//           console.log('Élève mis à jour avec succès:', updated);
//           // Clear the updated values here
//           this.updatedNom = '';
//           this.updatedPrenom = '';
//           this.updatedEmail = '';
//           this.toggleclosePopup(); // Close the pop-up
//       }, error => {
//           console.error('Erreur lors de la mise à jour de l\'élève:', error);
//           // Handle errors
//       });
// }

updateEleve(id: number): void {
  // Créez un objet Eleve mis à jour à partir des valeurs des champs mis à jour
  const updatedEleve: Eleve = {
    id: this.eleveIdToUpdate,
    nom: this.updatedNom,
    prenom: this.updatedPrenom,
    email: this.updatedEmail
  };

  this.eleveService.updateEleve(id, updatedEleve).subscribe(
    (updated) => {
      console.log('Élève mis à jour avec succès', updated);
      this.toggleclosePopup(); // Fermez la popup en cas de succès
      this.updatedNom = '';
      this.updatedPrenom = '';
      this.updatedEmail = '';
      // Vous pouvez mettre à jour la liste locale des élèves avec les données mises à jour ici
      // this.listeEleves = this.listeEleves.map(eleve => (eleve.id === id ? updated : eleve));
    },
    (error) => {
      console.error('Erreur lors de la mise à jour de l\'élève', error);
      // Gérez les erreurs ici
    }
  );
}


deleteEleves(id: number): void {
  const confirmDelete = window.confirm('Êtes-vous sûr de vouloir supprimer cette classe ?');
  
  if (confirmDelete) {
    this.eleveService.deleteEleve(id)
      .subscribe(() => {
        // Handle success, e.g., remove the deleted classe from your local data
        this.listeEleves = this.listeEleves.filter(eleve => eleve.id !== id);
        console.log(`Classe ${id} supprimée avec succès.`);
      }, error => {
        console.error(`Erreur lors de la suppression de la classe ${id}:`, error);
        // Handle errors as needed
      });
  }
}


performSearch() {
   
  this.eleveService.searchEleve(this.aa).subscribe(
    (result) => {
      console.log('Réponse de la recherche :', result); // Affichez la réponse dans la console
      this.listeEleves = result;
      console.log('zrvbnz',this.aa)
      console.log('result',result)
    },
    (error) => {
      console.error('Erreur lors de la recherche de réclamations', error);
    }
  );

  }
}
