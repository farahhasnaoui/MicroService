import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TuteurService } from 'src/app/service/tuteur.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-tuteur',
  templateUrl: './tuteur.component.html',
  styleUrls: ['./tuteur.component.scss']
})
export class TuteurComponent {
  Tuteurs: any; 

  Tuteur: any = {
    firstName: '',
    lastName: ''
  };
  
  fg = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', Validators.required),
  });
  
  isAddPopupOpen: boolean = false;
  isUpdatePopupOpen: boolean = false;
  
  toggleAddPopup() {
    this.isAddPopupOpen = !this.isAddPopupOpen;
  }
  
  toggleUpdatePopup() {
    this.isUpdatePopupOpen = !this.isUpdatePopupOpen;
  }
  
  toggleClosePopup() {
    this.isAddPopupOpen = !this.isAddPopupOpen;
  }
  
  constructor(
    private tuteurService: TuteurService,
    private acR: ActivatedRoute,
    private route: Router
  ) {}
  
  ngOnInit(): void {
    this.tuteurService.fetchTuteurs().subscribe((tuteurs) => { 
      this.Tuteurs = tuteurs; 
      console.log(tuteurs);
    });
  }
  
  addTuteur(fg: any) { 
    this.tuteurService.addTuteur(this.fg.value).subscribe((d) => { 
      console.log(d);
      this.isAddPopupOpen = !this.isAddPopupOpen;
      this.tuteurService.fetchTuteurs().subscribe((d) => {
        this.Tuteurs = d; 
      });
      this.showSuccessNotification("added!")
    });
  }
  
  deleteTuteur(id: any) { 
    this.tuteurService.deleteTuteur(id).subscribe(() => { 
      console.log("removed");
      this.tuteurService.fetchTuteurs().subscribe((d) => { 
        this.Tuteurs = d; 
      });
      this.showSuccessNotification("removed!")
    });
  }
  
  updateTuteurData(t: any) { 
    this.Tuteur = t; 
    this.toggleUpdatePopup();
  }
  
  updateTuteur(t: any) { 
    return this.tuteurService.updateTuteur(t.id, t).subscribe((d) => { 
      console.log("updated");
      this.tuteurService.fetchTuteurs().subscribe((d) => { 
        this.Tuteurs = d; 
      });
      this.toggleUpdatePopup();
      this.showSuccessNotification("updated!")
    });
  }
  showSuccessNotification(data:any) {
    Swal.fire({
      icon: 'success',
      title: 'Success',
      text: data,
    });
  }
  
}
