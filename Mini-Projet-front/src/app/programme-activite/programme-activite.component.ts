import { Component, OnInit, ElementRef, ViewChild, AfterViewInit } from '@angular/core';
import Swal from 'sweetalert2';
import { ProgrammesActiviteService } from '../services/programmes-activite.service';
import { Chart, ChartOptions,ChartDataset, CategoryScale, LinearScale, Title, Tooltip, Legend, plugins } from 'chart.js';

@Component({
  selector: 'app-programme-activite',
  templateUrl: './programme-activite.component.html',
  styleUrls: ['./programme-activite.component.scss']
})
export class ProgrammeActiviteComponent implements OnInit, AfterViewInit {
  isAddPopupOpen: boolean = false;
  isUpdatePopupOpen: boolean = false;
  addProgrammeData: any = {};
  updateProgrammeData: any = {};
  programmes: any[] = [];
  activites: any[] = [];
  programCount: any;
  averageActivities: any;
  programsWithLongestDuration: any[] = []; 
  showCharts: boolean = false;

  @ViewChild('averageActivitiesChart') averageActivitiesChart!: ElementRef;
  averageActivitiesChartInstance!: Chart; 

  @ViewChild('longestDurationChart') longestDurationChart!: ElementRef;
  longestDurationChartInstance!: Chart;
  isstatpopup: boolean =  false;

  constructor(private programActiviteService: ProgrammesActiviteService) {}

  ngOnInit(): void {
    this.programActiviteService.getProgramCount().subscribe(count => {
      this.programCount = count;
      console.log(`Number of Programs: ${this.programCount}`);
    });

    this.programActiviteService.getAverageActivitiesPerProgram().subscribe(average => {
      this.averageActivities = average; 
      this.createAverageActivitiesChart()
      console.log(`Average Activities per Program: ${this.averageActivities}`);
    });

    this.programActiviteService.getProgramsWithLongestDuration().subscribe(programs => {
      this.programsWithLongestDuration = programs; 
      this.createLongestDurationChart()
      console.log('Programs with Longest Duration:', this.programsWithLongestDuration);
    });

    this.loadProgrammes();
    this.loadActivites();
  }
  togglestatPopup(){
    this.showCharts = !this.showCharts;
  }

  ngAfterViewInit(): void {
    this.createAverageActivitiesChart(); 
    this.createLongestDurationChart();
  }

  toggleAddPopup() {
    this.isAddPopupOpen = !this.isAddPopupOpen;
  }

  toggleUpdatePopup(programme: any) {
    this.updateProgrammeData = programme;
    this.isUpdatePopupOpen = !this.isUpdatePopupOpen;
  }

  toggleUpdatePopup1() {
    this.isUpdatePopupOpen = !this.isUpdatePopupOpen;
  }

  toggleclosePopup() {
    this.isAddPopupOpen = !this.isAddPopupOpen;
  }

  loadProgrammes() {
    this.programActiviteService.getAllProgrammes().subscribe((data: any) => {
      this.programmes = data;
    });
  }

  loadActivites() {
    this.programActiviteService.getAllActivites().subscribe((data: any) => {
      this.activites = data;
    });
  }

  createProgramme(programmeData: any) {
    this.programActiviteService.createProgramme(programmeData).subscribe(
      (createdProgramme: any) => {
        this.programmes.push(createdProgramme);
        Swal.fire('Success', 'Programme created successfully', 'success');
      },
      (error) => {
        Swal.fire('Error', 'Failed to create programme', 'error');
      }
    );
  }

  updateProgramme(id: number, updatedProgrammeData: any) {
    this.programActiviteService.updateProgramme(id, updatedProgrammeData).subscribe(
      (updatedProgramme: any) => {
        const index = this.programmes.findIndex((programme) => programme.id === id);
        if (index !== -1) {
          this.programmes[index] = updatedProgramme;
          Swal.fire('Success', 'Programme updated successfully', 'success');
        } else {
          Swal.fire('Error', 'Programme not found', 'error');
        }
      },
      (error) => {
        Swal.fire('Error', 'Failed to update programme', 'error');
      }
    );
  }

  deleteProgramme(id: number) {
    this.programActiviteService.deleteProgramme(id).subscribe(
      () => {
        this.programmes = this.programmes.filter(programme => programme.id !== id);
        Swal.fire('Success', 'Programme deleted successfully', 'success');
      },
      (error) => {
        Swal.fire('Error', 'Failed to delete programme', 'error');
      }
    );
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
  createAverageActivitiesChart() {
    // Destroy the previous chart instance, if it exists
    if (this.averageActivitiesChartInstance) {
      this.averageActivitiesChartInstance.destroy();
    }
  
    this.averageActivitiesChartInstance = new Chart(this.averageActivitiesChart.nativeElement, {
      type: 'bar',
      data: {
        labels: ['Average Activities per Program'],
        datasets: [{
          label: 'Average Activities',
          data: [this.averageActivities],
          backgroundColor: 'rgba(75, 192, 192, 0.2)',
          borderColor: 'rgba(75, 192, 192, 1)',
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          x: {
            beginAtZero: true
          },
          y: {
            beginAtZero: true,
            suggestedMin: 0
          }
        }
      }
    });
  } 




  createLongestDurationChart() {
    // Destroy the previous chart instance, if it exists
    if (this.longestDurationChartInstance) {
      this.longestDurationChartInstance.destroy();
    }
  
    const labels = this.programsWithLongestDuration.map(program => program.nom);
    const data = this.programsWithLongestDuration.map(program => program.dureeEnSemaines);
  
    const radarData = {
      labels: labels,
      datasets: [
        {
          label: 'Programs with Longest Duration',
          data: data,
          fill: true,
          backgroundColor: 'rgba(54, 162, 235, 0.2)',
          borderColor: 'rgb(54, 162, 235)',
          pointBackgroundColor: 'rgb(54, 162, 235)',
          pointBorderColor: '#fff',
          pointHoverBackgroundColor: '#fff',
          pointHoverBorderColor: 'rgb(54, 162, 235)'
        }
      ]
    };
  
    const radarOptions = {
      elements: {
        line: {
          borderWidth: 3
        }
      },
      scales: {
        r: {
          beginAtZero: true
        }
      }
    };
  
    this.longestDurationChartInstance = new Chart(this.longestDurationChart.nativeElement, {
      type: 'radar',
      data: radarData,
      options: radarOptions
    });
  }
  
  
}
  


  
