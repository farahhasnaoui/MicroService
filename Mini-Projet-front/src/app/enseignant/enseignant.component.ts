import { Component, OnInit } from '@angular/core';
import { EnseignantService } from '../enseignant.service';
import { ContratService } from '../contrat.service'; // Import the service for contracts
import Swal from 'sweetalert2';

@Component({
  selector: 'app-enseignant',
  templateUrl: './enseignant.component.html',
  styleUrls: ['./enseignant.component.scss']
})
export class EnseignantComponent implements OnInit {
  enseignants: any[] = [];
  isAddPopupOpen: boolean = false;
  isUpdatePopupOpen: boolean = false;
  addEnseignantData: any = { contrat: { id: null } };
  updateEnseignantData: any = { contrat: { id: null } };
  contrats: any[] = [];

  constructor(
    private enseignantService: EnseignantService,
    private contratService: ContratService // Inject the contract service
  ) {}

  ngOnInit() {
    this.loadEnseignants();
    this.loadContrats();
  }

  toggleAddPopup() {
    this.isAddPopupOpen = !this.isAddPopupOpen;
  }

  toggleUpdatePopup(enseignant: any) {
    this.updateEnseignantData = { ...enseignant };
    this.isUpdatePopupOpen = !this.isUpdatePopupOpen;
  }

  loadEnseignants() {
    this.enseignantService.getAllEnseignants().subscribe((data: any) => {
      this.enseignants = data;
    });
  }

  loadContrats() {
    this.contratService.getAllContrats().subscribe((data: any) => {
      this.contrats = data;
    });
  }

  createEnseignant() {
    this.enseignantService.createEnseignant(this.addEnseignantData, this.addEnseignantData.contrat.id)
      .subscribe(
        (createdEnseignant: any) => {
          this.enseignants.push(createdEnseignant);
          Swal.fire('Succès', 'Enseignant créé avec succès', 'success');
        },
        (error) => {
          Swal.fire('Erreur', 'Échec de la création de l\'enseignant', 'error');
        }
      );
  }
  
  

  updateEnseignant() {
    this.enseignantService.updateEnseignant(this.updateEnseignantData.id, this.updateEnseignantData).subscribe(
      (updatedEnseignant: any) => {
        const index = this.enseignants.findIndex(enseignant => enseignant.id === updatedEnseignant.id);
        if (index !== -1) {
          this.enseignants[index] = updatedEnseignant;
        }
        this.isUpdatePopupOpen = false;
        Swal.fire('Succès', 'Enseignant mis à jour avec succès', 'success');
      },
      (error) => {
        Swal.fire('Erreur', 'Échec de la mise à jour de l\'enseignant', 'error');
      }
    );
  }

  deleteEnseignant(id: number) {
    this.enseignantService.deleteEnseignant(id).subscribe(
      () => {
        this.enseignants = this.enseignants.filter(enseignant => enseignant.id !== id);
        Swal.fire('Succès', 'Enseignant supprimé avec succès', 'success');
      },
      (error) => {
        Swal.fire('Erreur', 'Échec de la suppression de l\'enseignant', 'error');
      }
    );
  }
  sendContractEmail(id: number) {
    this.enseignantService.sendContractEmail(id).subscribe(
      () => {
        // Gérer la réussite de l'envoi de l'e-mail
        alert('Contrat envoyé par e-mail.');
      },
      (error) => {
        // Gérer les erreurs
        alert('Erreur lors de l\'envoi de l\'e-mail.');
      }
    );
  }
}
