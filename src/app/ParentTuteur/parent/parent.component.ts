import { Component } from '@angular/core';

@Component({
  selector: 'app-parent',
  templateUrl: './parent.component.html',
  styleUrls: ['./parent.component.scss']
})
export class ParentComponent { 
  isAddPopupOpen: boolean = false;
  isUpdatePopupOpen: boolean = false;

  toggleAddPopup() {
    this.isAddPopupOpen = !this.isAddPopupOpen;
  }

  toggleUpdatePopup() {
    this.isUpdatePopupOpen = !this.isUpdatePopupOpen;
  } 
  toggleclosePopup() {
    this.isAddPopupOpen = !this.isAddPopupOpen;
  }


}
