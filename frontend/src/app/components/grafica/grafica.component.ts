import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { DatosService } from '../../services/datos.service';
import * as d3 from 'd3';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'app-grafica',
  standalone: true,
  providers: [],
  templateUrl: './grafica.component.html',
  styleUrls: ['./grafica.component.css']
})
export class GraficaComponent implements OnInit {
  private svg: any;
  private margin = 50;
  private width = 750 - (this.margin * 2);
  private height = 400 - (this.margin * 2);
  private isBrowser: boolean;
  public title: string = '';  // Define la variable title

  constructor(private datosService: DatosService, @Inject(PLATFORM_ID) private platformId: Object) {
    this.isBrowser = isPlatformBrowser(platformId);  // Verifica si estamos en el navegador
  }

  ngOnInit(): void {
    if (this.isBrowser) {  // Solo ejecuta si estamos en el navegador
      this.showGraphEdadVsHoras();  // Muestra la gráfica por defecto
    }
  }

  // Función genérica para crear el gráfico
  private drawBars(data: any[], xKey: string, yKey: string): void {
    // Limpia el gráfico anterior
    this.createSvg();

    const x = d3.scaleBand()
      .range([0, this.width])
      .domain(data.map(d => d[xKey].toString()))
      .padding(0.2);

    this.svg.append('g')
      .attr('transform', 'translate(0,' + this.height + ')')
      .call(d3.axisBottom(x))
      .selectAll('text')
      .attr('transform', 'translate(-10,0)rotate(-45)')
      .style('text-anchor', 'end');

    const y = d3.scaleLinear()
      .domain([0, d3.max(data, (d: any) => +d[yKey])!])
      .range([this.height, 0]);

    this.svg.append('g')
      .call(d3.axisLeft(y));

    this.svg.selectAll('rect')
      .data(data)
      .enter()
      .append('rect')
      .attr('x', (d: any) => x(d[xKey].toString()))
      .attr('y', (d: any) => y(+d[yKey]))
      .attr('width', x.bandwidth())
      .attr('height', (d: any) => this.height - y(+d[yKey]))
      .attr('fill', '#d04a35');
  }

  // Función para mostrar Edad vs Horas trabajadas
  showGraphEdadVsHoras(): void {
    this.title = 'Gráfica: Edad vs Horas Trabajadas';
    this.datosService.getDatos().subscribe(data => {
      const filteredData = data.filter((d: any) => d.age && d.hours_per_week);
      this.drawBars(filteredData, 'age', 'hours_per_week');
    });
  }

  // Función para mostrar Edad vs Ingreso
  showGraphEdadVsIngreso(): void {
    this.title = 'Gráfica: Edad vs Ingreso';
    this.datosService.getDatos().subscribe(data => {
      const filteredData = data.filter((d: any) => d.age && d.income);

      // Mapeamos los valores de 'income' a un valor numérico (si 'income' es categórico)
      const incomeMapping: { [key: string]: number } = { '<=50K': 1, '>50K': 2 }; // Ajusta las categorías según sea necesario
      const processedData = filteredData.map((d: any) => ({
        age: d.age,
        income: incomeMapping[d.income as string] || 0 // Asignamos 0 si no coincide con las categorías
      }));

      this.drawScatterPlot(processedData, 'age', 'income');
    });
  }

// Función para crear una gráfica de dispersión
  private drawScatterPlot(data: any[], xKey: string, yKey: string): void {
    // Limpia el gráfico anterior
    this.createSvg();

    const x = d3.scaleLinear()
      .domain([d3.min(data, (d: any) => +d[xKey])!, d3.max(data, (d: any) => +d[xKey])!])
      .range([0, this.width]);

    const y = d3.scaleLinear()
      .domain([0, 2]) // Aquí 0 es ninguna categoría, 1 es <=50K, 2 es >50K
      .range([this.height, 0]);

    this.svg.append('g')
      .attr('transform', 'translate(0,' + this.height + ')')
      .call(d3.axisBottom(x));

    this.svg.append('g')
      .call(d3.axisLeft(y).ticks(2).tickFormat(d => d === 1 ? '<=50K' : '>50K')); // Etiquetas personalizadas para los ingresos

    this.svg.selectAll('circle')
      .data(data)
      .enter()
      .append('circle')
      .attr('cx', (d: any) => x(d[xKey]))
      .attr('cy', (d: any) => y(d[yKey]))
      .attr('r', 5)
      .attr('fill', '#69b3a2');
  }


  // Función genérica que permite cambiar entre diferentes conjuntos de datos
  showGraphGeneric(xKey: string, yKey: string): void {
    this.title = `Gráfica: ${xKey} vs ${yKey}`;
    this.datosService.getDatos().subscribe(data => {
      const filteredData = data.filter((d: any) => d[xKey] && d[yKey]);
      this.drawBars(filteredData, xKey, yKey);
    });
  }

  // Función para mostrar la gráfica circular (relación vs proporción)
  public showGraph4(): void {
    this.title = 'Gráfica 4: Relación vs Proporción';
    this.datosService.getDatos().subscribe(data => {
      // Agrupar los datos por relación
      const groupedData = d3.rollup(data, v => v.length, (d: any) => d.relationship);
      const processedData = Array.from(groupedData, ([key, value]) => ({ key, value }));

      this.createSvg();
      this.drawPieChart(processedData);  // Dibuja la gráfica circular
    });
  }

  // Función para dibujar la gráfica circular
  private drawPieChart(data: any[]): void {
    const radius = Math.min(this.width, this.height) / 2;
    const pie = d3.pie<any>().value((d: any) => d.value);

    const arc = d3.arc<any>()
      .innerRadius(0)
      .outerRadius(radius);

    const color = d3.scaleOrdinal()
      .domain(data.map(d => d.key))
      .range(d3.schemeCategory10);

    const pieGroup = this.svg
      .append('g')
      .attr('transform', 'translate(' + this.width / 2 + ',' + this.height / 2 + ')');

    const arcs = pieGroup.selectAll('arc')
      .data(pie(data))
      .enter()
      .append('g')
      .attr('class', 'arc');

    arcs.append('path')
      .attr('d', arc)
      .attr('fill', (d: any) => color(d.data.key));

    arcs.append('text')
      .attr('transform', (d: any) => 'translate(' + arc.centroid(d) + ')')
      .text((d: any) => d.data.key)
      .style('text-anchor', 'middle');
  }

  // Método que borra el gráfico anterior y crea uno nuevo
  private createSvg(): void {
    // Elimina cualquier gráfico SVG existente
    d3.select('figure#bar').select('svg').remove();

    // Crea el nuevo SVG
    this.svg = d3.select('figure#bar')
      .append('svg')
      .attr('width', this.width + (this.margin * 2))
      .attr('height', this.height + (this.margin * 2))
      .append('g')
      .attr('transform', 'translate(' + this.margin + ',' + this.margin + ')');
  }
}
