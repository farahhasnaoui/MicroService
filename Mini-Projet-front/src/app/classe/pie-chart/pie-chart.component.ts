import { Component, OnInit } from '@angular/core';
import { ClasseService } from '../classe.service';
import { Class } from '../Classe';
import { Chart } from 'chart.js';
@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.scss']
})
export class PieChartComponent implements OnInit{
  data: Object = [];
  pieChart: any;

  constructor(private classeService: ClasseService) { }

  ngOnInit() {
    this.classeService.getClassesAndStudents().subscribe((data: Object) => {
      this.data = data;

      // Créez un tableau des noms des classes
      const dataAsArray: any[] = this.data as any[];
      const classLabels: string[] = dataAsArray.map((item: any) => item[0].nom);


      // Créez un tableau des nombres d'étudiants par classe
     
      const studentCounts = dataAsArray.map((item: any) => item[1].length);


      this.pieChart = new Chart('pieChart', {
        type: 'pie',
        data: {
          labels: classLabels,
          datasets: [{
            data: studentCounts,
            backgroundColor: [
              'red',
              'blue',
              'green',
              // Ajoutez plus de couleurs selon le nombre de classes
            ]
          }]
        }
      });
    });
  }
}





