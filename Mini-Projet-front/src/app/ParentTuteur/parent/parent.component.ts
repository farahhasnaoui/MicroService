import { Component , OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ParentServiceService } from 'src/app/service/parent-service.service';
import { TuteurService } from 'src/app/service/tuteur.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-parent',
  templateUrl: './parent.component.html',
  styleUrls: ['./parent.component.scss']
})
export class ParentComponent { 
   Parents:any
   selectedTuteurId:any
   tuteursForSelect: any[] = [];
   Parent:any = {
    firstName:'',
    lastName: ''
  };
   fg = new FormGroup({
    firstName:new FormControl('',[Validators.required,]),
    lastName:new FormControl('',Validators.required),
  })

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
constructor(private ParentService:ParentServiceService, private acR:ActivatedRoute, private route:Router, private tuteurService:TuteurService){}

ngOnInit():void{
  this.ParentService.fetchParent().subscribe((p)=>{
    this.Parents=p
    console.log(p);
  })
  this.tuteurService.fetchTuteurs().subscribe(data => {
    this.tuteursForSelect = data;
  });
}

addParent(fg:any){
      
  this.ParentService.addParent(this.fg.value).subscribe((d)=>{
   console.log(d)
   //this.route.navigate(['listcontrat'])
   this.isAddPopupOpen = !this.isAddPopupOpen;
   this.ParentService.fetchParent().subscribe((d)=>{
    this.Parents=d
   })
   this.showSuccessNotification("added!")
 })
}

deletParent(id:any){
    
  this.ParentService.deleteParent(id).subscribe(()=>{
    console.log("removed")
    this.ParentService.fetchParent().subscribe((d)=>{
      this.Parents=d
     })
     this.showSuccessNotification("removed!")
    
  })


}
updateParentData(p:any){
  this.Parent=p;
  this.toggleUpdatePopup()
}
updateParent(p:any){
  return this.ParentService.updateParent(p.id,p).subscribe((d)=>{
    console.log("updated")
    this.ParentService.fetchParent().subscribe((d)=>{
      this.Parents=d
     })
     this.toggleUpdatePopup()
     this.showSuccessNotification("updated!")
  })
}
affecterParentTuteur(idP:any,idT:any){
  return this.ParentService.affecterParentTuteur(idP,idT).subscribe((d)=>{
    console.log("affected")
    this.ParentService.fetchParent().subscribe((d)=>{
      this.Parents=d
     })
     this.showSuccessNotification("affected!")
  })
}
showSuccessNotification(data:any) {
  Swal.fire({
    icon: 'success',
    title: 'Success',
    text: data,
  });
}
}
