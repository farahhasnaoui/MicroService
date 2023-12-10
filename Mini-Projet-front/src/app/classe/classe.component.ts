import { Component } from '@angular/core';
import { Class } from './Classe';
import { ClasseService } from './classe.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-classe',
  templateUrl: './classe.component.html',
  styleUrls: ['./classe.component.scss']
})
export class ClasseComponent {
  listeClasse: Class[] = [];
  isAddPopupOpen: boolean = false;
  isUpdatePopupOpen: boolean = false;
  nom: string = '';
  etage: string = '';
  block: string = '';
  id:any;
  updatedNom: string = '';
  updatedEtage: string = '';
  updatedBlock: string = '';
  ClasseIdToUpdate?: any;


  constructor( private classeService:ClasseService,private route:Router){}


  ngOnInit(): void {
    this.getAllClasse();;

  }

  getAllClasse(){
    this.classeService.GetAllClasse().subscribe(res => this.listeClasse = res)
    
  }

  toggleAddPopup() {
    this.isAddPopupOpen = !this.isAddPopupOpen;
  }

  toggleUpdatePopup() {
    this.isUpdatePopupOpen = !this.isUpdatePopupOpen;
  } 
  toggleclosePopup() {
    this.isAddPopupOpen = !this.isAddPopupOpen;
  }
  refreshPage() {
    window.location.reload();
  }


 

  addClasse() {
    // Create an Eleve object from the input values
    const newCalsse: Class = {
        id:this.id,
        nom: this.nom,
        etage: this.etage,
        block: this.block
    };

    // Call the service to add the new Eleve
    this.classeService.AddClasse(newCalsse).subscribe(
        (response) => {
            console.log('Élève ajouté avec succès', response);
            this.toggleclosePopup(); // Close the pop-up on success
            // Optionally, you can reset the input fields here
            this.nom = '';
            this.etage = '';
            this.block = '';
        },
        (error) => {
            console.error('Erreur lors de l\'ajout de l\'élève', error);
            // Handle errors here
        }
    );
}


openUpdatePopup(classe: Class) {
  this.isUpdatePopupOpen = true;
  this.ClasseIdToUpdate = classe.id;
  this.updatedNom = classe.nom;
  this.updatedEtage = classe.etage;
  this.updatedBlock = classe.block;
}

updateClasse(id: number): void {
  // Créez un objet Eleve mis à jour à partir des valeurs des champs mis à jour
  const updatedClasse: Class = {
    id: this.ClasseIdToUpdate,
    nom: this.updatedNom,
    etage: this.updatedEtage,
    block: this.updatedBlock
  };

  this.classeService.updateClasse(id, updatedClasse).subscribe(
    (updated) => {
      console.log('Élève mis à jour avec succès', updated);
      this.toggleclosePopup(); // Fermez la popup en cas de succès
      this.updatedNom = '';
      this.updatedEtage = '';
      this.updatedBlock = '';
      // Vous pouvez mettre à jour la liste locale des élèves avec les données mises à jour ici
      // this.listeEleves = this.listeEleves.map(eleve => (eleve.id === id ? updated : eleve));
    },
    (error) => {
      console.error('Erreur lors de la mise à jour de l\'élève', error);
      // Gérez les erreurs ici
    }
  );
}




deleteClasse(id: number): void {
  const confirmDelete = window.confirm('Êtes-vous sûr de vouloir supprimer cette classe ?');
  
  if (confirmDelete) {
    this.classeService.deleteClasse(id)
      .subscribe(() => {
        // Handle success, e.g., remove the deleted classe from your local data
        this.listeClasse = this.listeClasse.filter(classe => classe.id !== id);
        console.log(`Classe ${id} supprimée avec succès.`);
      }, error => {
        console.error(`Erreur lors de la suppression de la classe ${id}:`, error);
        // Handle errors as needed
      });
  }
}




trier() {
  this.classeService.sortClasse().subscribe(
    (result) => {
      console.log('classe triées :', result);
      // Mettez à jour la liste des classe triées ici si nécessaire
      this.listeClasse = result;
    },
    (error) => {
      console.error('Erreur lors du tri des classe', error);
      // Gérez les erreurs ici
    }
  );
}

navigateToPieChart() {
  this.route.navigate(['/pie']);
}

}
