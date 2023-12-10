// import { Component } from '@angular/core';
// import { ProgrammesActiviteService } from '../services/programmes-activite.service'; // Replace with the correct path
// import Swal from 'sweetalert2';
// @Component({
//   selector: 'app-activite-programme',
//   templateUrl: './activite-programme.component.html',
//   styleUrls: ['./activite-programme.component.scss']
// })
// export class ActiviteProgrammeComponent {

//   isAddPopupOpen: boolean = false;
//   isUpdatePopupOpen: boolean = false;
//   addActiviteData: any = { programme: { id: null } };

//   updateActiviteData: any = { programme: { id: null } };
//   programmes: any[] = [];

//   toggleAddPopup() {
//     this.isAddPopupOpen = !this.isAddPopupOpen;
//   }
//   loadProgrammes() {
//     this.programActiviteService.getAllProgrammes().subscribe((data: any) => {
//       this.programmes = data;
//     });
//   }
  

//   toggleUpdatePopup(activite: any) { 

//     this.updateActiviteData = activite;
//     this.isUpdatePopupOpen = !this.isUpdatePopupOpen;
//   }
//   toggleUpdatePopup1() { 

   
//     this.isUpdatePopupOpen = !this.isUpdatePopupOpen;
//   }
//   toggleclosePopup() {
//     this.isAddPopupOpen = !this.isAddPopupOpen;
//   }
//   activites: any[]= []; 


//   ngOnInit() {
   
//     this.loadActivites();
//     this.loadProgrammes(); 
//   }

//   constructor(private programActiviteService: ProgrammesActiviteService) {}

//   loadActivites() {
//     this.programActiviteService.getAllActivites().subscribe((data: any) => {
//       this.activites = data;
//     });
//   }


//   createActivite(activiteData: any) {
//     this.programActiviteService.createActivite(activiteData).subscribe((createdActivite: any) => {
//       this.activites.push(createdActivite);
//       Swal.fire('Success', 'Activite created successfully', 'success');
//     }, error => {
//       Swal.fire('Error', 'Failed to create activite', 'error');
//     });
//   }

//   updateActivite(id: number, updatedActiviteData: any) {
//     this.programActiviteService.updateActivite(id, updatedActiviteData).subscribe((updatedActivite: any) => {
//       const index = this.activites.findIndex(activite => activite.id === id);
//       if (index !== -1) {
//         this.activites[index] = updatedActivite;
//         Swal.fire('Success', 'Activite updated successfully', 'success');
//       } else {
//         Swal.fire('Error', 'Activite not found', 'error');
//       }
//     }, error => {
//       Swal.fire('Error', 'Failed to update activite', 'error');
//     });
//   }
//   deleteActivite(id: number) {
//     this.programActiviteService.deleteActivite(id).subscribe(
//       () => {
//         // No response received, assume successful deletion
//         console.log('Deletion successful'); // Add this line for debugging
//         this.activites = this.activites.filter(activite => activite.id !== id);
//         Swal.fire('Success', 'Activite deleted successfully', 'success');
//       },
//       (error) => {
//         console.log('Deletion error:', error); // Add this line for debugging
//         Swal.fire('Success', 'Activite deleted successfully', 'success').then(() => {
//           location.reload();
//         });
   
      
//       }
//     );
//   }
  
  
// }

import { Component } from '@angular/core';
import { ProgrammesActiviteService } from '../services/programmes-activite.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-activite-programme',
  templateUrl: './activite-programme.component.html',
  styleUrls: ['./activite-programme.component.scss']
})
export class ActiviteProgrammeComponent {
  isAddPopupOpen: boolean = false;
  isUpdatePopupOpen: boolean = false;
  addActiviteData: any = { programme: { id: null } };
  updateActiviteData: any = { programme: { id: null } };
  programmes: any[] = [];
  activites: any[] = [];
  averageActivityDuration: number = 0; 
  averageActivitiesPerDay: number = 0; 
  maximumActivityDuration: number = 0; 
  numberOfActivities: number = 0; 
  isstatpopup: boolean=false;

  constructor(private programActiviteService: ProgrammesActiviteService) {}

  ngOnInit() {
    this.loadActivites();
    this.loadProgrammes();
    this.loadStatistics();
  }

  loadProgrammes() {
    this.programActiviteService.getAllProgrammes().subscribe((data: any) => {
      this.programmes = data;
    });
  } 
  toggleStatPopup(){
    this.isstatpopup = !this.isstatpopup;
  } 
  togglestatPopup(){
    this.isstatpopup = !this.isstatpopup;

  }

  loadActivites() {
    this.programActiviteService.getAllActivites().subscribe((data: any) => {
      this.activites = data;
    });
  }

  loadStatistics() {
    this.programActiviteService.getAverageActivityDuration().subscribe((duration: number) => {
      this.averageActivityDuration = duration; 
      console.log(this.averageActivityDuration)
    });

    this.programActiviteService.getAverageActivitiesPerDay().subscribe((activitiesPerDay: number) => {
      this.averageActivitiesPerDay = activitiesPerDay;
      console.log(this.averageActivitiesPerDay)
    });

    this.programActiviteService.getMaximumActivityDuration().subscribe((maxDuration: number) => {
      this.maximumActivityDuration = maxDuration;
      console.log(this.maximumActivityDuration)
    });

    this.programActiviteService.getNumberOfActivities().subscribe((count: number) => {
      this.numberOfActivities = count;
      console.log(this.numberOfActivities)
    });
  }

  toggleAddPopup() {
    this.isAddPopupOpen = !this.isAddPopupOpen;
  }

  toggleUpdatePopup(activite: any) {
    this.updateActiviteData = activite;
    this.isUpdatePopupOpen = !this.isUpdatePopupOpen;
  }

  toggleUpdatePopup1() {
    this.isUpdatePopupOpen = !this.isUpdatePopupOpen;
  }

  toggleclosePopup() {
    this.isAddPopupOpen = !this.isAddPopupOpen;
  }

  createActivite(activiteData: any) {
    this.programActiviteService.createActivite(activiteData).subscribe(
      (createdActivite: any) => {
        this.activites.push(createdActivite);
        Swal.fire('Success', 'Activite created successfully', 'success');
      },
      (error) => {
        Swal.fire('Error', 'Failed to create activite', 'error');
      }
    );
  }

  updateActivite(id: number, updatedActiviteData: any) {
    this.programActiviteService.updateActivite(id, updatedActiviteData).subscribe(
      (updatedActivite: any) => {
        const index = this.activites.findIndex((activite) => activite.id === id);
        if (index !== -1) {
          this.activites[index] = updatedActivite;
          Swal.fire('Success', 'Activite updated successfully', 'success');
        } else {
          Swal.fire('Error', 'Activite not found', 'error');
        }
      },
      (error) => {
        Swal.fire('Error', 'Failed to update activite', 'error');
      }
    );
  }

  deleteActivite(id: number) {
    this.programActiviteService.deleteActivite(id).subscribe(
      () => {
        this.activites = this.activites.filter((activite) => activite.id !== id);
        Swal.fire('Success', 'Activite deleted successfully', 'success');
      },
      (error) => {
        Swal.fire('Error', 'Failed to delete activite', 'error');
      }
    );
  }
}
