import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { ContratService } from '../contrat.service';

@Component({
  selector: 'app-contrat',
  templateUrl: './contrat.component.html',
  styleUrls: ['./contrat.component.scss'],
})
export class ContratComponent implements OnInit {
  isAddPopupOpen: boolean = false;
  isUpdatePopupOpen: boolean = false;
  addContratData: any = {};
  updateContratData: any = {};
  contrats: any[] = [];

  constructor(private contratService: ContratService) {}

  ngOnInit() {
    this.loadContrats();
  }

  toggleAddPopup() {
    this.isAddPopupOpen = !this.isAddPopupOpen;
  }
  toggleUpdatePopup1() {
    this.isAddPopupOpen = !this.isAddPopupOpen;
  }

  toggleUpdatePopup(contrat: any) {
    this.updateContratData = contrat;
    this.isUpdatePopupOpen = !this.isUpdatePopupOpen;
  }

  createContrat(contratData: any) {
    // Send a POST request to create a Contrat.
    this.contratService.createContrat(contratData).subscribe(
      (createdContrat: any) => {
        this.contrats.push(createdContrat);
        Swal.fire('Success', 'Contrat created successfully', 'success');
      },
      (error) => {
        Swal.fire('Error', 'Failed to create Contrat', 'error');
      }
    );
  }

  updateContrat(id: number, updatedContratData: any) {
    // Send a PUT request to update a Contrat.
    this.contratService.updateContrat(id, updatedContratData).subscribe(
      (updatedContrat: any) => {
        // Update the Contrat in the contrats array.
        const index = this.contrats.findIndex((contrat) => contrat.id === id);
        if (index !== -1) {
          this.contrats[index] = updatedContrat;
          Swal.fire('Success', 'Contrat updated successfully', 'success');
        } else {
          Swal.fire('Error', 'Contrat not found', 'error');
        }
      },
      (error) => {
        Swal.fire('Error', 'Failed to update Contrat', 'error');
      }
    );
  }

  deleteContrat(id: number) {
    // Send a DELETE request to delete a Contrat.
    this.contratService.deleteContrat(id).subscribe(
      () => {
        // No response received, assume successful deletion
        this.contrats = this.contrats.filter((contrat) => contrat.id !== id);
        Swal.fire('Success', 'Contrat deleted successfully', 'success');
      },
      (error) => {
        Swal.fire('Error', 'Failed to delete Contrat', 'error');
      }
    );
  }

  loadContrats() {
    // Send a GET request to fetch a list of Contrats.
    this.contratService.getAllContrats().subscribe((data: any) => {
      this.contrats = data;
    });
  }
}
